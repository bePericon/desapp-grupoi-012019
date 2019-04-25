package model.event;

import model.account.Dinero;
import model.account.Usuario;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public abstract class Modalidad {

	protected List<Item> itemsAComprar;
	protected List<ItemUsuario> itemsComprados;
//	protected List<Invitacion> invitaciones;
	protected Dinero costoTotal;
	protected Usuario organizador; 	//se usa

	public Modalidad() {
		this.itemsAComprar = new ArrayList<Item>(); //esta es una declaracion SOLO de items
		this.itemsComprados = new ArrayList<ItemUsuario>();// una vez que son comrpados si tienen una correlacion con usuarios
//		this.invitaciones = new  ArrayList<Invitacion>();
		this.costoTotal = new Dinero(0);
	}


	//es la que se sobreescribe en Fiesta
//	public boolean puedeConfirmar(Usuario usuario) {
//		return estaInvitado(usuario.getEmail());
//	}
	
	public void calcularCostos(int cantidadAsistentes) {
		for (ItemUsuario i : itemsComprados)
			this.costoTotal.sumar(i.getItem().getCosto()); 
	}
	
//	public boolean estaInvitado(String mail) {
//		boolean ret = false;
//		for(Invitacion inv : invitaciones) {
//			if (inv.getEmail().equals(mail))
//				return true;
//		}
//		return ret;
//	}

	public boolean estaConfirmado(Evento evento, Usuario user) {
		return evento.getAsistentes().contains(user);
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


//	public void addInvitacion(Invitacion invitacion) {
//		this.invitaciones.add( invitacion);
//	}

//	public void addUsuarioItem(Usuario usuario, Item item){
//		this.itemsAComprar.add(new ItemUsuario(item, usuario));
//	}

//	public int getTotalCompradores(){
//		return this.itemsAComprar.stream().map(i -> i.getUsuario()).distinct().toArray().length;
//	}

	public void setItemsAComprar(List<Item> listaItems) {
		this.itemsAComprar = listaItems;
	}
	
	public void addItemsAComprar(Item item) {
		this.itemsAComprar.add(item);
	}

	public Dinero getCostoTotal(){
		return this.costoTotal;
	};

	public boolean fechaVigente(DateTime fecha){
		return true;
	}

	public void agregarItemUsuario(ItemUsuario itemUsuario) {
		this.itemsComprados.add(itemUsuario);
		this.costoTotal.sumar(itemUsuario.getItem().getCosto());
	}
}
