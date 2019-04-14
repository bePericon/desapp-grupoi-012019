package model.event;

import java.util.List;

import model.account.Usuario;

public class Canasta extends Modalidad {
	/*
		Canasta:  (...) se presenta la lista de	gastos a realizar y, al mejor estilo 
		scrum meeting, cada asistente elige un ítem del cual hacerse cargo.
	 */
	
	private List<ItemUsuario> itemsElegidos;
	
	//lista de gastos a realizar sera lista de items?
	public Canasta(Usuario organizador, List<Item> itemsAComprar, List<Usuario> invitados) {
		super(organizador);
		this.items = itemsAComprar;
		this.invitados = invitados;
		
	}

	//TODO:pasar indice o item especifico?
	//la lista debe tener el elemento en el indice pasado o no hace nada
	public void elegirItemEnParticular(Usuario user, int index) {
		if (hayItemsLibres()) {
			ItemUsuario elegido = new ItemUsuario(this.items.get(index), user);
			this.itemsElegidos.add(elegido);
			this.items.remove(index);
		}
		//TODO: exception
	}
	
	//la lista debe tener al menos un elemento o no hace nada
	public void elegirCuaquierItem(Usuario user) {
		if (hayItemsLibres())
			elegirItemEnParticular(user, 0);
		
		//TODO: else exception
	}

	public List<Item> getItemsLibres(){
		return this.items;
	}
	
	public void generarListaItemsAElegir() {
		
	}

	public boolean hayItemsLibres() {
		return this.items.size()>0;
	}
	
	
	
}
