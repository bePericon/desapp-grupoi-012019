package model.event;

import model.account.Dinero;
import model.account.Usuario;

public class Deuda {

    private Usuario deudor;
    private Dinero monto;
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
