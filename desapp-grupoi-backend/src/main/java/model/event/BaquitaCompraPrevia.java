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
	
	
	public BaquitaCompraPrevia(Usuario organizador) {
		super(organizador);
	}

//
//	public void comprar(ItemUsuario userEItem) {
//		this.itemsComprados.add(userEItem);
//	}

	//TODO: idealmente se sacaria de la lita de items
	public void comprar(Usuario usuario, Item item) {
		ItemUsuario compra = new ItemUsuario( item, usuario);
		
		this.itemsComprados.add(compra);
	}


	@Override
	public void calcularCostos(){ 
		
		//DIVIDIR LOS GASTOS
//		recorremos la lista y vamos sumando los gastos de cada usuario
//		compradores = usurios, gastos
//		que haga una lsita con la personay la plata que gasto y que quede guardada, despues vemos que hacemos
//		
//		sumamos todos los gastos  dividimos por invitados
//		despues hacemos la magia o guardamos de aguna manera los gastos que le corresponde a cada uno (gastos= lo que le tienen que dar los demas)
		
	}
	

	
	
	
	
	
	
//iniciar con compradores o hacer funcion comprar, con usuario y cosas compradas?


	
	
}
