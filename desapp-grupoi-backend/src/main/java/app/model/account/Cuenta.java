package app.model.account;

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

    @OneToOne(cascade={CascadeType.ALL, CascadeType.REMOVE})
    private TarjetaCredito tarjetaCredito;

    @OneToOne(cascade={CascadeType.ALL, CascadeType.REMOVE})
    private Dinero saldo;

    @Enumerated(EnumType.STRING)
    private EstadoSituacionDeuda situacionDeuda;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Invitacion> invitaciones = new ArrayList<Invitacion>();

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
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
        //TODO: manejo de excepciones
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
        if(! this.hayCreditoEnCurso() && this.getSituacion().esCumplidor()){
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
        return this.getUltimoCredito().getEstado();
    }

    public void debitarCuotaCredito() {
        Dinero cuota = this.getUltimoCredito().getMontoCuota();
        if(this.haySaldoSuficiente(cuota)){
            this.saldo.restar(cuota);
            this.agregarMovimiento(EnumTipos.TipoMovimiento.PAGARCUOTA, new Date(), cuota);
            this.getUltimoCredito().saldarMonto(cuota);
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


    // Getters and Setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
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

    public EstadoSituacionDeuda getSituacion() { return this.situacionDeuda; }

    public void setSituacionDeuda(EstadoSituacionDeuda situacion) {
        this.situacionDeuda = situacion;
    }

    public List<Invitacion> getInvitaciones() {
        return this.invitaciones;
    }

    public List<Evento> getEventos() {
        return this.eventos;
    }

    public List<Template> getTemplates() {
        return this.templates;
    }
}