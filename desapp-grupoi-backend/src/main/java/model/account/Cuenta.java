package model.account;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {

    private Usuario usuario;
    private List<Movimiento> movimientos = new ArrayList<Movimiento>();
    //    private Creditos creditos;
    private TarjetaCredito tarjetaCredito;
    private BigDecimal saldo;

    public Cuenta(){
        this.saldo = new BigDecimal(0);
    }

    public BigDecimal saldoActual() {
        return saldo;
    }

    public void depositarDinero(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
        this.agregarMovimiento(EnumTipos.TipoMovimiento.DEPOSITAR, DateTime.now(), monto);
    }

    public void retirarDinero(BigDecimal monto) {
        if(this.haySaldoSuficiente()) {
            this.saldo = this.saldo.subtract(monto);
            this.agregarMovimiento(EnumTipos.TipoMovimiento.RETIRAR, DateTime.now(), monto);
        }
        //TODO: manejo de excepciones
    }

    public boolean haySaldoSuficiente() {
        return this.saldo.compareTo(BigDecimal.ZERO) > 0;
    }

    public void setTarjetaCredito(String numero, int codigo) {
        this.tarjetaCredito = new TarjetaCredito(numero, codigo);
    }

    public TarjetaCredito getTarjetaCredito() {
        return this.tarjetaCredito;
    }

    public List<Movimiento> getMovimientos() {
        return this.movimientos;
    }

    private void agregarMovimiento(EnumTipos.TipoMovimiento depositar, DateTime fecha, BigDecimal monto) {
        this.movimientos.add(new Movimiento(depositar, fecha, monto));
    }

    public Movimiento ultimoMovimiento() {
        return this.movimientos.get(this.movimientos.size()-1);
    }
}
