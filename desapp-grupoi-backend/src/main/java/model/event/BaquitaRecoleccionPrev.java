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
	
	private int costoEstimado;
	private int dineroDepositado;
	private boolean listoParaComprarTodo;
	
	
	//TODO: lista de asistentes?
	
	public BaquitaRecoleccionPrev(Usuario organizador) {
		super(organizador);
		this.dineroDepositado = 0;
		this.listoParaComprarTodo = false;
	}


	public void calcularCostoEstimado() {
		
		//TODO: como hace el calculo?
//		this.costoEstimado = costoLoco;
		
	}
	
	
	no vamos 
	
	
	
	
	
	public void recibirDeposito(int deposito) {
		this.dineroDepositado += deposito;
		
		if (this.costoEstimado >= this.dineroDepositado ) {
			this.listoParaComprarTodo = true;
		}
	}
	
	public int getCostoEstimado() {
		return costoEstimado;
	}

	public void setCostoEstimado(int costoEstimado) {
		this.costoEstimado = costoEstimado;
	}

	public int getDineroDepositado() {
		return dineroDepositado;
	}

	public void setDineroDepositado(int dineroDepositado) {
		this.dineroDepositado = dineroDepositado;
	}
	
	
}
