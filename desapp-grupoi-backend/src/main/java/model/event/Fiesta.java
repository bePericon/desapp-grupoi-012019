package model.event;

import model.account.Usuario;

import java.util.Date;
import java.util.List;



public class Fiesta extends Modalidad {
	/*
	Fiesta: En este caso se envían invitaciones a través de la aplicación y por cada confirmación
	de asistencia, la aplicación va calculando la cantidad de mercaderías a comprar. En este tipo
	de eventos no se distribuyen los gastos del mismo sino que corren por cuenta del
	organizador. Al momento de crear el evento, el organizador no sólo ingresa una lista de
	usuarios a los cuales invitar sino también hasta cuanto tiempo antes se admiten
	confirmaciones.
*/
	
	private List<Invitacion> invitadosSinConfirmar;
	private Date fechaLimite;

	//costoevento
	//items?


	public Fiesta(Usuario organizador, Date fechaLimite, List<Invitacion> invitados) {
		super(organizador);
		this.fechaLimite = fechaLimite;
		this.invitadosSinConfirmar = invitados;
	}

	public void calcularCompras() {
//		TODO: ver como hacemos el calculo
	}
	
	
	public void setItemsPorPersona() {
//		TODO: ver si seteamos asi configurable o que
	}
	
	
	public void seRegistroInvitado(Usuario recienRegistrado) {
		this.agregarInvitado(recienRegistrado);
		this.calcularCompras();
	}
	
	public Date getDeadline() {
		return fechaLimite;
	}

	

	
	
}
