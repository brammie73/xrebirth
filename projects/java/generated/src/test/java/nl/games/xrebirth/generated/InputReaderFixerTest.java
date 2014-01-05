package nl.games.xrebirth.generated;

import com.google.common.io.LineReader;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.io.StringReader;

/**
 * Author: bram
 * Date: 5-1-14
 * Time: 0:51
 */
public class InputReaderFixerTest {

    @Test
    public void testTagSpace() throws Exception {
        String input = "<bla />\n";
        StringReader r = new StringReader(input);
        InputReaderFixer  fixer = new InputReaderFixer(r, "blans");
        LineReader lineReader =  new LineReader(fixer);
        String out = lineReader.readLine();
        assertEquals("<bla xmlns=\"blans\" />", out);

    }

    @Test
    public void testTagEmpty() throws Exception {
        String input = "<bla/>\n";
        StringReader r = new StringReader(input);
        InputReaderFixer  fixer = new InputReaderFixer(r, "blans");
        LineReader lineReader =  new LineReader(fixer);
        String out = lineReader.readLine();
        assertEquals("<bla xmlns=\"blans\"/>", out);
    }

    @Test
    public void testTagStart() throws Exception {
        String input = "<bla>\n";
        StringReader r = new StringReader(input);
        InputReaderFixer  fixer = new InputReaderFixer(r, "blans");
        LineReader lineReader =  new LineReader(fixer);
        String out = lineReader.readLine();
        assertEquals("<bla xmlns=\"blans\">", out);
    }

    @Test
    public void testTagStartAttribute() throws Exception {
        String input = "<bla id=\"1\">\n";
        StringReader r = new StringReader(input);
        InputReaderFixer  fixer = new InputReaderFixer(r, "blans");
        LineReader lineReader =  new LineReader(fixer);
        String out = lineReader.readLine();
        assertEquals("<bla xmlns=\"blans\" id=\"1\">", out);
    }

    @Test
    public void testProlog() throws Exception {
        String input = "<? this is a prolog ?><bla id=\"1\">\n";
        StringReader r = new StringReader(input);
        InputReaderFixer  fixer = new InputReaderFixer(r, "blans");
        LineReader lineReader =  new LineReader(fixer);
        String out = lineReader.readLine();
        assertEquals("<? this is a prolog ?><bla xmlns=\"blans\" id=\"1\">", out);
    }

    @Test
    public void testPrologComment() throws Exception {
        String input = "<? this is a prolog ?><!-- --><bla id=\"1\">\n";
        StringReader r = new StringReader(input);
        InputReaderFixer  fixer = new InputReaderFixer(r, "blans");
        LineReader lineReader =  new LineReader(fixer);
        String out = lineReader.readLine();
        assertEquals("<? this is a prolog ?><!-- --><bla xmlns=\"blans\" id=\"1\">", out);
    }

}
