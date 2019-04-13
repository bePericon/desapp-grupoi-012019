package model.account;

import org.joda.time.DateTime;
import java.math.BigDecimal;
import model.account.EnumTipos.TipoMovimiento;

public class Movimiento {

    private TipoMovimiento tipoMovimiento;
    private DateTime fecha;
    private BigDecimal monto;

    public Movimiento(TipoMovimiento tipo, DateTime fecha, BigDecimal monto) {
        this.tipoMovimiento = tipo;
        this.fecha = fecha;
        this.monto = monto;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }
}
