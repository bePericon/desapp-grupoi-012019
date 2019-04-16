package model.account;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.account.EnumEstados.*;

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

    public void solicitarCredito() {
        this.agregarCredito(new Credito("1000", 6));
    }

    public void agregarMovimiento(EnumTipos.TipoMovimiento depositar, DateTime fecha, BigDecimal monto) {
        this.movimientos.add(new Movimiento(depositar, fecha, monto));
    }

    private void agregarCredito(Credito credito) {
        if(! this.hayCreditoEnCurso() && this.esUsuarioCumplidor()){
            this.creditos.add(credito);
            this.agregarMovimiento(EnumTipos.TipoMovimiento.DEPOSITAR, DateTime.now(), credito.getMontoTotal());
        }
    }

    public boolean esUsuarioCumplidor() {
        return this.getUsuario().getSituacion().esCumplidor();
    }

    public boolean hayCreditoEnCurso() {
        return (this.getCreditos().size() > 0) && (this.estadoUltimoCredito().equals(EstadoCredito.ENCURSO));
    }

    private EstadoCredito estadoUltimoCredito() {
        return this.getUltimoCredito().getEstado();
    }


    public void crearTemplate() {

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

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
