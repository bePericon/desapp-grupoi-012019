package app.model.event;

import app.model.account.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "invitacion")
public class Invitacion {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	private String email;
	private boolean confirmada;

	@ManyToOne
	private Evento evento;

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


    public boolean estaPendiente() {
		return !this.confirmada;
    }
}
