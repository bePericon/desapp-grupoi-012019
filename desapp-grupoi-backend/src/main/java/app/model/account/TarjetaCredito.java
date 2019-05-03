package app.model.account;

import javax.persistence.*;

@Entity
@Table(name = "tarjetacredito")
public class TarjetaCredito {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

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
