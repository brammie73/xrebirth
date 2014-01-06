package nl.games.xrebirth.neo4j.importer;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */
public class ImportException extends RuntimeException {

    public ImportException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ImportException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ImportException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ImportException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected ImportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
