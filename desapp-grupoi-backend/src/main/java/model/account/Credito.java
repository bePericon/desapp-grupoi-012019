package model.account;

import model.account.EnumEstados.EstadoCredito;

public class Credito {

    private Dinero monto;
    private int cuotas;
    private Dinero montoRestante;
    private int cuotasRestantes;
    private EstadoCredito estado;

    public Credito() {
        this.estado = EstadoCredito.GUARDADO;
    }

    public Credito(Dinero monto, Dinero montoADevolver, int cuotas) {
        this();
        this.monto = monto;
        this.cuotas = cuotas;
        this.montoRestante = montoADevolver;
        this.cuotasRestantes = cuotas;
    }

    public Dinero getMontoCuota() {
        return new Dinero (this.montoRestante.getMonto()/ this.cuotasRestantes);
    }

    public void saldarMonto(Dinero cuota) {
        this.montoRestante.restar(cuota);
        this.cuotasRestantes -= 1;
        if(this.cuotasRestantes == 0)
            this.estado = EstadoCredito.FINALIZADO;
        else
            this.estado = EstadoCredito.ENCURSO;
    }


    // Getters and Setters

    public Dinero getMonto() {
        return this.monto;
    }

    public EstadoCredito getEstado() {
        return this.estado;
    }

    public Dinero getMontoRestante() {
        return this.montoRestante;
    }
}
