package nl.games.xrebirth.generated.text;

import com.google.common.io.CharStreams;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.VFS;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 30-12-13
 * Time: 20:37
 * To change this template use File | Settings | File Templates.
 */
public class LanguageTest {

    FileObject root;

    @BeforeTest
    public void oneTimeSetUp() throws Exception {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("/xrebirth.properties"));
        root = VFS.getManager().resolveFile("xr:/" + properties.getProperty("egosoft.xrebirth.dir"));


        //XMLUnit.setControlParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
        //XMLUnit.setTestParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
        //XMLUnit.setSAXParserFactory("org.apache.xerces.jaxp.SAXParserFactoryImpl");
        //XMLUnit.setIgnoreWhitespace(true);

    }

    @AfterTest
    public void oneTimeTearDown() throws Exception {
        VFS.getManager().closeFileSystem(root.getFileSystem());
    }

    @Test
    public void testRoundTrip() throws Exception {
        FileObject textFo = root.getChild("t").getChild("0001-l044.xml");
        String unmoodifiedContent = CharStreams.toString(new InputStreamReader(textFo.getContent().getInputStream()));
        JAXBContext jc = JAXBContext.newInstance(Language.class.getPackage().getName());
        Unmarshaller u = jc.createUnmarshaller();
        JAXBElement<Language> element  = (JAXBElement<Language>)u.unmarshal(textFo.getContent().getInputStream());
        Language lang  =element.getValue();
        assertNotNull(lang);
        assertNotNull(lang.getPage());
        assertFalse(lang.getPage().isEmpty());
        Marshaller marshaller = JAXBContext.newInstance(Language.class.getPackage().getName()).createMarshaller();
        StringWriter writer = new StringWriter();
        ObjectFactory of = new ObjectFactory();
        marshaller.marshal(of.createLanguage(lang),writer);
        String out = writer.toString();
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(unmoodifiedContent, out);

    }
}
