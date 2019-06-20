package app.model.event;

import app.model.account.Dinero;
import app.model.account.Usuario;
import app.model.event.EnumTipos.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class BaquitaCompraPrevia extends Baquita {
	/*
		Baquita: En este caso se determina el evento a realizar y sus gastos 
		La baquita puede tener dos modalidades. En la primera, una o más personas realizan las
		compras, informan lo gastado y luego se divide con los demás asistentes al evento. 
*/


	public BaquitaCompraPrevia() {	}

	public BaquitaCompraPrevia(Date fechaLimite){
		super(fechaLimite);
		this.tipoModalidad = TipoModalidad.BAQUITA_COMPRA;
	}

	@Override
	public void calcularCostos(List<Usuario> asistentes) {
		super.calcularCostos(asistentes);

		List<Usuario> compradores;
		compradores = this.itemsComprados.stream()
				.map(itemUsuario -> itemUsuario.getUsuario())
				.distinct().collect(Collectors.toList());
		List<Usuario> asistentesSinCompras = asistentes.stream()
				.filter(usuario -> !compradores.contains(usuario)).collect(Collectors.toList());

		List<Deuda> deudasCobradores = new ArrayList<>();
		List<Deuda> deudasDeudores = new ArrayList<>();

		// Se arman las deudas con los compradores
		for (Usuario comprador : compradores){
			Dinero compradorGasto = this.getCostoUsuario(comprador);
			compradorGasto.restar(this.costoUsuario);
			if(compradorGasto.mayorACero())
				deudasCobradores.add(new Deuda(compradorGasto, comprador));
			else {
				compradorGasto.abs();
				deudasDeudores.add(new Deuda(comprador, compradorGasto));
			}
		}
		// Se arman las deudas con los asistentes
		for (Usuario asistenteSC : asistentesSinCompras) {
			deudasDeudores.add(new Deuda(asistenteSC, this.costoUsuario));
		}
		// Se arman las deudas finales
		Dinero diferencia;
		for (Deuda dCobrador : deudasCobradores) {
			diferencia = dCobrador.getMonto();
			for (Deuda dDeudor : deudasDeudores) {
				diferencia.restar(dDeudor.getMonto());
				if(diferencia.esCero() || diferencia.mayorACero()){
					this.deudas.add(new Deuda(dDeudor.getDeudor(), dDeudor.getMonto(), dCobrador.getCobrador()));
				}
			}
		}
	}

	@Override
	public Dinero getCostoUsuario(Usuario usuario){
		Dinero d = new Dinero(0);
		List<Dinero> lista = this.itemsComprados.stream()
				.filter(itemUsuario -> itemUsuario.getUsuario().getEmail() == usuario.getEmail())
				.map(itemUsuario -> itemUsuario.getItem().getCosto()).collect(Collectors.toList());
		if(!lista.isEmpty()){
			for (Dinero din : lista){
				d.sumar(din);
			}
			return d;
		}else{
			return this.getCostoUsuario();
		}
	}
}
