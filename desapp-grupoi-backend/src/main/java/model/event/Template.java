package model.event;

import java.util.ArrayList;
import java.util.List;

import model.account.Usuario;
import model.event.TipoVisibilidad.Visibilidad;

public class Template {

	private String nombre;
	private String descripcion;
	private List<Item> items;
	private Modalidad modalidad; 
	private Visibilidad visibilidad;

	public Template(){
		this.items = new ArrayList<Item>();
	}
	
	public Template(String nombreTemplate, String descripcionTemplate, Modalidad modalidad) {
		this.nombre = nombreTemplate;
		this.descripcion = descripcionTemplate;
		this.modalidad = modalidad;
		this.visibilidad = TipoVisibilidad.Visibilidad.PRIVADA;
	}

	public Template(String nombre, String descripcion) {
		this();
		this.nombre = nombre;
		this.descripcion = descripcion;
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
	}
	public Modalidad getModalidad() {
		return modalidad;
	}

	public Visibilidad getVisibilidad() {
		return visibilidad;
	}

	public void setCompradorItem(Usuario usuario, int posItem) {
		this.modalidad.addUsuarioItem(usuario, this.items.get(posItem));
	}
}
