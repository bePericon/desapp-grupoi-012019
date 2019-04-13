package model.account;

import static org.junit.Assert.*;

import org.junit.*;

import java.math.BigDecimal;
import java.util.List;

public class CuentaTest {

    Cuenta cuentaTest;

    @Before
    public void setup(){
        this.cuentaTest = new Cuenta();
    }

    @After
    public void setdown(){
        this.cuentaTest = null;
    }

    private BigDecimal getMonto(String monto){
        return new BigDecimal(monto);
    }

    @Test
    public void testSaldoActual_CuentaSaldoEnCero() {
        BigDecimal saldo = this.cuentaTest.saldoActual();

        assertEquals(this.getMonto("0"), saldo);
    }

    @Test
    public void testDepositarDinero_CuentaSaldoEnMil() {
        this.cuentaTest.depositarDinero(this.getMonto("1000"));

        assertEquals(this.getMonto("1000"), this.cuentaTest.saldoActual());
    }

    @Test
    public void testDepositarDinero_SeAgregaUnMovimiento() {
        this.cuentaTest.depositarDinero(this.getMonto("100"));
        List<Movimiento> movimientos = this.cuentaTest.getMovimientos();

        assertEquals(1, movimientos.size());
    }

    @Test
    public void testDepositarDinero_SeAgregaUnMovimientoTipoDepositar() {
        this.cuentaTest.depositarDinero(this.getMonto("100"));
        Movimiento movimiento = this.cuentaTest.ultimoMovimiento();

        assertEquals(EnumTipos.TipoMovimiento.DEPOSITAR, movimiento.getTipoMovimiento());
    }

    @Test
    public void testHaySaldoSuficiente_CuentaSaldoSuficiente() {
        this.cuentaTest.depositarDinero(this.getMonto("1"));

        assertTrue(this.cuentaTest.haySaldoSuficiente());
    }

    @Test
    public void testRetirarDinero_CuentaSaldoEnQuinientos() {
        this.cuentaTest.depositarDinero(this.getMonto("1000"));
        this.cuentaTest.retirarDinero(this.getMonto("500"));

        assertEquals(this.getMonto("500"), this.cuentaTest.saldoActual());
    }

    @Test
    public void testRetirarDinero_SeAgregaUnMovimientoTipoRetirar() {
        this.cuentaTest.depositarDinero(this.getMonto("1000"));
        this.cuentaTest.retirarDinero(this.getMonto("100"));
        Movimiento movimiento = this.cuentaTest.ultimoMovimiento();

        assertEquals(EnumTipos.TipoMovimiento.RETIRAR, movimiento.getTipoMovimiento());
    }

    @Test
    public void testSetTarjetaCredito_DatosCorrectos() {
        this.cuentaTest.setTarjetaCredito("1111222233334444", 123);
        TarjetaCredito tarjeta = this.cuentaTest.getTarjetaCredito();

        assertTrue(tarjeta.getNumero().length() == 16);
        assertTrue(String.valueOf(tarjeta.getCodigo()).length() == 3);
    }
}
