package model.account;

import org.joda.time.DateTime;

public class Usuario {

	private String nombre;
	private String apellido;
	private String email;
	private DateTime fechaNac;
	private String contrasenia;// TODO: ver si corresponde modelar aca
	private Cuenta cuenta;

	public Usuario() {

	}

	public Usuario(String nombre, String apellido, String email, DateTime fechaNac) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNac = fechaNac;
		this.cuenta = new Cuenta();
	}


    public void crearTemplate() {
		
	}
	
	public void crearEvento() {
		
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


    //cambiar estado
	
}
