package model.account;

import java.math.BigDecimal;

public class Credito {

    private BigDecimal montoTotal;
    private int cuotasRestantes;

    public Credito() {
    }

    public Credito(String monto, int cuotas) {
        this.montoTotal = Dinero.getMonto(monto);
        this.cuotasRestantes = cuotas;
    }

    public BigDecimal getMontoTotal() {
        return this.montoTotal;
    }
}
