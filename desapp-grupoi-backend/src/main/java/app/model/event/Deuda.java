package app.model.event;

import app.model.account.Dinero;
import app.model.account.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "deuda")
public class Deuda {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Usuario deudor;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Dinero monto;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Usuario cobrador;

    public Deuda(Usuario deudor, Dinero monto) {
        this.deudor = deudor;
        this.monto = monto;
    }

    public Deuda(Dinero monto, Usuario cobrador) {
        this.monto = monto;
        this.cobrador = cobrador;
    }

    public Deuda(Usuario deudor, Dinero monto, Usuario cobrador) {
        this(monto,cobrador);
        this.deudor = deudor;
    }

    // Getters and Setters
    public Usuario getDeudor() {
        return deudor;
    }

    public void setDeudor(Usuario deudor) {
        this.deudor = deudor;
    }

    public Dinero getMonto() {
        return monto;
    }

    public void setMonto(Dinero monto) {
        this.monto = monto;
    }

    public Usuario getCobrador() {
        return cobrador;
    }

    public void setCobrador(Usuario cobrador) {
        this.cobrador = cobrador;
    }
}
