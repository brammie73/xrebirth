package nl.games.xrebirth.generated;

import nl.games.xrebirth.generated.text.Language;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 5:31
 */

@Test
public class AbstractElementTest {

    public void testBeansImplementing() {
        AbstractElement elm = new AbstractElement() {
            public String getId() {
                return "id";
            }
            public List<String> getTags() {
                return Arrays.asList("tag", "tag");
            }
            public String getClazz() {
                return "clazz";
            }
        };

        assertEquals("id", elm.getIdString());
        assertEquals(Arrays.asList("clazz"), elm.getClassNames());
        assertEquals(Arrays.asList("tag", "tag"), elm.getTagList());
    }

    public void testBeansnotImplementing() {
        AbstractElement elm = new AbstractElement() {
        };

        assertEquals(null, elm.getIdString());
        assertEquals(null, elm.getClassNames());
        assertEquals(null, elm.getTagList());
    }

    public void testSomeInstance() {
        Language elm = new Language();
        elm.setId(123);
        assertEquals(String.valueOf(123), elm.getIdString());
        assertEquals(null, elm.getClassNames());
        assertEquals(null, elm.getTagList());
    }

}
