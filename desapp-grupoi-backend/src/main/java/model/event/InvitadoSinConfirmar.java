package model.event;

import model.account.Usuario;

public class InvitadoSinConfirmar {

	private String email;
	private Fiesta fiesta;


	public InvitadoSinConfirmar(String email, Fiesta fiesta) {
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
