package model.event;

import model.account.Dinero;
import model.account.Usuario;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public abstract class Modalidad {

	protected List<Item> itemsAComprar;
	protected List<ItemUsuario> itemsComprados;
	protected Dinero costoTotal;
	protected Usuario organizador;

	public Modalidad() {
		this.itemsAComprar = new ArrayList<Item>();
		this.itemsComprados = new ArrayList<ItemUsuario>();
		this.costoTotal = new Dinero(0);
	}


	public void calcularCostos(List<Usuario> asistentes) {
		this.costoTotal = new Dinero(0);
		for (ItemUsuario i : itemsComprados)
			this.costoTotal.sumar(i.getItem().getCosto());
	}

	public boolean fechaVigente(DateTime fecha){
		return true;
	}

	public void agregarItemUsuario(ItemUsuario itemUsuario) {
		this.itemsComprados.add(itemUsuario);
		this.costoTotal.sumar(itemUsuario.getItem().getCosto());
	}

	public void agregarItemUsuario(Item item, Usuario usuario){
		this.agregarItemUsuario(new ItemUsuario(item, usuario));
	}

	public int getCantidadItemsComprados(){
		return this.itemsComprados.size();
	}

	public Dinero getCostoUsuario(Usuario usuario){
		return this.getCostoUsuario();
	}

	public Dinero getCostoUsuario(){
		return new Dinero(0);
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

	public void setItemsAComprar(List<Item> listaItems) {
		this.itemsAComprar = listaItems;
	}

	public void addItemsAComprar(Item item) {
		this.itemsAComprar.add(item);
	}
	public Dinero getCostoTotal(){
		return this.costoTotal;

	};
}
