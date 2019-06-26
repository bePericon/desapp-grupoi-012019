package app.service.event;

import app.error.exception.ExceptionNoContent;
import app.model.CreadorInvitaciones;
import app.model.account.Cuenta;
import app.model.account.Usuario;
import app.model.event.Evento;
import app.model.event.Invitacion;
import app.model.event.Template;
import app.model.web.Invitaciones;
import app.model.web.NewEvento;
import app.persistence.event.EventoDao;
import app.service.GenericService;
import app.service.account.CuentaService;
import app.service.account.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventoService extends GenericService<Evento> {

    @Autowired
    private EventoDao dao;

    // Services
    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private InvitacionService invitacionService;

    @Override
    protected EventoDao getDao() {
        return this.dao;
    }

    /*
     * Retorna todos los eventos Publicos.
     */
    public List<Evento> getAllEventos() {
        List<Evento> eventos = this.getDao().getAllEventosPublicos();
        if (eventos.isEmpty()) {
            throw new ExceptionNoContent("Lista de eventos vacia.");
        }
        return eventos;
    }

    /*
     * Retorna todos los eventos que fueron creados por una cuenta.
     */
    public List<Evento> getEventosByCuentaId(long idCuenta) {
        Cuenta cuenta = this.cuentaService.getCuentaByIdUsuario(idCuenta);
        return cuenta.getEventos();
    }

    /*
     * Retorna todos los eventos donde el usuario esta invitado.
     */
    public List<Evento> getEventosInvitado(long idUsuario) {
        Usuario usuario = this.usuarioService.getByIdUsuario(idUsuario);
        List<Evento> eventos = this.getDao().getEventosInvitado(usuario.getEmail());
        return eventos;
    }

    /*
     * Retorna todos los eventos publicos que ya vencieron (su fecha limite ya paso).
     */
    public List<Evento> getAllEventosPasados() {
        List<Evento> eventos = this.getDao().getAllEventosPublicosPasados();
        if (eventos.isEmpty()) {
            throw new ExceptionNoContent("Lista de eventos vacia.");
        }
        return eventos;
    }

    /*
     * Retorna todos los eventos vencidos que fueron creados por una cuenta.
     */
    public List<Evento> getEventosPasadosByIdCuenta(long idCuenta) {
        return this.getEventosByCuentaId(idCuenta).stream().filter(e -> ! e.fechaVigente()).collect(Collectors.toList());
    }

    /*
     * Retorna todos los eventos vencidos donde el usuario esta invitado.
     */
    public List<Evento> getEventosMeInvitaronPasados(long idUsuario) {
        return this.getEventosInvitado(idUsuario).stream().filter(e -> ! e.fechaVigente()).collect(Collectors.toList());
    }

    /*
     * Retorna todos los eventos en curso donde el usuario esta invitado.
     */
    public List<Evento> getEventosInvitadoEnCurso(long idUsuario) {
        Usuario usuario = this.usuarioService.getByIdUsuario(idUsuario);
        List<Evento> eventos = this.getDao().getEventosInvitadoEnCurso(usuario.getEmail());
        return eventos;
    }

    /*
     * Crea un nuevo evento.
     */
    public Evento createNuevoEvento(long idUsuario, NewEvento nuevoEvento) {
        Template template = this.templateService.createNuevoTemplate(idUsuario, nuevoEvento.getNuevoTemplate());
        Usuario organizador = this.usuarioService.getByIdUsuario(idUsuario);
        Evento evento = new Evento(organizador, nuevoEvento.getNombre());
        Cuenta cuenta = this.cuentaService.getCuentaByIdUsuario(idUsuario);
        evento.setTemplate(template);

        cuenta.agregarEvento(evento);
        this.cuentaService.update(cuenta);
        cuenta = this.cuentaService.getCuentaByIdUsuario(idUsuario);
        evento = cuenta.obtenerUltimoEvento();

        Invitaciones invitaciones = new Invitaciones(nuevoEvento.getInvitados(), evento);
        CreadorInvitaciones creador = new CreadorInvitaciones(this.invitacionService, invitaciones);
        creador.start();

        return evento;
    }
}
