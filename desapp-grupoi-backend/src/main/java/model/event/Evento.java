package model.event;

import java.util.List;

import model.account.Usuario;

public class Evento {

	private String nombre;
	private Modalidad modalidad;
	private Usuario organizador;
	private List<Usuario> asistentes; //los asistentes son los usuarios que confirmaron
	private List<Invitacion> invitados;
	

	public Evento(Usuario organizador, String nombreEvento, Modalidad modalidad, List<String> listaInvitados) {
		this.organizador = organizador;
		this.nombre = nombreEvento;
		this.modalidad = modalidad;
		this.invitarPorLista(listaInvitados);
		
	}

	
	
	public void enviarInvitacionA(String mail) {
		//TODO:implementar envio de invitaciones
		
		
	}
	
	public void invitarPorLista(List<String> listaInvitados) {
		
		  for (String inv : listaInvitados) {
			  this.enviarInvitacionA(inv);
	      }
	}
	

	public void confirmarAsistente(Usuario recienConfirmado) {
		
		this.asistentes.add(recienConfirmado);
		
		//this.modalidad.setAsistentes(this.asistentes);
		//this.modalidad.calcularCostos();
		
	}
	
	
	
//	Getters y setters
	public List<Invitacion> getInvitados() {
		return invitados;
	}

	public void setInvitados(List<Invitacion> invitados) {
		this.invitados = invitados;
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

	public Usuario getOrganizador() {
		return organizador;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}



	
	
	
}
