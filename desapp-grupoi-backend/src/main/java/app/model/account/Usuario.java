package app.model.account;

import app.model.event.Invitacion;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
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

	@Column(name="contrasenia")
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

	public void agregarInvitacion(Invitacion inv) {
		this.invitaciones.add(inv);
	}
}
