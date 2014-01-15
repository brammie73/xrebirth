package nl.games.xrebirth.generated;

import nl.games.xrebirth.generated.components.ComponentsType;
import nl.games.xrebirth.generated.index.Entry;
import nl.games.xrebirth.generated.index.Index;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import javax.xml.bind.UnmarshalException;
import java.nio.file.FileSystemException;

import static org.testng.Assert.assertNotNull;


/**
 * Author: bram
 * Date: 7-1-14
 * Time: 0:32
 */
public class ComponentIndexTest extends AbstractXmlTest<Index> {

    Logger log = LogManager.getLogger();

    @Override
    public String getFileName() {
        return "index/components.xml";
    }

    @Test
    public void testComponents() throws Exception {
        Index index = testparseFile();
        for (Entry entry : index.getEntry()) {
            JAXBHelper helper =  JAXBHelper.get();
            helper.setValidating(validating);
            String file = entry.getValue();
            if (file.contains("assets\\test")) {
                continue;
            }
            file = file.replace("!", "%21");
            file = file.replace("\\\\", "\\");
            FileObject fo = getRoot().resolveFile(file  + ".xml");
            if (fo != null && (fo.getContent() != null) && fo.getType() == FileType.FILE && (fo.getContent().getSize() > 2) ) {
                try {
                    ComponentsType result =  helper.unMarshall(fo.getContent().getInputStream(), ComponentsType.class);
                    if  (result != null) {
                        assertNotNull(result.toString());
                    }
                    assertNotNull(result);
                } catch (FileSystemException e) {
                    //ignore
                } catch (UnmarshalException e) {

                    throw new UnmarshalException(entry.getValue(),  e);
                }
            }
        }

    }
}
