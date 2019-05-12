package app.model.account;

import app.model.account.EnumEstados.EstadoCredito;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "credito")
public class Credito {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade={CascadeType.ALL, CascadeType.REMOVE})
    private Usuario usuarioSolicitante;

    @OneToOne(cascade={CascadeType.ALL, CascadeType.REMOVE})
    private Dinero monto;

    private int cuotas;

    @OneToOne(cascade={CascadeType.ALL, CascadeType.REMOVE})
    private Dinero montoRestante;

    private int cuotasRestantes;

    @Enumerated(EnumType.STRING)
    private EstadoCredito estado;

    public Credito(){
    }

    public Credito(Dinero monto, Dinero montoADevolver, int cuotas, Usuario usuario) {
        this();
        this.monto = monto;
        this.cuotas = cuotas;
        this.montoRestante = montoADevolver;
        this.cuotasRestantes = cuotas;
        this.usuarioSolicitante = usuario;
        this.estado = EstadoCredito.GUARDADO;
    }

    public Dinero getMontoCuota() {
        return new Dinero (this.montoRestante.getMonto()/ this.cuotasRestantes);
    }

    public void saldarMonto(Dinero cuota) {
        this.montoRestante.restar(cuota);
        this.cuotasRestantes -= 1;
        if(this.cuotasRestantes == 0)
            this.estado = EstadoCredito.FINALIZADO;
        else
            this.estado = EstadoCredito.ENCURSO;
    }
}
