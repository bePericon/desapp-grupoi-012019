package app.model.account;

import org.joda.time.DateTime;
import app.model.account.EnumTipos.TipoMovimiento;

public class Movimiento {

    private TipoMovimiento tipoMovimiento;
    private DateTime fecha;
    private Dinero monto;

    public Movimiento(TipoMovimiento tipo, DateTime fecha, Dinero monto) {
        this.tipoMovimiento = tipo;
        this.fecha = fecha;
        this.monto = monto;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }
}
