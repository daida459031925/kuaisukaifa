package bean.exception;

public class xingbeiException extends RuntimeException{
    public xingbeiException() {
        super();
    }

    public xingbeiException(String message) {
        super(message);
    }

    public xingbeiException(String message, Throwable cause) {
        super(message, cause);
    }

    public xingbeiException(Throwable cause) {
        super(cause);
    }

    protected xingbeiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
