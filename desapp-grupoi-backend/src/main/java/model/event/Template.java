package model.event;

import java.util.ArrayList;
import java.util.List;

import model.account.Dinero;
import model.account.Usuario;
import model.event.TipoVisibilidad.Visibilidad;
import org.joda.time.DateTime;

public class Template {

	private String nombre;
	private String descripcion;
	private Usuario organizador;
	private List<Item> items;
	private Modalidad modalidad; 
	private Visibilidad visibilidad;

	public Template(){
	}

	public Template(String nombre, String descripcion, Usuario organizador) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.organizador = organizador;
		this.items = new ArrayList<Item>();
		this.visibilidad = Visibilidad.PRIVADA;
	}

	public Template(String nombre, String descripcion, Usuario organizador, Modalidad modalidad) {
		this(nombre,descripcion, organizador);
		this.modalidad = modalidad;
	}

	public void hacerPublica() {
		this.visibilidad = TipoVisibilidad.Visibilidad.PUBLICA;
	}

	public void agregarItem(Item item) {
		this.items.add(item);
	}

	//	GETTERS Y SETTERS
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

	public List<Item> getItems() {
		return this.items;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
		this.modalidad.setItemsAComprar(this.items);
		this.modalidad.setOrganizador(this.organizador);
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public Visibilidad getVisibilidad() {
		return visibilidad;
	}

	public int geCantidadItems() {
		return this.items.size();
	}

	public void calcularCostos(List<Usuario> asistentes) {
		if(this.modalidad != null)
			this.modalidad.calcularCostos(asistentes);
	}

	public Dinero getCostoTotal() {
		if(this.modalidad == null)
			return new Dinero(0);

		return this.modalidad.getCostoTotal();
	}

	public boolean fechaVigente(DateTime fecha) {
		if(this.modalidad == null)
			return true;

		return this.modalidad.fechaVigente(fecha);
	}

	public void setOrganizador(Usuario usuario){
		this.organizador = usuario;
	}

	public void elegirItemPorIndice(Usuario usuario, int i) {
		this.modalidad.agregarItemUsuario(this.items.get(i), usuario);
	}

	public int getCantidadItemsComprados() {
		return this.modalidad.getCantidadItemsComprados();
	}

	public List<ItemUsuario> getItemsComprados() {
		return this.modalidad.getItemsComprados();
	}

	public Dinero getCostoUsuario(Usuario usuario) {
		return this.modalidad.getCostoUsuario(usuario);
	}

	public Dinero getCostoUsuario() {
		return this.modalidad.getCostoUsuario();
	}
}
