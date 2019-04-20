package model.event;

import java.util.List;

import model.account.Usuario;

public class Canasta extends Modalidad {
	/*
		Canasta:  (...) se presenta la lista de	gastos a realizar y, al mejor estilo 
		scrum meeting, cada asistente elige un ítem del cual hacerse cargo.
	 */
	
//	private List<ItemUsuario> itemsElegidos;
//	private List<Item> copiaDeItems;
	
	public Canasta( ) {
	
		
	}

	//El indice debe ser valido
	public void elegirItemEnParticular(Usuario user, int index) {
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
			elegirItemEnParticular(user, 0);
			this.itemsAComprar.remove(0);
		}
			
		//TODO: else exception
	}

	public List<Item> getItemsLibres(){
		return this.itemsAComprar;
	}

	public boolean hayItemsLibres() {
		return this.itemsAComprar.size()>0;
	}
	
	
	
}
