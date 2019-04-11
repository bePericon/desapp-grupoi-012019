package model.event;

import model.account.Usuario;

import java.util.List;

public abstract class Modalidad {

	private Usuario organizador;
	private List<Usuario> invitados;
	
	//para que clases que heredan usen esto
	public Modalidad(Usuario organizador) {
		super();
		this.organizador = organizador;
	}
	
	//agregar invitado
	//agregar invitados
	
	
}
