package model.event;

import java.util.ArrayList;
import java.util.List;

import model.account.Dinero;
import model.account.Usuario;
import org.joda.time.DateTime;

public class Evento {

	private String nombre;
	private Usuario organizador;
	private Template template;
	private List<Usuario> asistentes;
	private List<Invitacion> invitados;

	public Evento(Usuario organizador, String nombreEvento) {
		this.organizador = organizador;
		this.nombre = nombreEvento;
		this.asistentes = new ArrayList<Usuario>();
		this.template = new Template(nombre, "", this.organizador);
		this.invitados = new ArrayList<Invitacion>();
	}

	public void cambiarModalidad(Modalidad modalidad) {
		this.setModalidad(modalidad);
		this.template.calcularCostos(this.getCantidadAsistentes());
	}
	
	
//	Getters y setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Usuario> getAsistentes() {
		return asistentes;
	}
	public Usuario getOrganizador() {
		return organizador;
	}
	public void setAsistentes(List<Usuario> asistentes) {
		this.asistentes = asistentes;
	}

	public void setModalidad(Modalidad modalidad) {
		this.template.setModalidad(modalidad);
	}
	public Modalidad getModalidad() {
		return this.template.getModalidad();
	}

	public int getTotalAsistentes() {
		return this.asistentes.size();
	}
	
	public void setTemplate(Template template) {
		this.template = template;
		this.template.setOrganizador(this.organizador);
	}

	public Template getTemplate() {
		return this.template;
	}

	public List<Invitacion> getInvitados() {
		return this.invitados;
	}

	public void agregarInvitado(String email) {
		this.invitados.add(new Invitacion(email, this));
	}

	public void agregarItem(Item item) {
		this.template.agregarItem(item);
	}

	public int getCantidadItems() {
		return this.template.geCantidadItems();
	}

	public boolean agregarAsistente(Usuario asistente) {
		if(this.puedeAsistir(asistente) && !this.esAsistente(asistente)){
			this.asistentes.add(asistente);
			this.calcularCostos(this.getCantidadAsistentes());
		}

		return this.esAsistente(asistente);
	}

	private boolean esAsistente(Usuario asistente) {
		return this.asistentes.stream().anyMatch(i -> i.getEmail().equals(asistente.getEmail()));
	}

	private boolean puedeAsistir(Usuario asistente) {
		boolean esfechaVigente = this.template.fechaVigente(DateTime.now());
		boolean estaInvitado = this.invitados.stream().anyMatch(i -> i.getEmail().equals(asistente.getEmail()));

		return esfechaVigente && estaInvitado;
	}

	private void calcularCostos(int cantidadAsistentes) {
		this.template.calcularCostos(cantidadAsistentes);
	}

	public int getCantidadAsistentes() {
		return this.asistentes.size();
	}

	public int getCantidadInvitados() {
		return this.invitados.size();
	}

	public Dinero getCostoTotal() {
		return this.template.getCostoTotal();
	}

    public void elegirItemPorIndice(Usuario usuario, int i) {
		this.template.elegirItemPorIndice(usuario, i);
    }

	public int getCantidadItemsComprados() {
		return this.template.getCantidadItemsComprados();
	}

	public List<ItemUsuario> getItemsComprados() {
		return this.template.getItemsComprados();
	}

	public Dinero getCostoUsuario(Usuario usuario) {
		return this.template.getCostoUsuario(usuario);
	}

	public Dinero getCostoUsuario() {
		this.calcularCostos(this.getCantidadAsistentes());
		return this.template.getCostoUsuario();
	}
}
