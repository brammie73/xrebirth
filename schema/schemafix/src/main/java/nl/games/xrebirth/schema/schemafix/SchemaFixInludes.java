package nl.games.xrebirth.schema.schemafix;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 2-1-14
 * Time: 23:41
 * To change this template use File | Settings | File Templates.
 */
public class SchemaFixInludes implements SchemaFix {

    public static String XMLNS = "xmlns";
    public static String TARGETNAMESPACE = "targetNamespace";


    private DocumentBuilderFactory dbf;

    private final Properties namespaces;

    public SchemaFixInludes(Properties properties) {
        this.dbf = DocumentBuilderFactory.newInstance();
        this.namespaces = properties;
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);

    }

    @Override
    public boolean execute(File in, File out) {
        Document schema = parseFile(in);
        Document fixedSchema = includeDoument(in, schema);
        fixedSchema = fixNamespace(in.getName(), fixedSchema);
        writeDocument(fixedSchema, out);
        return true;
    }

    private Document fixNamespace(String key, Document document) {
        String tns = namespaces.getProperty(key);
        if (tns != null) {
            Element root = document.getDocumentElement();
            //xmlns="http://test.com/test" targetNamespace="http://test.com/test"
            root.setAttribute(XMLNS, tns);
            root.setAttribute(TARGETNAMESPACE, tns);
        }

        return document;
    }


    protected File resolve(File file, String fileName) {
        File dir = file.getParentFile();
        URI ressultUri = dir.toURI().resolve(fileName);
        File result = new File(ressultUri);
        if (!result.exists()) {
            throw new SchemaFixException(String.format("include file not found: %s", fileName));
        }
        return result;
    }

    private Document includeDoument(File file, Document document) {
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if ("xs:include".equals(element.getTagName())) {
                    String schemaLocation = element.getAttribute("schemaLocation");
                    File includeFile = resolve(file, schemaLocation);
                    Document include = parseFile(includeFile);
                    includeDoument(element, include);
                }
            }
        }
        return document;
    }

    Document includeDoument(Element element, Document includeDocument) {
        Document document = element.getOwnerDocument();
        Comment start = document.createComment("start include of file");
        element.getParentNode().insertBefore(start, element);
        element.getParentNode().insertBefore(document.createTextNode("\n"), element);

        NodeList childNodes = includeDocument.getDocumentElement().getChildNodes();
        for (int j = 0; j < childNodes.getLength(); j++) {
            if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                Node newNode = document.importNode(childNodes.item(j), true);
                element.getParentNode().insertBefore(newNode, element);
            }
        }
        Comment end = document.createComment("end include of file");
        element.getParentNode().insertBefore(document.createTextNode("\n"), element);
        element.getParentNode().insertBefore(end, element);
        element.getParentNode().removeChild(element);
        return document;
    }


    Document parseFile(URI uri) {
        return parseFile(new File(uri));
    }

    private Document parseFile(File file) {
        try {
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            InputSource inputSource = new InputSource(new FileInputStream(file));
            return documentBuilder.parse(inputSource);
        } catch (ParserConfigurationException e) {
            throw new SchemaFixException("no valid parser found", e);
        } catch (SAXException e) {
            throw new SchemaFixException(String.format("%s is not a valid xml document", file.getName()), e);
        } catch (IOException e) {
            throw new SchemaFixException(String.format("%s not found", file.getName()), e);
        }
    }

    private void writeDocument(Document document, File out) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(new FileOutputStream(out));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new SchemaFixException(String.format("error writing to %s", out.getName()));
        } catch (IOException e) {
            throw new SchemaFixException(String.format("error writing to %s", out.getName()));
        }
    }
}
