package model.account;

public class Dinero {

    private double monto;

    public Dinero(double monto) {
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

    public boolean mayorACero() {
        return this.monto > 0;
    }

    public double sumar(Dinero monto) {
        return this.monto += monto.getMonto();
    }

    public double restar(Dinero monto) {
        return this.monto -= monto.getMonto();
    }

    public boolean mayorIgualA(Dinero monto) {
        return this.monto >= monto.getMonto();
    }

    public Dinero dividir(int divisor) {
        return new Dinero(this.monto / divisor);
    }
}
