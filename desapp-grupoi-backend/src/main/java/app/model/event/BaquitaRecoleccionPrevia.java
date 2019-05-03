package app.model.event;

import app.model.account.Dinero;
import app.model.account.Usuario;

import javax.persistence.Entity;

@Entity
public class BaquitaRecoleccionPrevia extends Baquita {

	/*
	Baquita: En este caso se determina el evento a realizar y sus gastos (comida, alquiler de
		salon, etc) y a partir de la cantidad de asistentes la aplicación determina cuánto debe pagar
		cada uno de ellos.

		En la segunda, la aplicación calcula un costo estimado total para el evento (se puede valer de
		información propia o ingresada por los usuarios), crea una cuenta común a la cual deberán
		girar su parte los asistentes y una vez reunido el dinero, el organizador puede disponer de los
		mismos para las compras.
*/

	public BaquitaRecoleccionPrevia() {
	}

	public void pagarDeuda(Usuario deudor, Dinero monto){
		if(this.esDeudor(deudor.getEmail()))
			this.deudas.add(new Deuda(deudor, monto, this.organizador));
		//else
		// throw exception
	}

	private boolean esDeudor(String email){
		return !this.deudas.stream().anyMatch(deuda -> deuda.getDeudor().getEmail().equals(email));
	}

	public boolean estanDeudasPagadas(){
		Dinero total = new Dinero(0);
		for (Deuda d : this.deudas)
			total.sumar(d.getMonto());
		return this.costoTotal.getMonto() == total.getMonto();
	}
}
