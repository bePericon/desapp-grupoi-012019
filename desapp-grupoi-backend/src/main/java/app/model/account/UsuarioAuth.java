package app.model.account;

import javax.validation.constraints.NotNull;

public class UsuarioAuth {

	@NotNull
	String given_name;

	@NotNull
	String family_name;

	@NotNull
	String email;

	public UsuarioAuth() {}

	public String getNombre() {
		return given_name;
	}

	public void setApellido(String apellido) {
		this.family_name = apellido;
	}

	public String getApellido() {
		return this.family_name;
	}

	public void setGiven_name(String nombre) {
		this.given_name = nombre;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
//}
