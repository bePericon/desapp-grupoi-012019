package model.event;

import model.account.Usuario;

public abstract class Baquita extends Modalidad {
/*
		Baquita: En este caso se determina el evento a realizar y sus gastos (comida, alquiler de
			salon, etc) y a partir de la cantidad de asistentes la aplicación determina cuánto debe pagar
			cada uno de ellos.
			La baquita puede tener dos modalidades. En la primera, una o más personas realizan las
			compras, informan lo gastado y luego se divide con los demás asistentes al evento. En la
			segunda, la aplicación calcula un costo estimado total para el evento (se puede valer de
			información propia o ingresada por los usuarios), crea una cuenta común a la cual deberán
			girar su parte los asistentes y una vez reunido el dinero, el organizador puede disponer de los
			mismos para las compras.
	*/
	
	public Baquita(Usuario organizador) {
		super(organizador);
	}
	
}
