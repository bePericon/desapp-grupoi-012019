package model.account;

import java.math.BigDecimal;

public class Cuenta {

    private Usuario usuario;
//    private Movimiento movimientos;
//    private Creditos creditos;
    private TarjetaCredito fechaNac;
    private BigDecimal saldo;

    public Cuenta(){
        this.saldo = new BigDecimal(0);
    }

    public BigDecimal saldoActual() {
        return saldo;
    }

    public void depositarDinero(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
    }
}
