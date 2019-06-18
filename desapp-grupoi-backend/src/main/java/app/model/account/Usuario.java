package app.model.account;

import app.model.event.Invitacion;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String nombre;

	@Column
	private String apellido;

	@Column
	private String email;

	@Column
	//@Temporal(TemporalType.DATE)
	private Date fechaNac;

	@Column
	private String contrasenia;

//	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	@PrimaryKeyJoinColumn
//	private Cuenta cuenta;

//	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private List<Invitacion> invitaciones;
	
	public Usuario() {

	}

	public Usuario(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
//		this.cuenta = new Cuenta(this);
//		this.invitaciones = new ArrayList<Invitacion>();
	}

	public Usuario(String nombre, String apellido, String email) {
		this(nombre, apellido);
		this.email = email;
	}

	public Usuario(String nombre, String apellido, String email, Date fechaNac) {
		this(nombre, apellido, email);
		this.fechaNac = fechaNac;
	}

	public Usuario(String nombre, String apellido, String email, String contrasenia) {
		this(nombre, apellido, email);
		this.contrasenia = contrasenia;
	}

	// Metodo de clase usado para creacion de usuario desde un responseBody
	public static Usuario build(Usuario u){
		Usuario usuario = new Usuario(u.nombre,u.apellido,u.email,u.fechaNac);
		usuario.contrasenia = u.getContrasenia();
		return usuario;
	}

	public boolean esContraseniaCorrecta(String contra) {
		return this.contrasenia.equals(contra);
	}

//	public void agregarInvitacion(Invitacion inv) {
//		this.invitaciones.add(inv);
//	}

	public void actualizar(Usuario usuario) {
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.email = usuario.getEmail();
		this.fechaNac = usuario.getFechaNac();
		this.contrasenia = usuario.getContrasenia();
	}

//	public boolean tieneInvitacionesPendientes() {
//		return this.invitaciones.size() > 0 && this.invitaciones.stream().anyMatch(inv -> inv.estaPendiente());
//	}

	//Getters y setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

//	public List<Invitacion> getInvitaciones() {
//		return invitaciones;
//	}

//	public void setInvitaciones(List<Invitacion> invitaciones) {
//		this.invitaciones = invitaciones;
//	}

}

