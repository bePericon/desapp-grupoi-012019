package model.account;

import static org.junit.Assert.*;

import app.model.account.*;
import app.model.event.Evento;
import app.model.event.Invitacion;
import app.model.event.Template;
import org.junit.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
    public void testCrearCuentaNuevaDatosObligatorios() {
        Cuenta cuenta = new Cuenta(Mockito.mock(Usuario.class));

        assertNotNull(cuenta.getUsuario());
        assertEquals(EnumEstados.EstadoSituacionDeuda.NORMAL, cuenta.getSituacion());
        assertEquals(0, cuenta.getSaldo().getMonto(), 0.0);
    }

    @Test
    public void testSaldoActualCuentaSaldoEnCero() {
        Dinero saldo = this.cuentaTest.getSaldo();

        assertEquals(0, saldo.getMonto(), 0.0);
    }

    @Test
    public void testDepositarDineroCuentaSaldoEnMil() {
        this.cuentaTest.depositarDinero(new Dinero(1000));

        assertEquals(1000, this.cuentaTest.getSaldo().getMonto(), 0.0);
    }

    @Test
    public void testDepositarDineroSeAgregaUnMovimiento() {
        this.cuentaTest.depositarDinero(new Dinero(100));
        List<Movimiento> movimientos = this.cuentaTest.getMovimientos();

        assertEquals(1, movimientos.size());
    }

    @Test
    public void testDepositarDineroSeAgregaUnMovimientoTipoDepositar() {
        this.cuentaTest.depositarDinero(new Dinero(100));
        Movimiento movimiento = this.cuentaTest.getUltimoMovimiento();

        assertEquals(EnumTipos.TipoMovimiento.DEPOSITAR, movimiento.getTipoMovimiento());
    }

    @Test
    public void testRetirarDineroCuentaSaldoEnQuinientos() {
        this.cuentaTest.depositarDinero(new Dinero(1000));
        this.cuentaTest.retirarDinero(new Dinero(500));

        assertEquals(500, this.cuentaTest.getSaldo().getMonto(), 0.0);
    }

    @Test
    public void testRetirarDineroSeAgregaUnMovimientoTipoRetirar() {
        this.cuentaTest.depositarDinero(new Dinero(1000));
        this.cuentaTest.retirarDinero(new Dinero(100));
        Movimiento movimiento = this.cuentaTest.getUltimoMovimiento();

        assertEquals(EnumTipos.TipoMovimiento.RETIRAR, movimiento.getTipoMovimiento());
    }

    @Test
    public void testSetTarjetaCreditoDatosCorrectos() {
        this.cuentaTest.setTarjetaCredito("1111222233334444", 123);
        TarjetaCredito tarjeta = this.cuentaTest.getTarjetaCredito();

        assertEquals(16, tarjeta.getNumero().length());
        assertEquals(3, String.valueOf(tarjeta.getCodigo()).length());
    }

    @Test
    public void testEstadoSituacionDeudaUsuarioSituacionNormal() {
        this.cuentaTest.setSituacionDeuda(EnumEstados.EstadoSituacionDeuda.NORMAL);

        assertTrue(this.cuentaTest.getSituacion().esNormal());
    }
    @Test
    public void testEstadoSituacionDeudaUsuarioSituacionCumplidor() {
        this.cuentaTest.setSituacionDeuda(EnumEstados.EstadoSituacionDeuda.CUMPLIDOR);

        assertTrue(this.cuentaTest.getSituacion().esCumplidor());
    }
    @Test
    public void testEstadoSituacionDeudaUsuarioSituacionMoroso() {
        this.cuentaTest.setSituacionDeuda(EnumEstados.EstadoSituacionDeuda.MOROSO);

        assertTrue(this.cuentaTest.getSituacion().esMoroso());
    }

    @Test
    public void testSolicitarCreditoEsPosibleAgregarUnCredito() {
        this.cuentaTestConCredito();

        assertEquals(1, this.cuentaTest.getCreditos().size());
    }

    @Test
    public void testDebitarCuotaCreditoEsPosibleDebitarLaPrimerCuota() {
        this.cuentaTestConCredito();
        this.cuentaTest.debitarCuotaCredito();
        Dinero saldoDespues = this.cuentaTest.getSaldo();

        assertEquals(800, saldoDespues.getMonto(),0.0);
    }

    @Test
    public void testDebitarCuotaCreditoNoEsPosibleDebitarLaCuotaNoHaySaldoUsuarioMoroso() {
        this.cuentaTestConCredito();
        this.cuentaTestSinSaldo();
        this.cuentaTest.debitarCuotaCredito();

        assertEquals(0, this.cuentaTest.getSaldo().getMonto(), 0.0);
        assertTrue(this.cuentaTest.getSituacion().esMoroso());
    }

    @Test
    public void testAgregarInvitacionSeAgregaUnaInvitacion() {
        this.cuentaTest.agregarInvitacion(Mockito.mock(Invitacion.class));

        assertEquals(1, this.cuentaTest.getInvitaciones().size());
    }

    @Test
    public void testAgregarEventoSeAgregaUnEvento() {
        this.cuentaTest.agregarEvento(Mockito.mock(Evento.class));

        assertEquals(1, this.cuentaTest.getEventos().size());
    }

    @Test
    public void testAgregarTemplateSeAgregaUnTemplate() {
        this.cuentaTest.agregarTemplate(Mockito.mock(Template.class));

        assertEquals(1, this.cuentaTest.getTemplates().size());
    }

    // Methods aux
    private void cuentaTestConCredito() {
        this.cuentaTest.setSituacionDeuda(EnumEstados.EstadoSituacionDeuda.CUMPLIDOR);
        this.cuentaTest.solicitarCredito();
    }

    private void cuentaTestSinSaldo() {
        Dinero saldo = this.cuentaTest.getSaldo();
        this.cuentaTest.retirarDinero(saldo);
    }
}
