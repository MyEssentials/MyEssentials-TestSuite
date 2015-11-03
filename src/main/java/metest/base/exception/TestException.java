package metest.base.exception;

public class TestException extends RuntimeException {

    public TestException(String message) {
        super(message);
    }

    public TestException(String message, Throwable cause) {
        this(message);
        initCause(cause);
    }
}
