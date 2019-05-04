package app.model.account;

import app.model.event.Invitacion;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="nombre")
	private String nombre;

	@Column(name="apellido")
	private String apellido;

	@Column(name="email")
	private String email;

	@Column(name = "fechaNac")
	@Temporal(TemporalType.DATE)
	private Date fechaNac;

	@Column(name="contraseña")
	private String contrasenia;

	@OneToOne(cascade={CascadeType.ALL, CascadeType.REMOVE})
	private Cuenta cuenta;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Invitacion> invitaciones;
	
	public Usuario() {

	}

	public Usuario(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuenta = new Cuenta(this);
		this.invitaciones = new ArrayList<Invitacion>();
	}

	public Usuario(String nombre, String apellido, String email) {
		this(nombre, apellido);
		this.email = email;
	}

	public Usuario(String nombre, String apellido, String email, Date fechaNac) {
		this(nombre, apellido, email);
		this.fechaNac = fechaNac;
	}

	public boolean esContraseniaCorrecta(String contra) {
		return this.contrasenia.equals(contra);
	}

	public void agregarInvitacion(Invitacion inv) {
		this.invitaciones.add(inv);
	}

//	GETTERS Y SETTERS
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
	public void setCuenta(Cuenta cuenta) { this.cuenta = cuenta; }
	public Cuenta getCuenta() { return this.cuenta; }
	public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
	public String getContrasenia(){ return this.contrasenia; }
	public List<Invitacion> getInvitaciones() {
		return invitaciones;
	}
}
