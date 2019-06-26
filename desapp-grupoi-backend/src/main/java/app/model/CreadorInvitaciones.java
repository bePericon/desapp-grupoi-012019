package app.model;

import app.model.web.Invitaciones;
import app.service.event.InvitacionService;

public class CreadorInvitaciones extends Thread {

    private InvitacionService invitacionService;
    private Invitaciones invitaciones;

    public CreadorInvitaciones(InvitacionService invitacionService, Invitaciones invitaciones) {
        this.invitacionService = invitacionService;
        this.invitaciones = invitaciones;
    }

    @Override
    public void run(){
        this.invitacionService.guardarListaInvitaciones(this.invitaciones);
    }
}
