package model.event;

import java.util.Date;
import java.util.stream.Collectors;

import model.account.Usuario;


public class Fiesta extends Modalidad {
/*
	Fiesta: En este caso se envían invitaciones a través de la aplicación y por cada confirmación
	de asistencia, la aplicación va calculando la cantidad de mercaderías a comprar. En este tipo
	de eventos no se distribuyen los gastos del mismo sino que corren por cuenta del
	organizador. Al momento de crear el evento, el organizador no sólo ingresa una lista de
	usuarios a los cuales invitar sino también hasta cuanto tiempo antes se admiten
	confirmaciones.
*/
	
	private Date fechaLimite;

/*
 * Fiesta tiene una lista de items que son los que quieren para la fiesta
 * 
 * 
 * */
	
	
	public Fiesta( Date fechaLimite) {
		super();
		this.fechaLimite = fechaLimite;
	}


	
	public void calcularCompras() {
		int cantAsistentes = this.asistentes;
		
		for (Item i : this.itemsAComprar) {
			ItemUsuario agregar = new ItemUsuario(i, this.organizador);
			int cantItems = cantAsistentes / i.getPersonasPorUnidad(); //se fija la cantidad de cada uno y los agrega

			while(cantItems>0) {
				this.itemsComprados.add(agregar);
				cantItems--;
			}
			
			if (cantAsistentes % i.getPersonasPorUnidad() > 0) //si no era un numero justo, agrega uno mas para no quedarse corto
				this.itemsComprados.add(agregar);
		}

	}
	
	@Override
	public boolean puedeConfirmar(Usuario usuario) {		
		if(getHoy().before(this.fechaLimite)) {
			return estaInvitado(usuario.getEmail());
		}
		return false;
	}
	
	public Date getHoy() {
		Date date = new Date();

		return  date;
	}
	
	public Date getDeadline() {
		return fechaLimite;
	}


}
