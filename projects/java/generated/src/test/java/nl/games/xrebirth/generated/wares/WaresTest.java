package nl.games.xrebirth.generated.wares;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.VFS;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * Author: bram
 * Date: 4-1-14
 * Time: 4:28
 */
public class WaresTest {

    FileObject root;

    @BeforeClass
    public void setUp() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("/xrebirth.properties"));
        root = VFS.getManager().resolveFile("xr:/" + properties.getProperty("egosoft.xrebirth.dir"));
    }


    @Test
    public void testWares() throws Exception {
        FileObject waresFO = root.resolveFile("libraries/wares.xml");


    }

}
