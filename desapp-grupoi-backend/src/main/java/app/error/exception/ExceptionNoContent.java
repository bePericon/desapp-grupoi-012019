package app.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ExceptionNoContent extends RuntimeException {
    public ExceptionNoContent(String message) {
        super(message);
    }
}
