package app.model.event;

import app.model.account.Dinero;
import app.model.account.Usuario;
import app.model.event.EnumTipos.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class Modalidad implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@OneToMany(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	protected List<TemplateItem> itemsAComprar;

	@OneToMany(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	protected List<ItemUsuario> itemsComprados;

	@OneToOne(cascade=CascadeType.ALL)
	protected Dinero costoTotal;

	@OneToOne(cascade=CascadeType.ALL)
	protected Usuario organizador;

	@Column
	protected Date fechaLimite;

	@OneToOne(cascade=CascadeType.ALL)
	protected Dinero costoUsuario;

	@Enumerated(EnumType.STRING)
	protected TipoModalidad tipoModalidad;

	public Modalidad(){}

	public Modalidad(Date fechaLimite) {
		this.itemsAComprar = new ArrayList<>();
		this.itemsComprados = new ArrayList<ItemUsuario>();
		this.costoTotal = new Dinero(0);
		this.fechaLimite = fechaLimite;
		this.costoUsuario = new Dinero(0);
	}

	public void calcularCostos(List<Usuario> asistentes) {
		this.costoTotal = new Dinero(0);
		for (ItemUsuario i : itemsComprados)
			this.costoTotal.sumar(i.getItem().getCosto());
	}

	public boolean fechaVigente(Date fecha){
		return this.fechaLimite.after(fecha);
	}

	public void agregarItemUsuario(ItemUsuario itemUsuario) {
		this.itemsComprados.add(itemUsuario);
		this.costoTotal.sumar(itemUsuario.getItem().getCosto());
	}

	public void agregarItemUsuario(TemplateItem ti, Usuario usuario){
		int i;
		for (i = ti.getCantidad(); i > 0; i--){
			this.agregarItemUsuario(new ItemUsuario(ti.getItem(), usuario));
		}
	}

	public int obtenerCantidadItemsComprados(){
		return this.itemsComprados.size();
	}

	public Dinero getCostoUsuario(Usuario usuario){
		return this.getCostoUsuario();
	}

	//Getters y Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<TemplateItem> getItemsAComprar() {
		return itemsAComprar;
	}

	public void setItemsAComprar(List<TemplateItem> itemsAComprar) {
		this.itemsAComprar = itemsAComprar;
	}

	public List<ItemUsuario> getItemsComprados() {
		return itemsComprados;
	}

	public void setItemsComprados(List<ItemUsuario> itemsComprados) {
		this.itemsComprados = itemsComprados;
	}

	public Dinero getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(Dinero costoTotal) {
		this.costoTotal = costoTotal;
	}

	public Usuario getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Usuario organizador) {
		this.organizador = organizador;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public Dinero getCostoUsuario() {
		return costoUsuario;
	}

	public void setCostoUsuario(Dinero costoUsuario) {
		this.costoUsuario = costoUsuario;
	}

	public TipoModalidad getTipoModalidad() {
		return tipoModalidad;
	}

	public void setTipoModalidad(TipoModalidad tipoModalidad) {
		this.tipoModalidad = tipoModalidad;
	}
}
