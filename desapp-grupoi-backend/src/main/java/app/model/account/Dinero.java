package app.model.account;


import javax.persistence.*;

@Entity
@Table(name = "dinero")
public class Dinero {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private double monto;
    private double aux;

    public Dinero() {
    }

    public Dinero(double monto) {
        this.monto = (double)Math.round(monto * 100d) / 100d;
    }

    public double getMonto() {
        return (double)Math.round(this.monto * 100d) / 100d;
    }

    public boolean mayorACero() {
        return this.monto > 0.01;
    }

    public boolean menorACero() {
        return this.monto < 0;
    }

    public Dinero sumar(Dinero monto) {
        this.monto += monto.getMonto();
        return new Dinero(this.monto);
    }

    public Dinero restar(Dinero monto) {
        this.aux = this.monto - monto.getMonto();
        this.monto = new Dinero(this.aux).getMonto();
        return new Dinero(this.monto);
    }

    public boolean mayorIgualA(Dinero monto) {
        return this.monto >= monto.getMonto();
    }

    public Dinero dividir(int divisor) {
        double ret = (double)Math.round((this.monto / divisor) * 100d) / 100d;
        return new Dinero(ret);
    }

    public boolean esCero() {
        return this.monto == 0 || this.monto == 0.01 || this.monto == (-0.01);
    }

    public void abs() {
        this.monto = Math.abs(this.monto);
    }
}
