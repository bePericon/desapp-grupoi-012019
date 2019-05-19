package app.error;

import app.error.exception.ExceptionConflict;
import app.error.exception.ExceptionNoContent;
import app.error.exception.ExceptionNotFound;
import app.error.exception.ExceptionNotAcceptable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ExceptionNotFound.class)
    public ResponseEntity<ErrorInfo> exceptionNotFound(HttpServletRequest request, ExceptionNotFound e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExceptionNotAcceptable.class)
    public ResponseEntity<ErrorInfo> exceptionNotAcceptable(HttpServletRequest request, ExceptionNotAcceptable e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ExceptionConflict.class)
    public ResponseEntity<ErrorInfo> exceptionConflict(HttpServletRequest request, ExceptionNotAcceptable e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.CONFLICT.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExceptionNoContent.class)
    public ResponseEntity<ErrorInfo> exceptionNoContent(HttpServletRequest request, ExceptionNoContent e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NO_CONTENT.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.NO_CONTENT);
    }
}