package nl.games.xrebirth.filesystem;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 30-12-13
 * Time: 4:22
 * To change this template use File | Settings | File Templates.
 */
public class XRFileInfoTest {

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testParseLineFail1() {
        XRFileInfo info = new XRFileInfo(null, null, "", 0l);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testParseLineFail2() {
        XRFileInfo info = new XRFileInfo(null, null, null, 0l);
    }

    @Test
    public void testParseLine() {
        XRFileInfo info = new XRFileInfo(null, null, "assets/characters/argon/textures/ar_m_body_04d_diff-small.gz 15458 1380272061 f3895afde4d555f0856da97cfe722cca", 0l);
        assertEquals(info.getLength(), 15458);
        assertEquals(info.getEpoch(), 1380272061);
        assertEquals(info.getPath(), "assets/characters/argon/textures/ar_m_body_04d_diff-small.gz");
        assertEquals(info.getHash(), "f3895afde4d555f0856da97cfe722cca");
    }

}
