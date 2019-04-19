package model.event;

import model.account.Usuario;

public class Invitacion {

	private String email;
	private Evento evento;


	public Invitacion(String email, Evento evento) {
		this.email = email;
		this.evento = evento;
	}

	
	//si no es usuario se registra y ahi confirma
	public void confirmar(Usuario recienConfirmado) {
		this.evento.confirmarAsistente(recienConfirmado);
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
