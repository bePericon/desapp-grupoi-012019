package model.event;

import model.account.Usuario;

public class BaquitaCompraPrevia extends Baquita {

	
	private int costoEstimado;
	private int dineroDepositado;
	
	
	public BaquitaCompraPrevia(Usuario organizador) {
		super(organizador);
		this.dineroDepositado = 0;
	}


	public void calcularCostoEstimado() {
		
		//hace un calculo loco y mira un estmado del precio
//		this.costoEstimado = costoLoco;
		
	}
	
	public void depositar(int deposito) {
		this.dineroDepositado += deposito;
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
