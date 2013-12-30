package nl.games.xrebirth.neo4j.importer.reader;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:52
 * To change this template use File | Settings | File Templates.
 */
public class XmlReaderException extends ImportException {

    public XmlReaderException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public XmlReaderException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public XmlReaderException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public XmlReaderException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected XmlReaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
