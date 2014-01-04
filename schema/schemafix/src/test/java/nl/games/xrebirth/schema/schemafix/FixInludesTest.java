package nl.games.xrebirth.schema.schemafix;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 3-1-14
 * Time: 0:45
 * To change this template use File | Settings | File Templates.
 */
public class FixInludesTest {


    @Test
    public void testIncludeNameSpaces() throws Exception {
        SchemaFixInludes fixInludes = new SchemaFixInludes(null);
        URL t = getClass().getClassLoader().getResource(".");
        URL schemaUri = getClass().getClassLoader().getResource("test.xsd");
        URL includeUri = getClass().getClassLoader().getResource("include.xsd");
        Document schema = fixInludes.parseFile(schemaUri.toURI());
        Element element = (Element)schema.getDocumentElement().getElementsByTagNameNS("http://www.w3.org/2001/XMLSchema", "include").item(0);
        Document include = fixInludes.parseFile(includeUri.toURI());
        fixInludes.includeDoument(element, include);
        String xml = serialize(schema);
        assertTrue(xml.contains("jxb"));
    }

    private String serialize(Document doc) throws Exception {
        StringWriter sw = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
        return sw.toString();

    }

}
