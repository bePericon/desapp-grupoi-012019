package model.event;

public class Evento {

	private String nombre;
	private Modalidad modalidad;
	
	public Evento(String nombreEvento, Modalidad modalidad) {
		
		this.nombre=nombreEvento;
		this.modalidad=modalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}
	
	
	
}
