package app.model.web;

import app.model.account.Dinero;

public class NewMovimiento {

    private int codigoSeguridad;
    private Dinero monto;

    public NewMovimiento() {
    }

    public NewMovimiento(int codigoSeguridad, Dinero dinero) {
        this.codigoSeguridad = codigoSeguridad;
        this.monto = dinero;
    }

    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public Dinero getMonto() {
        return monto;
    }

    public void setMonto(Dinero monto) {
        this.monto = monto;
    }
}
