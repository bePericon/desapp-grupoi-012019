package model.event;

import model.account.Usuario;

import java.util.List;

public abstract class Modalidad {

	
	protected List<Usuario> invitados;
	protected List<Item> items;
	

	public Modalidad(Usuario organizador) {
		super();		
	}

	
	protected void agregarInvitado(Usuario invitado) {
		this.invitados.add(invitado);
	}
	
	
	
	
//	Getters y setters
	public List<Item> getItems() {
		return items;
	}
	public List<Usuario> getInvitados() {
		return invitados;
	}
	public void setInvitados(List<Usuario> invitados) {
		this.invitados = invitados;
	}
	
	
}
