package model.event;

import model.account.Dinero;
import model.account.Usuario;

import java.util.ArrayList;
import java.util.List;

public abstract class Modalidad {

	protected List<ItemUsuario> itemsAComprar;
	protected List<ItemUsuario> itemsComprados;
	protected List<Invitacion> invitaciones;
	protected Dinero costoTotal;
	protected int asistentes;
	protected Usuario organizador; //para mi no va

	public Modalidad() {
		super();
		this.itemsComprados = new ArrayList<ItemUsuario>();
		this.itemsAComprar = new ArrayList<ItemUsuario>();
		this.invitaciones = new  ArrayList<Invitacion>();
		this.asistentes = 0;
	}

	
	//es la que se sobreescribe en Fiesta
	public boolean puedeConfirmar(Usuario usuario) {		
		return estaInvitado(usuario.getEmail());
	}
	
	public void calcularCostos() {
		for (ItemUsuario i : itemsComprados)
			this.costoTotal.sumar(i.getItem().getCosto());
	}
	
	public boolean estaInvitado(String mail) {
		boolean ret = false;
		for(Invitacion inv : invitaciones) {
			if (inv.getEmail().equals(mail))
				return true;
		}
		return ret;
	}

	
//	Getters y setters
	public List<ItemUsuario> getItemsAComprar() {
		return itemsAComprar;
	}

	public List<ItemUsuario> getItemsComprados() {
		return itemsComprados;
	}
	
	public void setOrganizador(Usuario user) {
		this.organizador = user;
	}


	public void addInvitacion(Invitacion invitacion) {
		this.invitaciones.add( invitacion);
	}

	public void addUsuarioItem(Usuario usuario, Item item){
		this.itemsAComprar.add(new ItemUsuario(item, usuario));
	}

	public int getTotalCompradores(){
		return this.itemsAComprar.stream().map(i -> i.getUsuario()).distinct().toArray().length;
	}

	public void addAsistente(){
		this.asistentes += 1;
	}
}
