package model.event;

import java.util.ArrayList;
import java.util.List;

import model.account.Usuario;

public class Evento {

	private String nombre;
	private Usuario organizador;
	private Modalidad modalidad;
	private List<Usuario> asistentes;   //usuarios confirmados
	private PanelDeControl pControl;	//un @autowired de PanelDeControl, la idea es tener una instancia


	public Evento(Usuario organizador, String nombreEvento, Modalidad modalidad) {
		this.organizador = organizador;
		this.nombre = nombreEvento;
		this.modalidad = modalidad;
		this.asistentes = new ArrayList<Usuario>();
	}
	
	public void setPanelDeControl(PanelDeControl pControl) {
		this.pControl = pControl;
	}
	
	
	public void enviarInvitacion(String mail) {
		Invitacion invitacion = new Invitacion(mail, this);
		this.modalidad.addInvitacion(invitacion);
		this.pControl.registrarInvitacion(mail, invitacion); //registra en el sistema
	}
	
	public void invitarPorLista(List<String> listaInvitados) {
		  for (String mailInvitado : listaInvitados) 
			  this.enviarInvitacion(mailInvitado);
	}
	

	public void confirmarAsistencia(Usuario confirmado) {
		if (this.modalidad.puedeConfirmar(confirmado)) 	{
			this.asistentes.add(confirmado);
			this.modalidad.addAsistente();
		}
		//TODO: si no puede confirmar que lance una advertencia, eception o algo
			
	}
	
	public void cambiarModalidad(Modalidad modalidad) {
		this.setModalidad(modalidad);
		modalidad.calcularCostos();
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
	public void setpControl(PanelDeControl pControl) {
		this.pControl = pControl;
	}
	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}
	public Modalidad getModalidad() {
		return this.modalidad;
	}
	public void agregarAsistente(Usuario asistente) {
		this.asistentes.add(asistente);
	}
	public int getTotalAsistentes() {
		return this.asistentes.size();
	}
	
	//TODO:¿va en evento?, porque no parece ser una funcion comun a todos los eventos
//	public void elegirCompradorItem(int posicionComprador, int posItem) {
//		this.setCompradorItem(this.asistentes.get(posicionComprador), posItem);
//	}

//	public int getTotalCompradores() {
//		return this.getModalidad().getTotalCompradores();
//	}
}
