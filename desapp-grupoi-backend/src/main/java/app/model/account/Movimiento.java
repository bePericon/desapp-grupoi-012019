package app.model.account;

import app.model.account.EnumTipos.TipoMovimiento;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movimiento")
public class Movimiento {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @Column(name = "fechaNac")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @OneToOne(cascade=CascadeType.ALL)
    private Dinero monto;

    public Movimiento() {
    }

    public Movimiento(TipoMovimiento tipo, Date fecha, Dinero monto) {
        this.tipoMovimiento = tipo;
        this.fecha = fecha;
        this.monto = monto;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }
}
