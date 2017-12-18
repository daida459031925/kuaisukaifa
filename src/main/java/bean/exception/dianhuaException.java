package bean.exception;

public class dianhuaException extends RuntimeException{
    public dianhuaException() {
        super();
    }

    public dianhuaException(String message) {
        super(message);
    }

    public dianhuaException(String message, Throwable cause) {
        super(message, cause);
    }

    public dianhuaException(Throwable cause) {
        super(cause);
    }

    protected dianhuaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
