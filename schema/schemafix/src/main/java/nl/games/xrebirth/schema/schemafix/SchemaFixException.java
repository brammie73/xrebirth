package nl.games.xrebirth.schema.schemafix;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 3-1-14
 * Time: 0:14
 * To change this template use File | Settings | File Templates.
 */
public class SchemaFixException extends RuntimeException {

    public SchemaFixException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public SchemaFixException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public SchemaFixException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public SchemaFixException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected SchemaFixException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
