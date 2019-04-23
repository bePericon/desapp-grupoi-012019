package model.event;

import java.util.ArrayList;
import java.util.List;

import model.account.Usuario;

public class Evento {

	private String nombre;
	private Usuario organizador;
	private Template template;
	private List<Usuario> asistentes;   //los asistentes son los usuarios que confirmaron
	private PanelDeControl pControl;	//un @autowired de PanelDeControl, la idea es tener una instancia


	public Evento(Usuario organizador, String nombreEvento) {
		this.organizador = organizador;
		this.nombre = nombreEvento;
		this.asistentes = new ArrayList<Usuario>();
	}
	
	public void setPanelDeControl(PanelDeControl pControl) {
		this.pControl = pControl;
	}
	
	
	public void enviarInvitacion(String mail) {
		Invitacion invitacion = new Invitacion(mail, this);
		this.template.getModalidad().addInvitado(invitacion);//sumar contador
		this.pControl.registrarInvitacion(mail, invitacion); //registra en el sistema
	}
	
	
	public void invitarPorLista(List<String> listaInvitados) {
		  for (String inv : listaInvitados) 
			  this.enviarInvitacion(inv);
	}
	

	public void confirmarAsistencia(Usuario confirmado) {
		if (this.template.getModalidad().puedeConfirmar(confirmado)) 	{
			this.asistentes.add(confirmado);
			this.template.getModalidad().addAsistente();
		}
			
	}
	
	public void cambiarModalidad(Modalidad modalidad) {
		this.template.setModalidad(modalidad);
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


	public void setTemplate(Template tem) {
		this.template = tem;
	}

	public Template getTemplate() {
		return this.template;
	}

	public void agregarAsistente(Usuario asistente) {
		this.asistentes.add(asistente);
	}

	public int getTotalAsistentes() {
		return this.asistentes.size();
	}

	public void elegirCompradorItem(int posComprador, int posItem) {
		this.template.setCompradorItem(this.asistentes.get(posComprador), posItem);
	}

	public int getTotalCompradores() {
		return this.template.getModalidad().getTotalCompradores();
	}
}
