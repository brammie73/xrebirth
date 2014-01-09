package nl.games.xrebirth.neo4j.importer.db;

import nl.games.xrebirth.neo4j.importer.ImportException;

/**
 * Author: bram
 * Date: 9-1-14
 * Time: 2:26
 */
public class ServiceException extends ImportException {

    public ServiceException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ServiceException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ServiceException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
