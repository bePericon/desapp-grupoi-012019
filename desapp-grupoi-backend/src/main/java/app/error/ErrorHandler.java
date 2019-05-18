package app.error;

import app.error.exception.ExceptionUsuarioNoEncontrado;
import app.error.exception.ExceptionUsuarioSaldoInsuficiente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ExceptionUsuarioNoEncontrado.class)
    public ResponseEntity<ErrorInfo> exceptionUsuarioNoEncontrado(HttpServletRequest request, ExceptionUsuarioNoEncontrado e) {
        // return error info object with standard json
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExceptionUsuarioSaldoInsuficiente.class)
    public ResponseEntity<ErrorInfo> exceptionUsuarioSaldoInsuficiente(HttpServletRequest request, ExceptionUsuarioSaldoInsuficiente e) {
        // return error info object with standard json
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_ACCEPTABLE);
    }

}