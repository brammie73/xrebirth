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

            @Override
            public void accept(PropertyVisitor aVisitor, String name) {

            }

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

        assertEquals(Arrays.asList("clazz"), elm.classNames());
        assertEquals(Arrays.asList("tag", "tag"), elm.tagList());
    }

    public void testBeansnotImplementing() {
        AbstractElement elm = new AbstractElement() {
            @Override
            public void accept(PropertyVisitor aVisitor, String name) {

            }
        };

        assertEquals(null, elm.classNames());
        assertEquals(null, elm.tagList());
    }

    public void testSomeInstance() {
        Language elm = new Language();
        elm.setId(123);
        assertEquals(null, elm.classNames());
        assertEquals(null, elm.tagList());
    }

}
