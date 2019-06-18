package app.model.event;

import app.model.account.Usuario;
import app.model.event.EnumEstados.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invitacion")
public class Invitacion implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	private String email;

	@Enumerated(EnumType.STRING)
	private EstadoInvitacion estadoInvitacion;

	@ManyToOne
	private Evento evento;

	public Invitacion(){}

	public Invitacion(String email, Evento evento) {
		this.email = email;
		this.evento = evento;
		this.estadoInvitacion = EstadoInvitacion.PENDIENTE;
	}

	//si no es usuario se registra y ahi confirma
	public void confirmar(Usuario recienConfirmado) {
		if(this.evento.agregarAsistente(recienConfirmado))
			this.estadoInvitacion = EstadoInvitacion.ACEPTADA;
	}

	public String getNombreEventoInvitacion() {
		return this.evento.getNombre();
	}

	public boolean estaPendiente() { return this.estadoInvitacion == EstadoInvitacion.PENDIENTE; }

	public boolean estaAceptada() {
		return this.estadoInvitacion == EstadoInvitacion.ACEPTADA;
	}

	public boolean estaRechazada() {
		return this.estadoInvitacion == EstadoInvitacion.RECHAZADA;
	}

	// Getters y Setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EstadoInvitacion getEstadoInvitacion() {
		return estadoInvitacion;
	}

	public void setEstadoInvitacion(EstadoInvitacion estadoInvitacion) {
		this.estadoInvitacion = estadoInvitacion;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
