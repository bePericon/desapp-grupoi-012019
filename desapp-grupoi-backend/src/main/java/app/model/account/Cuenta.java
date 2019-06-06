package app.model.account;

import app.error.exception.ExceptionNotAcceptable;
import app.model.event.Evento;
import app.model.event.Invitacion;
import app.model.event.Template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.model.account.EnumEstados.*;

import javax.persistence.*;

@Entity
@Table(name = "cuenta")
public class Cuenta implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade=CascadeType.ALL)
    private Usuario usuario;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Movimiento> movimientos = new ArrayList<Movimiento>();

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Credito> creditos = new ArrayList<Credito>();

    @OneToOne(cascade=CascadeType.ALL)
    private TarjetaCredito tarjetaCredito;

    @OneToOne(cascade=CascadeType.ALL)
    private Dinero saldo;

    @Enumerated(EnumType.STRING)
    private EstadoSituacionDeuda situacionDeuda;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Invitacion> invitaciones = new ArrayList<Invitacion>();

    @OneToMany(cascade=CascadeType.ALL)
    private List<Evento> eventos = new ArrayList<Evento>();

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Template> templates = new ArrayList<Template>();

    public Cuenta(){
    }

    public Cuenta(Usuario usuario){
        this.usuario = usuario;
//        this.usuario.setCuenta(this);
        this.saldo = new Dinero(0);
        this.situacionDeuda = EstadoSituacionDeuda.NORMAL;
    }

    public void depositarDinero(Dinero monto) {
        this.saldo.sumar(monto);
        this.agregarMovimiento(EnumTipos.TipoMovimiento.DEPOSITAR, new Date(), monto);
    }

    public void retirarDinero(Dinero monto) {
        if(this.haySaldoSuficiente(monto)) {
            this.saldo.restar(monto);
            this.agregarMovimiento(EnumTipos.TipoMovimiento.RETIRAR, new Date(), monto);
        }
        else
            throw new ExceptionNotAcceptable("No hay saldo suficiente.");
    }

    public boolean haySaldoSuficiente(Dinero monto) {
        return this.saldo.mayorACero() && this.saldo.mayorIgualA(monto);
    }

    public void solicitarCredito() {
        this.agregarCredito(new Credito(new Dinero(1000), new Dinero(1200), 6, this.usuario));
    }

    public void agregarMovimiento(EnumTipos.TipoMovimiento depositar, Date fecha, Dinero monto) {
        this.movimientos.add(new Movimiento(depositar, fecha, monto));
    }

    private void agregarCredito(Credito credito) {
        if(! this.hayCreditoEnCurso() && this.getSituacionDeuda().esCumplidor()){
            credito.setUsuarioSolicitante(this.getUsuario());
            this.creditos.add(credito);
            this.saldo.sumar(credito.getMonto());
            this.agregarMovimiento(EnumTipos.TipoMovimiento.CREDITO, new Date(), credito.getMonto());
        }
    }

    public boolean hayCreditoEnCurso() {
        return (this.getCreditos().size() > 0) && (this.estadoUltimoCredito().equals(EstadoCredito.ENCURSO));
    }

    private EstadoCredito estadoUltimoCredito() {
        return this.creditos.get(this.creditos.size()-1).getEstado();
    }

    public void debitarCuotaCredito() {
        Dinero cuota = this.creditos.get(this.creditos.size()-1).getMontoCuota();
        if(this.haySaldoSuficiente(cuota)){
            this.saldo.restar(cuota);
            this.agregarMovimiento(EnumTipos.TipoMovimiento.PAGARCUOTA, new Date(), cuota);
            this.creditos.get(this.creditos.size()-1).saldarMonto(cuota);
        }else {
            this.setSituacionDeuda(EstadoSituacionDeuda.MOROSO);
        }
    }

    public void agregarInvitacion(Invitacion invitacion) {
        this.invitaciones.add(invitacion);
    }

    public void agregarEvento(Evento evento) {
        this.eventos.add(evento);
    }

    public void agregarTemplate(Template template) {
        this.templates.add(template);
    }

    //Getters y setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public List<Credito> getCreditos() {
        return creditos;
    }

    public void setCreditos(List<Credito> creditos) {
        this.creditos = creditos;
    }

    public TarjetaCredito getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public Dinero getSaldo() {
        return saldo;
    }

    public void setSaldo(Dinero saldo) {
        this.saldo = saldo;
    }

    public EstadoSituacionDeuda getSituacionDeuda() {
        return situacionDeuda;
    }

    public void setSituacionDeuda(EstadoSituacionDeuda situacionDeuda) {
        this.situacionDeuda = situacionDeuda;
    }

    public List<Invitacion> getInvitaciones() {
        return invitaciones;
    }

    public void setInvitaciones(List<Invitacion> invitaciones) {
        this.invitaciones = invitaciones;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }
}