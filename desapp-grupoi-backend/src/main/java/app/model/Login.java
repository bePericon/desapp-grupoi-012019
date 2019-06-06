package app.model;

public class Login {

    private String email;
    private String contrasenia;

    public Login(String email, String contrasenia) {
        this.email = email;
        this.contrasenia = contrasenia;
    }

    //Getters y setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
