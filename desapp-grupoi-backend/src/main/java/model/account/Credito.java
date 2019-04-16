package model.account;

import java.math.BigDecimal;
import model.account.EnumEstados.EstadoCredito;

public class Credito {

    private BigDecimal montoTotal;
    private int cuotasRestantes;
    private EstadoCredito estado;

    public Credito() {
    }

    public Credito(String monto, int cuotas) {
        this.montoTotal = Dinero.getMonto(monto);
        this.cuotasRestantes = cuotas;
        this.estado = EstadoCredito.GUARDADO;
    }

    public BigDecimal getMontoTotal() {
        return this.montoTotal;
    }

    public EstadoCredito getEstado() {
        return this.estado;
    }
}
