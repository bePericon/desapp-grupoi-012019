package model;

import java.util.List;

public class RecoleccionPrevia extends Baquita {

	private List<Usuario> compradores;
	
	
	public RecoleccionPrevia(Usuario organizador, List<Usuario> compradores) {
		super(organizador);
		this.compradores= compradores;

	}


	public List<Usuario> getCompradores() {
		return compradores;
	}

	
	
	
	
}
