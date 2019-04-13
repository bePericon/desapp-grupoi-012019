package model.account;

public class TarjetaCredito {

    private String numeroTarjeta;
    private int codigoSeguridad;

    public TarjetaCredito(String numero, int codigo) {
        this.numeroTarjeta = numero;
        this.codigoSeguridad = codigo;
    }

    public String getNumero() {
        return this.numeroTarjeta;
    }

    public int getCodigo() {
        return this.codigoSeguridad;
    }
}
