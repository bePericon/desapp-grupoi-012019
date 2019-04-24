package model.event;

import model.account.Usuario;

public class Canasta extends Modalidad {
	/*
		Canasta: se presenta la lista de gastos a realizar y cada asistente elige un ítem del cual hacerse cargo.
	 */
	
	public Canasta( ) {

	}

	//El indice debe ser valido
	public void elegirItemEnParticularPorIndice(Usuario user, int index) {
		if (hayItemsLibres()) {
			ItemUsuario elegido = new ItemUsuario(this.itemsAComprar.get(index), user);
			this.itemsComprados.add(elegido);
			this.itemsAComprar.remove(index);
		}
		//TODO: exception
	}
	
	//Lista debe ser no vacia
	public void elegirCuaquierItem(Usuario user) {
		if (hayItemsLibres()) {
			elegirItemEnParticularPorIndice(user, 0);
			this.itemsAComprar.remove(0);
		}
			
		//TODO: else exception
	}


	public boolean hayItemsLibres() {
		return this.itemsAComprar.size()>0;
	}
	
	
	
}
