package app.error.exception;

public class ExceptionUsuarioSaldoInsuficiente extends RuntimeException {
    public ExceptionUsuarioSaldoInsuficiente() {
        super("Saldo insuficiente.");
    }
}
