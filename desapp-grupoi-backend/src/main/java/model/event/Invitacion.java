package model.event;

import model.account.Usuario;

public class Invitacion {

	private String email;
	private Evento evento;
	private boolean confirmada;


	public Invitacion(String email, Evento evento) {
		this.email = email;
		this.evento = evento;
		this.confirmada = false;
	}

	//si no es usuario se registra y ahi confirma
	public void confirmar(Usuario recienConfirmado) {
		this.confirmada = this.evento.agregarAsistente(recienConfirmado);
	}

	public String getNombreEventoInvitacion() {
		return this.evento.getNombre();
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
