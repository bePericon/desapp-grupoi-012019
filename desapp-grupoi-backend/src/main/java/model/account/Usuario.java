package model.account;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import model.event.Invitacion;

public class Usuario {

	private String nombre;
	private String apellido;
	private String email;
	private DateTime fechaNac;
	private String contrasenia;// TODO: ver si corresponde modelar aca
	private Cuenta cuenta;
	private List<Invitacion> invitaciones;
	
	
	public Usuario() {

	}

	public Usuario(String nombre, String apellido, String email, DateTime fechaNac) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNac = fechaNac;
		this.cuenta = new Cuenta();
		this.invitaciones = new ArrayList<Invitacion>();
	}

	public boolean esContraseniaCorrecta(String contra) {
		return this.contrasenia.equals(contra);
	}

	public void addInvitacion (Invitacion inv) {
		this.invitaciones.add(inv);
	}
	
	public void aceptarInvitacion(Invitacion invitacion) {
		invitacion.confirmar(this);
	}


//	GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public DateTime getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(DateTime fechaNac) {
		this.fechaNac = fechaNac;
	}
	public void setCuenta(Cuenta cuenta) { this.cuenta = cuenta; }
	public Cuenta getCuenta() { return this.cuenta; }
	public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

	public String getContrasenia(){ return this.contrasenia; }

	public List<Invitacion> getInvitaciones() {
		return invitaciones;
	}
	public void setInvitaciones(List<Invitacion> invitaciones) {
		this.invitaciones = invitaciones;
	}

	
}
