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

    @Test
    public void testSaldoActual_CuentaSaldoEnCero() {
        BigDecimal saldo = this.cuentaTest.getSaldo();

        assertEquals(Dinero.getMonto(0), saldo);
    }

    @Test
    public void testDepositarDinero_CuentaSaldoEnMil() {
        this.cuentaTest.depositarDinero(Dinero.getMonto(1000));

        assertEquals(Dinero.getMonto(1000), this.cuentaTest.getSaldo());
    }

    @Test
    public void testDepositarDinero_SeAgregaUnMovimiento() {
        this.cuentaTest.depositarDinero(Dinero.getMonto(100));
        List<Movimiento> movimientos = this.cuentaTest.getMovimientos();

        assertEquals(1, movimientos.size());
    }

    @Test
    public void testDepositarDinero_SeAgregaUnMovimientoTipoDepositar() {
        this.cuentaTest.depositarDinero(Dinero.getMonto(100));
        Movimiento movimiento = this.cuentaTest.getUltimoMovimiento();

        assertEquals(EnumTipos.TipoMovimiento.DEPOSITAR, movimiento.getTipoMovimiento());
    }

    @Test
    public void testRetirarDinero_CuentaSaldoEnQuinientos() {
        this.cuentaTest.depositarDinero(Dinero.getMonto(1000));
        this.cuentaTest.retirarDinero(Dinero.getMonto(500));

        assertEquals(Dinero.getMonto(500), this.cuentaTest.getSaldo());
    }

    @Test
    public void testRetirarDinero_SeAgregaUnMovimientoTipoRetirar() {
        this.cuentaTest.depositarDinero(Dinero.getMonto(1000));
        this.cuentaTest.retirarDinero(Dinero.getMonto(100));
        Movimiento movimiento = this.cuentaTest.getUltimoMovimiento();

        assertEquals(EnumTipos.TipoMovimiento.RETIRAR, movimiento.getTipoMovimiento());
    }

    @Test
    public void testSetTarjetaCredito_DatosCorrectos() {
        this.cuentaTest.setTarjetaCredito("1111222233334444", 123);
        TarjetaCredito tarjeta = this.cuentaTest.getTarjetaCredito();

        assertTrue(tarjeta.getNumero().length() == 16);
        assertTrue(String.valueOf(tarjeta.getCodigo()).length() == 3);
    }

    @Test
    public void testAgregarCredito_SeAgregaUnCredito() {
        this.cuentaTest.agregarCredito(new Credito());
        List<Credito> creditos = this.cuentaTest.getCreditos();

        assertEquals(1, creditos.size());
    }

    @Test
    public void testAgregarCredito_SeAgregaUnCreditoDeMil() {
        this.cuentaTest.agregarCredito(new Credito("1000", 6));
        Credito credito = this.cuentaTest.getUltimoCredito();

        assertEquals(Dinero.getMonto(1000), credito.getMontoTotal());
    }

}
