package model.account;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CuentaTest {

    Cuenta cuentaTest;

    @Before
    public void setup(){
        this.cuentaTest = new Cuenta();
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
    public void testSaldoActual_DepositarMil_CuentaSaldoEnMil() {

        cuentaTest.depositarDinero(this.getMonto("1000"));

        BigDecimal saldo = this.cuentaTest.saldoActual();

        assertEquals(this.getMonto("1000"), saldo);
    }

    @Test
    public void testListarEstadoCuenta() {

        assertTrue(true);
    }
}
