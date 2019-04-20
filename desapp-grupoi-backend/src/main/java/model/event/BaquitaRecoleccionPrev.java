package model.event;

import model.account.Usuario;

import java.util.List;

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
	

	private int dineroDepositado;
	private boolean listoParaComprarTodo;
	
	
	public BaquitaRecoleccionPrev( ) {
//		super();
		this.dineroDepositado = 0;
		this.listoParaComprarTodo = false;
		//calculo de costo total?
	}
		
	public void recibirDeposito(int deposito) {
		this.dineroDepositado += deposito;
		
		if (this.costoTotal >= this.dineroDepositado ) {
			this.listoParaComprarTodo = true;
		}
	}

	
//	calculo de costoTotal tras cada deposito?
	
	
	public void comprar() {
		
		//el organizador compra todo, ver si compra todo asi 
		
	}
	
	
	public int getDineroDepositado() {
		return dineroDepositado;
	}

	public void setDineroDepositado(int dineroDepositado) {
		this.dineroDepositado = dineroDepositado;
	}
	
	
}
