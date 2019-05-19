package app.error.exception;

public class ExceptionBadRequest extends RuntimeException {
    public ExceptionBadRequest(String message) {
        super(message);
    }
}