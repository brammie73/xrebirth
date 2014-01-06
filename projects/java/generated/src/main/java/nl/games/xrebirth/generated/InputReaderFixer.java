package nl.games.xrebirth.generated;

import org.apache.commons.io.input.XmlStreamReader;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* Author: bram
* Date: 5-1-14
* Time: 0:51
*/
class InputReaderFixer extends Reader {

    private BufferedReader reader;
    private final String namespace;


    public InputReaderFixer(Reader in, String namespace)  throws IOException {
        this.reader = new BufferedReader(in);
        this.namespace = String.format(" xmlns=\"%s\"", namespace);
    }

    public InputReaderFixer(InputStream in, String namespace) throws IOException {
        this(new XmlStreamReader(in), namespace);
    }


    private Pattern pattern = Pattern.compile("(<\\w++)");
    private char[] buff = new char[8192];
    private int position = 0;
    private boolean done = false;
    private int result;

    @Override
    public void close() throws IOException {
        reader.close();
        buff = new char[8192];
        position = 0;
        done = false;
        result = -1;
    }

    @Override
    public int read(char[] outArray, int off, int len) throws IOException {
        if (done) {
            return reader.read(outArray, off, len);
        } else if (position > 0) {
            if (position + len > buff.length) {
                System.arraycopy(buff, position, outArray, off, buff.length - position);
                done = true;
                return result - position;
            } else {
                System.arraycopy(buff, position, outArray, off, len);
                position = position + len;
                return len;
            }
        } else {
            int maxRead = buff.length - namespace.length();
            result = reader.read(buff, 0, maxRead);
            if (result < 0) {
                return result;
            }
            if (appendXmlNs(maxRead)) {
                result = result + namespace.length();
            }
            System.arraycopy(buff, position, outArray, off, len);
            //System.err.println(new String(buff, 0, 500));
            if (len < result) {
                position = position + len;
                return len;
            }
            done = true; //one time
            return result;
        }
    }

    private boolean appendXmlNs(int maxRead) {
        StringBuffer stringBuffer = new StringBuffer(8192);
        stringBuffer.append(buff, 0, maxRead);
        Matcher m = pattern.matcher(stringBuffer.toString());
        if (m.find()) {
            int idx = m.end();
            stringBuffer.insert(idx, namespace);
            stringBuffer.getChars(0, stringBuffer.length(), buff, 0);
            return true;
        }
        return false;
    }

    @Override
    public boolean markSupported() {
        return false;
    }
}
