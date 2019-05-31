package app.model.event;

import app.model.account.Dinero;
import app.model.account.Usuario;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
public class Fiesta extends Modalidad {
/*
	Fiesta: En este caso se envían invitaciones a través de la aplicación y por cada confirmación
	de asistencia, la aplicación va calculando la cantidad de mercaderías a comprar.  
	los gastos del mismo  corren por cuenta del
	organizador. Al momento de crear el evento, el organizador  ingresa una lista de
	usuarios a los cuales invitar y también hasta cuanto tiempo antes se admiten
	confirmaciones.
*/

	public Fiesta() {
	}

	public Fiesta(Date fechaLimite) {
		super(fechaLimite);
	}

	@Override
	public void calcularCostos(List<Usuario> asistentes) {
		int cantAsistentes = asistentes.size();
		this.itemsComprados.clear();
		this.costoTotal = new Dinero(0);
		
		for (Item i : this.itemsAComprar) {
			ItemUsuario itemUsuario = new ItemUsuario(i, this.organizador);
			int cantItems = cantAsistentes / i.getPersonasPorUnidad(); //se fija la cantidad de cada uno y los agrega

			while(cantItems > 0) {
				this.agregarItemUsuario(itemUsuario);
				cantItems--;
			}
			
			if (cantAsistentes % i.getPersonasPorUnidad() > 0) //si no era un numero justo, agrega uno mas para no quedarse corto
				this.agregarItemUsuario(itemUsuario);
		}
	}
}
