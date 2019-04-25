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
	private List<Usuario> asistentes;   //los asistentes son los usuarios que confirmaron
	private List<Invitacion> invitados;

//	private PanelDeControl pControl;	//un @autowired de PanelDeControl, la idea es tener una instancia

	public Evento(Usuario organizador, String nombreEvento) {
		this.organizador = organizador;
		this.nombre = nombreEvento;
		this.asistentes = new ArrayList<Usuario>();
		this.template = new Template(nombre, "", this.organizador);
		this.invitados = new ArrayList<Invitacion>();
	}
	
//	public void setPanelDeControl(PanelDeControl pControl) {
//		this.pControl = pControl;
//	}
//
//
//	public void enviarInvitacion(String mail) {
//		Invitacion invitacion = new Invitacion(mail, this);
//		this.template.getModalidad().addInvitacion(invitacion);
//		this.pControl.registrarInvitacion(mail, invitacion); //registra en el sistema
//	}
	
//	public void invitarPorLista(List<String> listaInvitados) {
//		  for (String mailInvitado : listaInvitados)
//			  this.enviarInvitacion(mailInvitado);
//	}
//
//
//	public void confirmarAsistencia(Usuario confirmado) {
//		if (this.template.getModalidad().puedeConfirmar(confirmado)){
//			this.asistentes.add(confirmado);//capaz se tenga que enviar a modalidad
//			this.template.getModalidad().addAsistente();
//		}
//		//TODO: si no puede confirmar que lance una advertencia, eception o algo
//
//	}
	
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

//	public void setpControl(PanelDeControl pControl) {
//		this.pControl = pControl;
//	}

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

	//TODO:¿va en evento?, porque no parece ser una funcion comun a todos los eventos
//	public void elegirCompradorItem(int posicionComprador, int posItem) {
//		this.setCompradorItem(this.asistentes.get(posicionComprador), posItem);
//	}

//	public int getTotalCompradores() {
//		return this.getModalidad().getTotalCompradores();
//	}
}
