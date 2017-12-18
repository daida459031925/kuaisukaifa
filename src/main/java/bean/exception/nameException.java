package bean.exception;

public class nameException extends RuntimeException{
    public nameException() {
        super();
    }

    public nameException(String message) {
        super(message);
    }

    public nameException(String message, Throwable cause) {
        super(message, cause);
    }

    public nameException(Throwable cause) {
        super(cause);
    }

    protected nameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
