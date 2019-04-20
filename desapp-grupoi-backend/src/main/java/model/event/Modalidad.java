package model.event;

import model.account.Usuario;

import java.util.ArrayList;
import java.util.List;

public abstract class Modalidad {

	

	protected List<Item> itemsAComprar;
	protected List<ItemUsuario> itemsComprados;
	protected List<Usuario> asistentes;
	protected int costoTotal;
	protected Usuario organizador;

	public Modalidad() {
		super();
//		this.organizador = organizador;
		this.itemsComprados = new ArrayList<ItemUsuario>();
		this.itemsAComprar = new ArrayList<Item>();
	}

	
	

	public void calcularCostos() {
		for (ItemUsuario i : itemsComprados)
			this.costoTotal+= i.getItem().getCosto();
	}
	
	

//	Getters y setters
	public List<Item> getItemsAComprar() {
		return itemsAComprar;
	}

	public List<ItemUsuario> getItemsComprados() {
		return itemsComprados;
	}
	
	public void setOrganizador(Usuario user) {
		this.organizador = user;
	}
	
	
}
