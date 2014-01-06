package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.neo4j.importer.ImportException;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:52
 * To change this template use File | Settings | File Templates.
 */
public class Neo4jWriterException extends ImportException {

    public Neo4jWriterException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public Neo4jWriterException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public Neo4jWriterException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public Neo4jWriterException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected Neo4jWriterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
