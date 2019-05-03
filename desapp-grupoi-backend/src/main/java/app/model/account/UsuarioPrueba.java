package app.model.account;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuarioprueba")
public class UsuarioPrueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public UsuarioPrueba() {

    }

    public UsuarioPrueba(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public UsuarioPrueba(String nombre, String apellido, String email) {
        this(nombre, apellido);
        this.email = email;
    }

    public UsuarioPrueba(String nombre, String apellido, String email, Date fechaNac) {
        this(nombre, apellido, email);
        this.fechaNac = fechaNac;
    }
    //	GETTERS Y SETTERS
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
    public String getContrasenia(){ return this.contrasenia; }

}
