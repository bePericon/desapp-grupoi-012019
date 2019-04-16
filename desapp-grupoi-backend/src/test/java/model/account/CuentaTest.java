package model.account;

import static org.junit.Assert.*;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

public class CuentaTest {

    private Cuenta cuentaTest;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        this.cuentaTest = new Cuenta();
    }

    @After
    public void end(){
        this.cuentaTest = null;
    }

    @Test
    public void testSaldoActual_cuentaSaldoEnCero() {
        BigDecimal saldo = this.cuentaTest.getSaldo();

        assertEquals(Dinero.getMonto(0), saldo);
    }

    @Test
    public void testDepositarDinero_cuentaSaldoEnMil() {
        this.cuentaTest.depositarDinero(Dinero.getMonto(1000));

        assertEquals(Dinero.getMonto(1000), this.cuentaTest.getSaldo());
    }

    @Test
    public void testDepositarDinero_ceAgregaUnMovimiento() {
        this.cuentaTest.depositarDinero(Dinero.getMonto(100));
        List<Movimiento> movimientos = this.cuentaTest.getMovimientos();

        assertEquals(1, movimientos.size());
    }

    @Test
    public void testDepositarDinero_seAgregaUnMovimientoTipoDepositar() {
        this.cuentaTest.depositarDinero(Dinero.getMonto(100));
        Movimiento movimiento = this.cuentaTest.getUltimoMovimiento();

        assertEquals(EnumTipos.TipoMovimiento.DEPOSITAR, movimiento.getTipoMovimiento());
    }

    @Test
    public void testRetirarDinero_cuentaSaldoEnQuinientos() {
        this.cuentaTest.depositarDinero(Dinero.getMonto(1000));
        this.cuentaTest.retirarDinero(Dinero.getMonto(500));

        assertEquals(Dinero.getMonto(500), this.cuentaTest.getSaldo());
    }

    @Test
    public void testRetirarDinero_seAgregaUnMovimientoTipoRetirar() {
        this.cuentaTest.depositarDinero(Dinero.getMonto(1000));
        this.cuentaTest.retirarDinero(Dinero.getMonto(100));
        Movimiento movimiento = this.cuentaTest.getUltimoMovimiento();

        assertEquals(EnumTipos.TipoMovimiento.RETIRAR, movimiento.getTipoMovimiento());
    }

    @Test
    public void testSetTarjetaCredito_datosCorrectos() {
        this.cuentaTest.setTarjetaCredito("1111222233334444", 123);
        TarjetaCredito tarjeta = this.cuentaTest.getTarjetaCredito();

        assertEquals(16, tarjeta.getNumero().length());
        assertEquals(3, String.valueOf(tarjeta.getCodigo()).length());
    }

    @Test
    public void testSolicitarCredito_esPosibleAgregarUnCredito() {
        Usuario mockUsuario = Mockito.mock(Usuario.class);
        Mockito.when(mockUsuario.getSituacion()).thenReturn(EnumEstados.EstadoSituacionDeuda.CUMPLIDOR);
        this.cuentaTest.setUsuario(mockUsuario);

        this.cuentaTest.solicitarCredito();
        List<Credito> creditos = this.cuentaTest.getCreditos();

        assertEquals(1, creditos.size());
    }
}
