package app.model.event;

import app.model.account.Dinero;
import app.model.account.Usuario;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class Modalidad implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	@OneToOne(cascade=CascadeType.ALL)
	protected Dinero costoUsuario;

	public Modalidad(){}

	public Modalidad(Date fechaLimite) {
		this.itemsAComprar = new ArrayList<Item>();
		this.itemsComprados = new ArrayList<ItemUsuario>();
		this.costoTotal = new Dinero(0);
		this.fechaLimite = fechaLimite;
		this.costoUsuario = new Dinero(0);
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

	public int obtenerCantidadItemsComprados(){
		return this.itemsComprados.size();
	}

	public Dinero getCostoUsuario(Usuario usuario){
		return this.getCostoUsuario();
	}

}
