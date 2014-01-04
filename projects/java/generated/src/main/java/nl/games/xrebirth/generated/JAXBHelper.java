package nl.games.xrebirth.generated;

import com.google.common.base.Joiner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static JAXBContext jc;

    private boolean validating = false;
    private final Schema schema;



    public static JAXBHelper get() {
        return THREAD_LOCAL.get();
    }

    private JAXBHelper() {
        try {
            this.filtermap = loadEpisodeFile();
            this.schema  = loadSchema();
            Object[] arr = filtermap.keySet().toArray();
            String joined = Joiner.on(":").skipNulls().join(arr);
            jc = JAXBContext.newInstance(joined);
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
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        InputSource inputSource = new InputSource(fixedReader);
        SAXSource saxSource = new SAXSource(saxParser.getXMLReader(), inputSource);
        Unmarshaller u = jc.createUnmarshaller();
        if (isValidating()) {
            u.setSchema(schema);
        }
        JAXBElement<T> jaxbElement = u.unmarshal(saxSource, clazz);

        //clean up the filter
        return jaxbElement.getValue();
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

    static class InputReaderFixer extends Reader {

        private BufferedReader reader;
        private final String namespace;


        public InputReaderFixer(Reader in, String namespace) {
            this.reader = new BufferedReader(in);
            this.namespace = String.format(" xmlns=\"%s\" ", namespace);
        }

        public InputReaderFixer(InputStream in, String namespace) {
            this(new InputStreamReader(in), namespace);
        }


        private Pattern pattern = Pattern.compile("<\\w* ");
        private char[] buff = new char[8192];
        private int position = 0;
        private boolean done = false;
        private int result;

        @Override
        public void close() throws IOException {
            reader.close();
            buff = new char[8192];
            position = 0;
            done = false;
            result = -1;
        }

        @Override
        public int read(char[] outArray, int off, int len) throws IOException {
            if (done) {
                return reader.read(outArray, off, len);
            } else if (position > 0) {
                if (position + len > buff.length) {
                    System.arraycopy(buff, position, outArray, off, buff.length - position);
                    done = true;
                    return result - position;
                } else {
                    System.arraycopy(buff, position, outArray, off, len);
                    position = position + len;
                    return len;
                }
            } else {
                int maxRead = buff.length - namespace.length();
                result = reader.read(buff, 0, maxRead);
                if (result < 0) {
                    return result;
                }
                if (appendXmlNs(maxRead)) {
                    result = result + namespace.length();
                }

                System.arraycopy(buff, position, outArray, off, len);
                if (len < result) {
                    position = position + len;
                    return len;
                }
                done = true; //one time
                return result;
            }
        }

        private boolean appendXmlNs(int maxRead) {
            StringBuffer stringBuffer = new StringBuffer(8192);
            stringBuffer.append(buff, 0, maxRead);
            Matcher m = pattern.matcher(stringBuffer.toString());
            if (m.find()) {
                int idx = m.end();
                stringBuffer.insert(idx, namespace);
                stringBuffer.getChars(0, stringBuffer.length(), buff, 0);
                return true;
            }
            return false;
        }

        @Override
        public boolean markSupported() {
            return false;
        }
    }
}
