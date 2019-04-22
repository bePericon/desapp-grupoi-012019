package model.event;

import model.account.Usuario;

import java.util.ArrayList;
import java.util.List;

public abstract class Modalidad {

	protected List<Item> itemsAComprar;
	protected List<ItemUsuario> itemsComprados;
	protected List<Invitacion> invitados;
	protected List<Usuario> asistentes; //ver si se necesitan los asistentes o solo un contador de asistentes
	protected int costoTotal;
	protected Usuario organizador;

	public Modalidad() {
		super();
		this.itemsComprados = new ArrayList<ItemUsuario>();
		this.itemsAComprar = new ArrayList<Item>();
	}

	
	//es la que se sobreescribe en Fiesta
	public boolean puedeConfirmar(Usuario usuario) {		
		return estaInvitado(usuario.getEmail());
	}

	
	
	public void calcularCostos() {
		for (ItemUsuario i : itemsComprados)
			this.costoTotal+= i.getItem().getCosto();
	}
	
	
	public boolean estaInvitado(String mail) {
		boolean ret = false;
		for(Invitacion user : invitados) {
			if (user.getEmail().equals(mail))
				return true;
		}
		return ret;
	}
	
	
	public void addAsistente(Usuario usuario) {
		this.asistentes.add(usuario);
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


	public void addInvitado(Invitacion invitado) {
		this.invitados.add( invitado);
	}
	
	
}
