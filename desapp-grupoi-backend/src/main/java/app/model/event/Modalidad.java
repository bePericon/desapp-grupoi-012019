package app.model.event;

import app.model.account.Dinero;
import app.model.account.Usuario;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Modalidad implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;

	@OneToMany(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	protected List<Item> itemsAComprar;

	@OneToMany(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	protected List<ItemUsuario> itemsComprados;

	@OneToOne(cascade=CascadeType.ALL)
	protected Dinero costoTotal;

	@OneToOne(cascade=CascadeType.ALL)
	protected Usuario organizador;

	@Column
	protected Date fechaLimite;

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

	public boolean fechaVigente(Date fecha){
		return this.fechaLimite.after(fecha);
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
	}
}
