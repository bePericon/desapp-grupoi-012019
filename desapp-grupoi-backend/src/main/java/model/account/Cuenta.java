package model.account;

import org.joda.time.DateTime;
import java.util.ArrayList;
import java.util.List;

import model.account.EnumEstados.*;

public class Cuenta {

    private Usuario usuario; 
    private List<Movimiento> movimientos = new ArrayList<Movimiento>();
    private List<Credito> creditos = new ArrayList<Credito>();
    private TarjetaCredito tarjetaCredito;
    private Dinero saldo;
    private EstadoSituacionDeuda situacionDeuda;

    public Cuenta(){
        this.saldo = new Dinero(0);
        this.situacionDeuda = EstadoSituacionDeuda.NORMAL;
    }

    public void depositarDinero(Dinero monto) {
        this.saldo.sumar(monto);
        this.agregarMovimiento(EnumTipos.TipoMovimiento.DEPOSITAR, DateTime.now(), monto);
    }

    public void retirarDinero(Dinero monto) {
        if(this.haySaldoSuficiente(monto)) {
            this.saldo.restar(monto);
            this.agregarMovimiento(EnumTipos.TipoMovimiento.RETIRAR, DateTime.now(), monto);
        }
        //TODO: manejo de excepciones
    }

    public boolean haySaldoSuficiente(Dinero monto) {
        return this.saldo.mayorACero() && this.saldo.mayorA(monto);
    }

    public void solicitarCredito() {
        this.agregarCredito(new Credito(new Dinero(1000), new Dinero(1200), 6));
    }

    public void agregarMovimiento(EnumTipos.TipoMovimiento depositar, DateTime fecha, Dinero monto) {
        this.movimientos.add(new Movimiento(depositar, fecha, monto));
    }

    private void agregarCredito(Credito credito) {
        if(! this.hayCreditoEnCurso() && this.esUsuarioCumplidor()){
            this.creditos.add(credito);
            this.saldo.sumar(credito.getMontoTotal());
            this.agregarMovimiento(EnumTipos.TipoMovimiento.DEPOSITAR, DateTime.now(), credito.getMontoTotal());
        }
    }

    public boolean esUsuarioCumplidor() {
        return this.situacionDeuda.esCumplidor();
    }

    public boolean hayCreditoEnCurso() {
        return (this.getCreditos().size() > 0) && (this.estadoUltimoCredito().equals(EstadoCredito.ENCURSO));
    }

    private EstadoCredito estadoUltimoCredito() {
        return this.getUltimoCredito().getEstado();
    }

    public void debitarCuotaCredito() {
        Dinero cuota = this.getUltimoCredito().getMontoCuota();
        if(this.haySaldoSuficiente(cuota)){
            this.saldo.restar(cuota);
            this.getUltimoCredito().saldarMonto(cuota);
        }else {
            this.setSituacionDeuda(EstadoSituacionDeuda.MOROSO);
        }
    }

    public void crearTemplate() {

    }



    // Getters and Setters
    public Dinero getSaldo() {
        return this.saldo;
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

    public EstadoSituacionDeuda getSituacion() { return this.situacionDeuda; }

    public void setSituacionDeuda(EstadoSituacionDeuda situacion) {
        this.situacionDeuda = situacion;
    }
}
