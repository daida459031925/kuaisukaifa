package bean.exception;

public class shenfenzhengException extends RuntimeException{
    public shenfenzhengException() {
        super();
    }

    public shenfenzhengException(String message) {
        super(message);
    }

    public shenfenzhengException(String message, Throwable cause) {
        super(message, cause);
    }

    public shenfenzhengException(Throwable cause) {
        super(cause);
    }

    protected shenfenzhengException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
