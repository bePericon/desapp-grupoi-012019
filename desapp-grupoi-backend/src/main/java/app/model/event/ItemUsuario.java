package app.model.event;

import app.model.account.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "itemusuario")
public class ItemUsuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@OneToOne(cascade=CascadeType.ALL)
	private Item item;

	@OneToOne(cascade=CascadeType.ALL)
	private Usuario usuario;

	public ItemUsuario() {
	}

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
