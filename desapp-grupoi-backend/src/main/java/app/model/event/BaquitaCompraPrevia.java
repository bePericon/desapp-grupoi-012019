package app.model.event;


public class BaquitaCompraPrevia extends Baquita {
	/*
		Baquita: En este caso se determina el evento a realizar y sus gastos 
		La baquita puede tener dos modalidades. En la primera, una o más personas realizan las
		compras, informan lo gastado y luego se divide con los demás asistentes al evento. 
*/

	public BaquitaCompraPrevia() {
//		super();
	}

	/*
	*recibe una lista de items ya que cada uno tiene su precio (la de itemspor comprar)
	*viene una persona y compra, se guarda en la lista de ItemUsuario (la de comprados)
		neceitamos una funcion comprar() que guarde al que compro el item en esa lista, y que saque el item de la otra lista
	
	*necesitamos una fucion que recorre toda la lista y:
		1- saca el monto total de lo que costaron todos los items (necesita devolverlo)
		2- usa el costo total y lo divide por asistentes y tenes el precio promedio por cabeza (necesita devolverlo)
		3- arma una lista de hashes de cada persona y lo que gasto en total cada (necesita devolverlo)
	
	
	*/

}
