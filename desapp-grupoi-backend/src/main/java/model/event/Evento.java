package model.event;

import java.util.ArrayList;
import java.util.List;

import model.account.Usuario;

public class Evento {

	private String nombre;
	private Modalidad modalidad;		//necesita invitados?
	private Usuario organizador;
	private List<Usuario> asistentes;   //los asistentes son los usuarios que confirmaron
	private List<Invitacion> invitados;
	private PanelDeControl pControl;	//un @autowired de PanelDeControl, la idea es tener una instancia
	

	public Evento(Usuario organizador, String nombreEvento, Modalidad modalidad) {
		this.organizador = organizador;
		this.nombre = nombreEvento;
		this.modalidad = modalidad;
		this.modalidad.setOrganizador(organizador);
		this.asistentes = new ArrayList<Usuario>();
	}

	
	public void setPanelDeControl(PanelDeControl pControl) {
		this.pControl = pControl;
	}
	
	
	public void enviarInvitacion(String mail) {

		Invitacion invitacion = new Invitacion(mail, this);
		this.pControl.registrarInvitacion(mail, invitacion);
	}
	
	
	public void invitarPorLista(List<String> listaInvitados) {
		  for (String inv : listaInvitados) 
			  this.enviarInvitacion(inv);
	}
	

	public void confirmarAsistencia(Usuario confirmado) {
		//ya sea recien registrado o no
		this.asistentes.add(confirmado);
	}
	
	
	
//	Getters y setters
	public List<Invitacion> getInvitados() {
		return invitados;
	}

	public void setInvitados(List<Invitacion> invitados) {
		this.invitados = invitados;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public Usuario getOrganizador() {
		return organizador;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}


	public List<Usuario> getAsistentes() {
		return asistentes;
	}


	public void setAsistentes(List<Usuario> asistentes) {
		this.asistentes = asistentes;
	}


	public void setpControl(PanelDeControl pControl) {
		this.pControl = pControl;
	}



	
	
	
}
