package nl.games.xrebirth.neo4j;

/**
 * Author: bram
 * Date: 9-1-14
 * Time: 0:21
 */
public class Neo4jException extends RuntimeException {

    public Neo4jException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public Neo4jException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public Neo4jException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public Neo4jException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected Neo4jException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
