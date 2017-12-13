package exception;

public class qunbuException extends RuntimeException{ //异常类首先对RuntimeException进行继承，然后继承父类所有方法后暂时不做操作
    private static final long serialVersionUID = 6435296194529486206L;
    public qunbuException() {
        super();
    }

    public qunbuException(String message) {
        super(message);
    }

    public qunbuException(String message, Throwable cause) {
        super(message, cause);
    }

    public qunbuException(Throwable cause) {
        super(cause);
    }

    protected qunbuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
