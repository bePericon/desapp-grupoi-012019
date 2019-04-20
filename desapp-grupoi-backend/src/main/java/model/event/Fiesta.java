package model.event;

import model.account.Usuario;
import java.util.Date;



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


	public Fiesta(Usuario organizador, Date fechaLimite) {
		super(organizador);
		this.fechaLimite = fechaLimite;
		
	}

//	Se ejecuta cada vez que hay un confirmado
	public void calcularCompras() {
		int cantAsistentes = this.asistentes.size();
		
		for (Item i : this.itemsAComprar) {
			ItemUsuario agregar = new ItemUsuario(i, this.organizador);
			int cantItems = cantAsistentes / i.getPersonasPorUnidad();//se fija la cantidad de cada uno y los agrega

			while(cantItems>0) {
				this.itemsComprados.add(agregar);
				cantItems--;
			}
			
			if (cantAsistentes % i.getPersonasPorUnidad() > 0)// si no era un numero justo, agrega uno mas para no quedarse corto
				this.itemsComprados.add(agregar);
		}

	}
	
	
	public Date getHoy() {
		Date date = new Date();

		return  date;
	}


	public Date getDeadline() {
		return fechaLimite;
	}


}
