package model.event;



import model.account.Usuario;

public class BaquitaCompraPrevia extends Baquita {
	/*
		Baquita: En este caso se determina el evento a realizar y sus gastos (comida, alquiler de
		salon, etc) y a partir de la cantidad de asistentes la aplicación determina cuánto debe pagar
		cada uno de ellos.
		La baquita puede tener dos modalidades. En la primera, una o más personas realizan las
		compras, informan lo gastado y luego se divide con los demás asistentes al evento. 
*/

	public BaquitaCompraPrevia( ) {
//		super();
	}


	public void comprar(Usuario usuario, int indexItem) {
		ItemUsuario compra = new ItemUsuario(this.itemsAComprar.get(indexItem), usuario);
		
		this.itemsComprados.add(compra);
	}


	@Override
	public void calcularCostos(){ 
		
		//DIVIDIR LOS GASTOS
//		recorremos la lista y vamos sumando los gastos de cada usuario
		for (ItemUsuario i: this.itemsComprados) {
			
			//crea una lista con cada persona que compró algo con el monto total que gastó
//			al margen ve el monto total dividido por la cantidad de personas
		}
			

		
	}

	
	
}
