package app.model.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.model.account.Dinero;
import app.model.account.Usuario;
import app.model.event.EnumTipos.*;

import javax.persistence.*;

@Entity
@Table(name = "e_template")
public class Template implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String nombre;
	private String descripcion;

	@OneToOne(cascade=CascadeType.ALL)
	private Usuario organizador;

	@OneToMany(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	private List<Item> items;

	@OneToOne(cascade=CascadeType.ALL)
	private Modalidad modalidad;

	@Enumerated(EnumType.STRING)
	private TipoVisibilidad visibilidad;

	public Template(){
	}

	public Template(String nombre, String descripcion, Usuario organizador) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.organizador = organizador;
		this.items = new ArrayList<Item>();
		this.visibilidad = TipoVisibilidad.PRIVADA;
	}

	public Template(String nombre, String descripcion, Usuario organizador, Modalidad modalidad) {
		this(nombre,descripcion, organizador);
		this.modalidad = modalidad;
		this.modalidad.setOrganizador(organizador);
	}

	public void hacerPublica() {
		this.visibilidad = TipoVisibilidad.PUBLICA;
	}

	public void agregarItem(Item item) {
		this.items.add(item);
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
		this.modalidad.setItemsAComprar(this.items);
		this.modalidad.setOrganizador(this.organizador);
	}

	public int obtenerCantidadItems() {
		return this.items.size();
	}

	public void setOrganizador(Usuario usuario){
		this.organizador = usuario;
		if(this.modalidad != null)
			this.modalidad.setOrganizador(usuario);
	}

	public void calcularCostos(List<Usuario> asistentes) {
		if(this.modalidad != null)
			this.modalidad.calcularCostos(asistentes);
	}

	public Dinero obtenerCostoTotal() {
		if(this.modalidad == null)
			return new Dinero(0);

		return this.modalidad.getCostoTotal();
	}

	public boolean fechaVigente(Date fecha) {
		if(this.modalidad == null)
			return true;

		return this.modalidad.fechaVigente(fecha);
	}

	public void elegirItemPorIndice(Usuario usuario, int i) {
		this.modalidad.agregarItemUsuario(this.items.get(i), usuario);
	}

	public int obtenerCantidadItemsComprados() {
		return this.modalidad.obtenerCantidadItemsComprados();
	}

	public List<ItemUsuario> obtenerItemsComprados() {
		return this.modalidad.getItemsComprados();
	}

	public Dinero obtenerCostoUsuario(Usuario usuario) {
		return this.modalidad.getCostoUsuario(usuario);
	}

	public Dinero obtenerCostoUsuario() {
		return this.modalidad.getCostoUsuario();
	}

	//Getters y setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getOrganizador() {
		return organizador;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public TipoVisibilidad getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(TipoVisibilidad visibilidad) {
		this.visibilidad = visibilidad;
	}
}
