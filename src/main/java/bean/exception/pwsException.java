package bean.exception;

public class pwsException extends RuntimeException{
    public pwsException() {
        super();
    }

    public pwsException(String message) {
        super(message);
    }

    public pwsException(String message, Throwable cause) {
        super(message, cause);
    }

    public pwsException(Throwable cause) {
        super(cause);
    }

    protected pwsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
