package nl.games.xrebirth.generated;

import nl.games.xrebirth.generated.quotas.Componentoffset;
import nl.games.xrebirth.generated.text.Language;
import org.testng.annotations.Test;

import javax.xml.bind.UnmarshalException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.testng.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 3-1-14
 * Time: 3:08
 * To change this template use File | Settings | File Templates.
 */
public class JAXBHelperTest {

    @Test
    public void testLoadEpisode() throws Exception {
        JAXBHelper helper = JAXBHelper.get();
        assertNotNull(helper.getFilter(Componentoffset.class));
    }


    @Test
    public void testUnMarshall() throws Exception {
        JAXBHelper helper = JAXBHelper.get();
        try {
            helper.unMarshall(null, Componentoffset.class);
            fail("should  throw exception");
        } catch (NullPointerException e) {
        }
        InputStream in = new ByteArrayInputStream("<componentoffset object=\"object\" comment=\"comment\" />".getBytes());
        Componentoffset componentoffset = helper.unMarshall(in, Componentoffset.class);
        assertEquals("object", componentoffset.getObject());
        assertEquals("comment", componentoffset.getComment());
        in = new ByteArrayInputStream("<componentoffset object=\"second\" comment=\"second\" />".getBytes());
        componentoffset = helper.unMarshall(in, Componentoffset.class);
        assertEquals("second", componentoffset.getObject());
        assertEquals("second", componentoffset.getComment());
    }

    @Test(expectedExceptions = UnmarshalException.class)
    public void testValidateFail() throws Exception{
        JAXBHelper helper = JAXBHelper.get();
        try {
            helper.setValidating(true);
            InputStream in = new ByteArrayInputStream("<componentoffset hoepla=\"object\" comment=\"comment\" />".getBytes());
            Componentoffset componentoffset = helper.unMarshall(in, Componentoffset.class);
        } finally {
            helper.setValidating(false);
        }
    }

    @Test()
    public void testValidatePass() throws Exception{
        JAXBHelper helper = JAXBHelper.get();
        try {
            helper.setValidating(true);
            InputStream in = new ByteArrayInputStream("<language id=\"1\" />".getBytes());
            Language language = helper.unMarshall(in, Language.class);
        } finally {
            helper.setValidating(false);
        }
    }

    @Test()
    public void testScriptproperties() throws Exception{
        JAXBHelper helper = JAXBHelper.get();
        try {
            helper.setValidating(true);
            InputStream in = new ByteArrayInputStream("<language id=\"1\" />".getBytes());
            Language language = helper.unMarshall(in, Language.class);
        } finally {
            helper.setValidating(false);
        }
    }


}
