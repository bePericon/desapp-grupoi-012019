package model.account;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class UsuarioPrueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String apellido;
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHANAC")
    private DateTime fechaNac;

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

    public UsuarioPrueba(String nombre, String apellido, String email, DateTime fechaNac) {
        this(nombre, apellido, email);
        this.fechaNac = fechaNac;
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
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
    public String getContrasenia(){ return this.contrasenia; }

}
