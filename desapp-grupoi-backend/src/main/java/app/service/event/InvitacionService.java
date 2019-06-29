package app.service.event;

import app.error.exception.ExceptionConflict;
import app.model.account.Cuenta;
import app.model.account.Usuario;
import app.model.event.Evento;
import app.model.event.Invitacion;

import app.model.web.InvitacionUsuario;
import app.model.web.Invitaciones;
import app.persistence.event.InvitacionDao;
import app.service.EmailSenderService;
import app.service.GenericService;
import app.service.account.CuentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvitacionService extends GenericService<Invitacion> {

    @Autowired
    private InvitacionDao dao;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    protected InvitacionDao getDao() {
        return this.dao;
    }

    /*
     * Retorna invitaciones pendientes del usuario.
     */
    public List<Invitacion> getInvitacionesPendientes(long idUsuario) {
        Cuenta cuenta = this.cuentaService.getCuentaByIdUsuario(idUsuario);
        List<Invitacion> invitaciones = cuenta.getInvitaciones().stream()
                .filter(i -> i.estaPendiente()).collect(Collectors.toList());
        return invitaciones;
    }

    /*
     * Retorna las invitaciones en curso del usuario (las aceptadas y que el evento todavia empezo)
     */
    public List<Invitacion> getInvitacionesAceptadasEnCurso(long idUsuario) {
        Cuenta cuenta = this.cuentaService.getCuentaByIdUsuario(idUsuario);
        List<Invitacion> invitaciones = cuenta.getInvitaciones().stream()
                .filter(i -> i.estaAceptada() && i.getEvento().fechaVigente())
                .collect(Collectors.toList());
        return invitaciones;
    }

    /*
     * Retorna las invitaciones pasadas del usuario (las que son de eventos no vigentes)
     */
    public List<Invitacion> getInvitacionesPasadas(long idUsuario) {
        Cuenta cuenta = this.cuentaService.getCuentaByIdUsuario(idUsuario);
        List<Invitacion> invitaciones = cuenta.getInvitaciones().stream()
                .filter(i -> !i.getEvento().fechaVigente()).collect(Collectors.toList());
        return invitaciones;
    }

    /*
     * Retorna las invitaciones pasadas del usuario (las que son de eventos no vigentes)
     */
    public List<Invitacion> getInvitacionesRechazadas(long idUsuario) {
        Cuenta cuenta = this.cuentaService.getCuentaByIdUsuario(idUsuario);
        List<Invitacion> invitaciones = cuenta.getInvitaciones().stream()
                .filter(i -> i.estaRechazada()).collect(Collectors.toList());
        return invitaciones;
    }

    public void guardarListaInvitaciones(Invitaciones invitaciones) {
        Evento evento = invitaciones.getEvento();
        String nombreApellido = evento.getOrganizador().getNombre()+" "+evento.getOrganizador().getApellido();
        for (String email : invitaciones.getEmails()) {
            Cuenta cuenta =  this.cuentaService.getByUsuarioEmailWithException(email);
            if(cuenta != null){
                cuenta.agregarInvitacion(new Invitacion(email,evento));
                this.cuentaService.update(cuenta);
                this.emailSenderService.enviarEmailInvitacion(nombreApellido, email);
            }
        }
    }

    public Invitacion confirmarInvitacion(InvitacionUsuario invitacionUsuario) {
        return this.ConfirmarRechazarInvitacion(invitacionUsuario, true);
    }

    public Invitacion rechazarInvitacion(InvitacionUsuario invitacionUsuario) {
        return this.ConfirmarRechazarInvitacion(invitacionUsuario, false);
    }

    private Invitacion ConfirmarRechazarInvitacion(InvitacionUsuario invitacionUsuario, boolean accion) {
        long idInvitacion = invitacionUsuario.getIdInvitacion();
        long idUsuario = invitacionUsuario.getIdUsuario();

        Invitacion invitacion = this.getDao().getById(idInvitacion);

        // Validacion para los casos que no contemplamos.
        if(!invitacion.estaPendiente())
            throw new ExceptionConflict("Invitacion en estado incorrecto, estado actual: "+ invitacion.getEstadoInvitacion().toString());
        /////////////////////////////////

        Cuenta cuenta = this.cuentaService.getCuentaByIdUsuario(idUsuario);

        if(accion)
            invitacion.confirmar(cuenta.getUsuario());
        else
            invitacion.rechazar();

        this.getDao().update(invitacion);

        return invitacion;
    }
}
