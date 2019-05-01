package app.model.event;

import app.model.account.Usuario;

public class ItemUsuario {

	private Item item;
	private Usuario usuario;
	
	public ItemUsuario(Item item, Usuario user) {
		super();
		this.item = item;
		this.usuario = user;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
