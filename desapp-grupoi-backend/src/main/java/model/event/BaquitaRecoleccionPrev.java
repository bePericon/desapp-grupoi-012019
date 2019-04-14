package model.event;

import model.account.Usuario;

import java.util.List;

public class BaquitaRecoleccionPrev extends Baquita {

	private List<Usuario> compradores;
	
	
	public BaquitaRecoleccionPrev(Usuario organizador, List<Usuario> compradores) {
		super(organizador);
		this.compradores= compradores;

	}

	public List<Usuario> getCompradores() {
		return compradores;
	}

	
	
	
	
}
