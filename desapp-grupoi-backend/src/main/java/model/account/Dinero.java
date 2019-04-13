package model.account;

import java.math.BigDecimal;

public class Dinero {

    public static BigDecimal getMonto(int monto) {
        return new BigDecimal(monto);
    }

    public static BigDecimal getMonto(String monto) {
        return new BigDecimal(monto);
    }

    public static boolean mayorACero(BigDecimal monto) {
        return monto.compareTo(BigDecimal.ZERO) > 0;
    }

    public static BigDecimal sumar(BigDecimal saldo, BigDecimal monto) {
        return saldo.add(monto);
    }

    public static BigDecimal restar(BigDecimal saldo, BigDecimal monto) {
        return saldo.subtract(monto);
    }
}
