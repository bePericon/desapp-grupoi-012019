package app.model.web;

public class InvitacionUsuario {

    private long idInvitacion;
    private long idUsuario;

    public InvitacionUsuario(long idInvitacion, long idUsuario) {
        this.idInvitacion = idInvitacion;
        this.idUsuario = idUsuario;
    }

    public long getIdInvitacion() {
        return idInvitacion;
    }

    public void setIdInvitacion(long idInvitacion) {
        this.idInvitacion = idInvitacion;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
