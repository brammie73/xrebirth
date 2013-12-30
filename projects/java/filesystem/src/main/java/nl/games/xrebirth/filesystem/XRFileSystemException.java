package nl.games.xrebirth.filesystem;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 30-12-13
 * Time: 4:34
 * To change this template use File | Settings | File Templates.
 */
public class XRFileSystemException extends RuntimeException {
    public XRFileSystemException() {
        super();
    }

    public XRFileSystemException(String message) {
        super(message);
    }

    public XRFileSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public XRFileSystemException(Throwable cause) {
        super(cause);
    }

    protected XRFileSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
