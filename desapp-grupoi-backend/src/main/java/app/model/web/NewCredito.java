package app.model.web;

import app.model.account.Dinero;

public class NewCredito {

    private Dinero monto;
    private int cuotas;
    private Dinero montoADevolver;

    public NewCredito(Dinero monto, int cuotas, Dinero montoADevolver) {
        this.monto = monto;
        this.cuotas = cuotas;
        this.montoADevolver = montoADevolver;
    }

    public Dinero getMonto() {
        return monto;
    }

    public void setMonto(Dinero monto) {
        this.monto = monto;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public Dinero getMontoADevolver() {
        return montoADevolver;
    }

    public void setMontoADevolver(Dinero montoADevolver) {
        this.montoADevolver = montoADevolver;
    }
}
