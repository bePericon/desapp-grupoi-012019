package model.account;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {

    private Usuario usuario;
    private List<Movimiento> movimientos = new ArrayList<Movimiento>();
    private List<Credito> creditos = new ArrayList<Credito>();
    private TarjetaCredito tarjetaCredito;
    private BigDecimal saldo;

    public Cuenta(){
        this.saldo = Dinero.getMonto(0);
    }

    public void depositarDinero(BigDecimal monto) {
        this.saldo = Dinero.sumar(this.saldo, monto);
        this.agregarMovimiento(EnumTipos.TipoMovimiento.DEPOSITAR, DateTime.now(), monto);
    }

    public void retirarDinero(BigDecimal monto) {
        if(this.haySaldoSuficiente()) {
            this.saldo = Dinero.restar(this.saldo, monto);
            this.agregarMovimiento(EnumTipos.TipoMovimiento.RETIRAR, DateTime.now(), monto);
        }
        //TODO: manejo de excepciones
    }

    public boolean haySaldoSuficiente() {
        return Dinero.mayorACero(this.saldo);
    }

    public void agregarMovimiento(EnumTipos.TipoMovimiento depositar, DateTime fecha, BigDecimal monto) {
        this.movimientos.add(new Movimiento(depositar, fecha, monto));
    }

    public void agregarCredito(Credito credito) {
        this.creditos.add(credito);
    }

    // Getters and Setters

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Movimiento getUltimoMovimiento() {
        return this.movimientos.get(this.movimientos.size()-1);
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

    public List<Credito> getCreditos() {
        return this.creditos;
    }

    public Credito getUltimoCredito() {
        return this.creditos.get(this.creditos.size()-1);
    }
}
