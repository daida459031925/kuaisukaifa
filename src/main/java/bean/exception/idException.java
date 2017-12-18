package bean.exception;

public class idException extends RuntimeException{
    public idException() {
        super();
    }

    public idException(String message) {
        super(message);
    }

    public idException(String message, Throwable cause) {
        super(message, cause);
    }

    public idException(Throwable cause) {
        super(cause);
    }

    protected idException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
