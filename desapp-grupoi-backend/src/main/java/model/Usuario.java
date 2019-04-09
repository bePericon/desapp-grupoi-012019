package model;

import java.util.Date;

public class Usuario {

	private String nombre;
	private String apellido;
	private String email;
	private Date fechaNac;
	private TarjetaCredito tarjCredito;
	private SituacionDeuda situacionDeuda;
//	contrasenia
	
	public Usuario(String nombre, String apellido, String email, Date fechaNac) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNac = fechaNac;
		this.situacionDeuda = new SituacionNormal();
	}
	
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
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	//cambiar estado
	
}
