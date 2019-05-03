package app.model.event;

import app.model.account.Dinero;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class BaquitaRecoleccionPrev extends Baquita {

	/*
	Baquita: En este caso se determina el evento a realizar y sus gastos (comida, alquiler de
		salon, etc) y a partir de la cantidad de asistentes la aplicación determina cuánto debe pagar
		cada uno de ellos.

		En la segunda, la aplicación calcula un costo estimado total para el evento (se puede valer de
		información propia o ingresada por los usuarios), crea una cuenta común a la cual deberán
		girar su parte los asistentes y una vez reunido el dinero, el organizador puede disponer de los
		mismos para las compras.
*/

	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Dinero dineroDepositado;

	private boolean listoParaComprarTodo;
	
	
	public BaquitaRecoleccionPrev( ) {
//		super();
		this.dineroDepositado = new Dinero(0);
		this.listoParaComprarTodo = false;
		//calculo de costo total?
	}

	public void recibirDeposito(Dinero deposito) {
		this.dineroDepositado.sumar(deposito);
		this.listoParaComprarTodo = this.costoTotal.mayorIgualA(this.dineroDepositado);
	}

	
//	calculo de costoTotal tras cada deposito?
	
	
	public void comprar() {
		
		//el organizador compra todo, ver si compra todo asi 
		
	}
	
	
	public Dinero getDineroDepositado() {
		return dineroDepositado;
	}

	public void setDineroDepositado(Dinero dineroDepositado) {
		this.dineroDepositado = dineroDepositado;
	}
	
	
}
