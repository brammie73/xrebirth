package nl.games.xrebirth.filesystem;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.VFS;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import static org.testng.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 30-12-13
 * Time: 4:48
 * To change this template use File | Settings | File Templates.
 */
public class XRFileSystemTest {


    @Test
    public void testFileSystem()  throws Exception {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("/xrebirth.properties"));
        FileObject fo = VFS.getManager().resolveFile("xr:/" + properties.getProperty("egosoft.xrebirth.dir"));
        assertNotNull(fo);
        FileObject[] children = fo.getChildren();
        assertNotNull(children);
        assertTrue(children.length > 0);
        FileObject common = fo.resolveFile("/libraries/common.xsd");
        assertNotNull(common);
        BufferedReader in = new BufferedReader(new InputStreamReader(common.getContent().getInputStream()));
        String line = in.readLine();
        assertEquals(line, "<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?>");
        in.close();
        VFS.getManager().closeFileSystem(fo.getFileSystem());
    }

    @Test
    public void testFile() throws Exception {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("/xrebirth.properties"));
        FileObject fo = VFS.getManager().resolveFile("xr:/" + properties.getProperty("egosoft.xrebirth.dir"));
        FileObject xmlFO = fo.resolveFile("libraries/region_definitions.xml");
        byte[] arr = new byte[(int)xmlFO.getContent().getSize()];
        InputStream in = xmlFO.getContent().getInputStream();
        int read = in.read(arr);
        for (int i = 0; i < 5 ; i++) {
            System.err.println(String.format("%x", arr[i]));
        }
    }
}


