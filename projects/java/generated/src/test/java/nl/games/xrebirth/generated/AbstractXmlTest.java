package nl.games.xrebirth.generated;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Properties;

/**
 * Author: bram
 * Date: 5-1-14
 * Time: 0:09
 */
public abstract class AbstractXmlTest<T> {


    private FileObject root;

    @BeforeClass
    public void setUp() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("/xrebirth.properties"));
        root = VFS.getManager().resolveFile("xr:/" + properties.getProperty("egosoft.xrebirth.dir"));
    }

    public FileObject getRoot() {
        return root;
    }

    public Class<T> getDeclaredClass() {
        return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public FileObject getFile() throws JAXBHelperException {
        try {
            FileObject fo = root.resolveFile(getFileName());
            if (fo == null) {
                throw new JAXBHelperException(String.format("file not found: %s",getFileName()));
            }
            return fo;
        } catch (FileSystemException e) {
            throw new JAXBHelperException(e);
        }
    }

    public abstract String getFileName();

    @Test
    public void testparseFile() throws Exception {
        JAXBHelper helper =  JAXBHelper.get();
        helper.setValidating(true);
        T result =  helper.unMarshall(getFile().getContent().getInputStream(), getDeclaredClass());
        assertNotNull(result);
        assertEquals(result.getClass(), getDeclaredClass());
    }
}
