package model.account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class CreditoTest {

    private Credito creditoTest;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        this.creditoTest = new Credito();
    }

    @After
    public void end(){
        this.creditoTest = null;
    }

    @Test
    public void testCrearCredito_creditoGuardado() {
        assertEquals(EnumEstados.EstadoCredito.GUARDADO, this.creditoTest.getEstado());
    }

    @Test
    public void testMontoCuota_elMontoCuotaDebeSerDoscientos() {
        this.setNuevoCredito(1000,1200,6);

        assertEquals(200, this.creditoTest.getMontoCuota().getMonto(), 0.0);
    }

    @Test
    public void testMontoCuota_elMontoCuotaDebeSerCientoochentaYTresComaPeriodicoDosDecimales() {
        this.setNuevoCredito(1000,1100,6);

        assertEquals(183.33, this.creditoTest.getMontoCuota().getMonto(), 0.009);
    }

    @Test
    public void testSaldarMonto_sePagaPrimerCuotaEstadoEnCurso() {
        this.setNuevoCredito(1000,1200,6);
        Dinero cuota = this.creditoTest.getMontoCuota();
        this.creditoTest.saldarMonto(cuota);

        assertEquals(1000, this.creditoTest.getMontoRestante().getMonto(), 0.0);
        assertEquals(EnumEstados.EstadoCredito.ENCURSO, this.creditoTest.getEstado());
    }

    @Test
    public void testSaldarMonto_sePaganTodasLasCuotasEstadoFinalizado() {
        this.setNuevoCredito(1000,1200,2);
        Dinero cuota = this.creditoTest.getMontoCuota();
        this.creditoTest.saldarMonto(cuota);
        this.creditoTest.saldarMonto(cuota);

        assertEquals(0, this.creditoTest.getMontoRestante().getMonto(), 0.0);
        assertEquals(EnumEstados.EstadoCredito.FINALIZADO, this.creditoTest.getEstado());
    }

    // Methods aux

    private void setNuevoCredito(double monto, double montoDevolver, int cuotas){
        this.creditoTest = new Credito(new Dinero(monto), new Dinero(montoDevolver), cuotas);
    }
}
