package app.model.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.model.account.Dinero;
import app.model.account.Usuario;
import app.model.event.EnumTipos.*;

import javax.persistence.*;

@Entity
@Table(name = "template")
public class Template {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String nombre;
	private String descripcion;

	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Usuario organizador;

	@OneToMany(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	private List<Item> items;

	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Modalidad modalidad;

	@Enumerated(EnumType.STRING)
	private TipoVisibilidad visibilidad;

	public Template(){
	}

	public Template(String nombre, String descripcion, Usuario organizador) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.organizador = organizador;
		this.items = new ArrayList<Item>();
		this.visibilidad = TipoVisibilidad.PRIVADA;
	}

	public Template(String nombre, String descripcion, Usuario organizador, Modalidad modalidad) {
		this(nombre,descripcion, organizador);
		this.modalidad = modalidad;
	}

	public void hacerPublica() {
		this.visibilidad = TipoVisibilidad.PUBLICA;
	}

	public void agregarItem(Item item) {
		this.items.add(item);
	}

	//	GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
		this.modalidad.setItemsAComprar(this.items);
		this.modalidad.setOrganizador(this.organizador);
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public TipoVisibilidad getVisibilidad() {
		return visibilidad;
	}

	public int geCantidadItems() {
		return this.items.size();
	}

	public void calcularCostos(List<Usuario> asistentes) {
		if(this.modalidad != null)
			this.modalidad.calcularCostos(asistentes);
	}

	public Dinero getCostoTotal() {
		if(this.modalidad == null)
			return new Dinero(0);

		return this.modalidad.getCostoTotal();
	}

	public boolean fechaVigente(Date fecha) {
		if(this.modalidad == null)
			return true;

		return this.modalidad.fechaVigente(fecha);
	}

	public void setOrganizador(Usuario usuario){
		this.organizador = usuario;
	}

	public void elegirItemPorIndice(Usuario usuario, int i) {
		this.modalidad.agregarItemUsuario(this.items.get(i), usuario);
	}

	public int getCantidadItemsComprados() {
		return this.modalidad.getCantidadItemsComprados();
	}

	public List<ItemUsuario> getItemsComprados() {
		return this.modalidad.getItemsComprados();
	}

	public Dinero getCostoUsuario(Usuario usuario) {
		return this.modalidad.getCostoUsuario(usuario);
	}

	public Dinero getCostoUsuario() {
		return this.modalidad.getCostoUsuario();
	}
}
