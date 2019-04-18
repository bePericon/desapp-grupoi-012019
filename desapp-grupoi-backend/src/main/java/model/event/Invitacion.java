package model.event;

import model.account.Usuario;

public class Invitacion {

	private String email;
	private Fiesta fiesta;


	public Invitacion(String email, Fiesta fiesta) {
		this.email = email;
		this.fiesta = fiesta;
	}

	
//	al manejar el registro de un usuario, llamar a esta funcion
	public void confirmar(Usuario recienRegistrado) {
		fiesta.seRegistroInvitado(recienRegistrado);
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
