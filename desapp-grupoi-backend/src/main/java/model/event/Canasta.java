package model.event;

import model.account.Dinero;
import model.account.Usuario;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Canasta extends Baquita {
	/*
		Canasta: se presenta la lista de gastos a realizar y cada asistente elige un ítem del cual hacerse cargo.
	 */

	public Canasta() {
		super();
	}

	@Override
	public Dinero getCostoUsuario(Usuario usuario){
		Dinero d = new Dinero(0);
		List<Dinero> lista = this.itemsComprados.stream()
				.filter(itemUsuario -> itemUsuario.getUsuario().getEmail() == usuario.getEmail())
				.map(itemUsuario -> itemUsuario.getItem().getCosto()).collect(toList());
		for (Dinero din : lista){
			d.sumar(din);
		}
		return d;
	}
}
