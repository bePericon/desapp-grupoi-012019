package model.event;

import java.util.List;

import model.account.Usuario;

public class BaquitaCompraPrevia extends Baquita {

	/*
		Baquita: En este caso se determina el evento a realizar y sus gastos (comida, alquiler de
		salon, etc) y a partir de la cantidad de asistentes la aplicación determina cuánto debe pagar
		cada uno de ellos.
		La baquita puede tener dos modalidades. En la primera, una o más personas realizan las
		compras, informan lo gastado y luego se divide con los demás asistentes al evento. 
		
*/
	private List<Usuario> compradores;
	
	
	public BaquitaCompraPrevia(Usuario organizador, List<Usuario> compradores) {
		super(organizador);
		this.compradores= compradores;

	}

	public List<Usuario> getCompradores() {
		return compradores;
	}

	
//iniciar con compradores o hacer funcion comprar, con usuario y cosas compradas?


	
	
}
