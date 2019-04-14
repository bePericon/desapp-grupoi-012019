package model.event;

import model.account.Usuario;

public class Evento {

	private String nombre;
	private Modalidad modalidad;
	private Usuario organizador;
	
	public Evento(Usuario organizador, String nombreEvento, Modalidad modalidad) {
		this.organizador = organizador;
		this.nombre = nombreEvento;
		this.modalidad = modalidad;
	}

	
	
	
//	Getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public Usuario getOrganizador() {
		return organizador;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}
	
	
	
}
