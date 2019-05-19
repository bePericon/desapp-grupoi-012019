package app.model.account;

import app.model.event.Invitacion;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
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

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Invitacion> invitaciones;
	
	public Usuario() {

	}

	public Usuario(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
//		this.cuenta = new Cuenta(this);
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

	public void actualizar(Usuario usuario) {
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.email = usuario.getEmail();
		this.fechaNac = usuario.getFechaNac();
		this.contrasenia = usuario.getContrasenia();
	}

	public boolean tieneInvitacionesPendientes() {
		return this.invitaciones.size() > 0 && this.invitaciones.stream().anyMatch(inv -> inv.estaPendiente());
	}
}
