package app.error.exception;

public class ExceptionUsuarioNoEncontrado extends RuntimeException {
    public ExceptionUsuarioNoEncontrado() {
        super("El usuario no fue encontrado.");
    }
}
