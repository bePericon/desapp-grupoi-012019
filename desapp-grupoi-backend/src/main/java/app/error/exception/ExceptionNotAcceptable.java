package app.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ExceptionNotAcceptable extends RuntimeException {
    public ExceptionNotAcceptable(String message) {
        super(message);
    }
}
