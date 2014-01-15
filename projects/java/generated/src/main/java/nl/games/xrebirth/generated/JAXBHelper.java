package nl.games.xrebirth.generated;

import com.google.common.base.Joiner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 3-1-14
 * Time: 2:41
 * To change this template use File | Settings | File Templates.
 */
public class JAXBHelper {

    private static ThreadLocal<JAXBHelper> THREAD_LOCAL = new ThreadLocal<JAXBHelper>() {
        @Override
        protected JAXBHelper initialValue() {
            return new JAXBHelper();
        }
    };


    private Map<String, String> filtermap;
    private JAXBContext jc;
    private boolean validating = false;
    private final Schema schema;
    SAXParserFactory spf = SAXParserFactory.newInstance();


    public static JAXBHelper get() {
        return THREAD_LOCAL.get();
    }

    private JAXBHelper() {
        try {
            this.filtermap = loadEpisodeFile();
            this.schema = loadSchema();
            Object[] arr = filtermap.keySet().toArray();
            String joined = Joiner.on(":").skipNulls().join(arr);
            jc = JAXBContext.newInstance(joined);
            spf.setNamespaceAware(true);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValidating() {
        return validating;
    }

    public void setValidating(boolean validating) {
        this.validating = validating;
    }

    String getFilter(Package aPackage) {
        return filtermap.get(aPackage.getName());
    }

    String getFilter(Class aClass) {
        return getFilter(aClass.getPackage());
    }

    private Schema loadSchema() {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            URL schemaDir = getClass().getClassLoader().getResource("META-INF/xsd/");
            List<StreamSource> schemaSources = new ArrayList<>();
            final List<String> ignore = Arrays.asList("common.xsd", "racelookup.xsd");
            if (schemaDir != null) {
                File f = new File(schemaDir.toURI());
                File[] xsds = f.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".xsd") && !ignore.contains(name);
                    }
                });
                for (File xsd : xsds) {
                    schemaSources.add(new StreamSource(xsd));
                }
            }
            return schemaFactory.newSchema(schemaSources.toArray(new StreamSource[schemaSources.size()]));
        } catch (URISyntaxException | SAXException e) {
            throw new JAXBHelperException(e);
        }
    }


    public <T> T unMarshall(InputStream inputStream, Class<T> clazz) throws Exception {
        //get the filter
        String tns = getFilter(clazz);
        if (inputStream == null) {
            throw new NullPointerException("input stream is null");
        }

        if (tns == null) {
            throw new IllegalArgumentException(String.format("%s not configuered in JAXB", clazz.getPackage().getName()));
        }
        InputReaderFixer fixedReader = new InputReaderFixer(inputStream, tns);
        InputSource inputSource = new InputSource(fixedReader);

        SAXParser saxParser = spf.newSAXParser();
        XMLReader reader = saxParser.getXMLReader();
        //todo: use xmlstream
        final MyXMLFilterImpl filter = new MyXMLFilterImpl();

        filter.setParent(reader);
        SAXSource saxSource = new SAXSource(filter, inputSource);
        Unmarshaller u = createUnmarshaller();
        u.setListener(new Unmarshaller.Listener() {
            @Override
            public void beforeUnmarshal(Object target, Object parent) {
                if (target instanceof AbstractElement) {
                ((AbstractElement)target).setQName(filter.getqName());
                }
            }
        });
        JAXBElement<T> jaxbElement = u.unmarshal(saxSource, clazz);
        return jaxbElement.getValue();
    }

    Unmarshaller createUnmarshaller() {
        try {
            Unmarshaller u = jc.createUnmarshaller();
            if (isValidating()) {
                u.setSchema(schema);
            }
            return u;
        } catch (JAXBException e) {
            throw new JAXBHelperException(e);
        }
    }

    public Marshaller createMarshaller() {
        try {
            Marshaller u = jc.createMarshaller();
            return u;
        } catch (JAXBException e) {
            throw new JAXBHelperException(e);
        }
    }


    Map<String, String> loadEpisodeFile() throws Exception {
        Map<String, String> map = new HashMap<>();
        InputStream in = getClass().getClassLoader().getResourceAsStream("META-INF/sun-jaxb.episode");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse(new InputSource(in));
        NodeList bindingList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < bindingList.getLength(); i++) {
            if (bindingList.item(i) instanceof Element) {
                Element binding = (Element) bindingList.item(i);
                String tns = binding.getAttributeNS("http://www.w3.org/2000/xmlns/", "tns");
                if (tns != null) {
                    Element schemaBinding = (Element) binding.getElementsByTagNameNS(binding.getNamespaceURI(), "schemaBindings").item(0);
                    Element packageElement = (Element) schemaBinding.getElementsByTagNameNS(binding.getNamespaceURI(), "package").item(0);
                    map.put(packageElement.getAttribute("name"), tns);
                }

            }
        }
        return Collections.unmodifiableMap(map);
    }

    private static class MyXMLFilterImpl extends XMLFilterImpl {

        private boolean ignore = false;
        private String prefix = null;

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            if ("http://xrebirth.egosoft.com/components".equals(uri)) {
                ignore = true;
                this.prefix = prefix;
            }
            getContentHandler().startPrefixMapping(prefix, uri);
        }

        @Override
        public void endPrefixMapping(String prefix) throws SAXException {
            if  (ignore && this.prefix.equals(prefix)) {
                ignore = false;
                prefix = null;
            }
            getContentHandler().endPrefixMapping(prefix);
            this.qName = null;
        }

        public void characters(char[] ch, int start, int length) throws SAXException {
            if (!ignore) {
                getContentHandler().characters(ch, start, length);
            }
        }

        QName qName;

        public QName getqName() {
            return qName;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
            this.qName = new QName(uri, localName, qName);
            getContentHandler().startElement(uri, localName, qName, atts);
        }
    }
}
