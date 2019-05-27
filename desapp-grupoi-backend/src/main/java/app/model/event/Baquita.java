package app.model.event;



import app.model.account.Dinero;
import app.model.account.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
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

	@OneToOne(cascade=CascadeType.ALL)
	protected Dinero costoUsuario;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	protected List<Deuda> deudas;

	public Baquita(){}

	public Baquita(Date fechaLimite) {
		super();
		this.fechaLimite = fechaLimite;
		this.costoUsuario = new Dinero(0);
		this.deudas = new ArrayList<Deuda>();
	}

	@Override
	public void calcularCostos(List<Usuario> asistentes) {
		this.costoTotal = new Dinero(0);
		for (Item i : this.itemsAComprar)
			this.costoTotal.sumar(i.getCosto());

		if(this.costoTotal.mayorACero())
			this.costoUsuario = this.costoTotal.dividir(asistentes.size());
	}

	@Override
	public Dinero getCostoUsuario(){
		return this.costoUsuario;
	}

	public int getCantidadDeudas(){
		return this.deudas.size();
	}

	public List<Deuda> getDeudas() {
		return deudas;
	}
}
