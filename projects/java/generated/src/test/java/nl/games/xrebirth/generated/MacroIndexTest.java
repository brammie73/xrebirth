package nl.games.xrebirth.generated;

import nl.games.xrebirth.generated.index.Entry;
import nl.games.xrebirth.generated.index.Index;
import nl.games.xrebirth.generated.macros.MacrosType;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileType;
import org.testng.annotations.Test;

import javax.xml.bind.UnmarshalException;
import java.nio.file.FileSystemException;

import static org.testng.Assert.assertNotNull;


/**
 * Author: bram
 * Date: 7-1-14
 * Time: 0:32
 */
public class MacroIndexTest extends AbstractXmlTest<Index> {


    @Override
    public String getFileName() {
        return "index/macros.xml";
    }

    @Test
    public void testComponents() throws Exception {
        Index index = testparseFile();
        for (Entry entry : index.getEntry()) {
            JAXBHelper helper =  JAXBHelper.get();
            helper.setValidating(validating);
            if (entry.getValue().endsWith("fog_test_v1_macro")) {
                continue;
            }
            FileObject fo = getRoot().resolveFile(entry.getValue() + ".xml");
            if (fo != null && (fo.getContent() != null) && fo.getType() == FileType.FILE && (fo.getContent().getSize() > 2) ) {
                try {
                    MacrosType result =  helper.unMarshall(fo.getContent().getInputStream(), MacrosType.class);
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
