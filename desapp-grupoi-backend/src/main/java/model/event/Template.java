package model.event;

import java.util.List;

import model.event.TipoVisibilidad.Visibilidad;

public class Template {

	private String nombre;
	private String descripcion;
//	private List<Item> items; 
	private Modalidad modalidad; 
	private Visibilidad visibilidad;
	
	
	public Template(String nombreTemplate, String descripcionTemplate, Modalidad modalidad) {
		this.nombre = nombreTemplate;
		this.descripcion = descripcionTemplate;
		this.modalidad = modalidad;
		this.visibilidad = TipoVisibilidad.Visibilidad.PRIVADA;
	}

	
	public void hacerPublica() {
		this.visibilidad = TipoVisibilidad.Visibilidad.PUBLICA;
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
		return modalidad.getItems();
	}

	public Modalidad getModalidad() {
		return modalidad;
	}
	public Visibilidad getVisibilidad() {
		return visibilidad;
	}

}
