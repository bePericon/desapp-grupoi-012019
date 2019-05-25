package app.model.account;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tarjetacredito")
public class TarjetaCredito {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String numeroTarjeta;
    @Column
    private int codigoSeguridad;

    public TarjetaCredito(){}

    public TarjetaCredito(String numeroTarjeta, int codigoSeguridad) {
        this.numeroTarjeta = numeroTarjeta;
        this.codigoSeguridad = codigoSeguridad;
    }
}
