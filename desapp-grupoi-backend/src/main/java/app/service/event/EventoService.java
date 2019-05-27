package app.service.event;

import app.error.exception.ExceptionNoContent;
import app.model.account.Cuenta;
import app.model.account.Usuario;
import app.model.event.Evento;
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

    @Override
    protected EventoDao getDao() {
        return this.dao;
    }

    public List<Evento> getAllEventos() {
        List<Evento> eventos = this.getDao().getAllEventosPublicos();
        if (eventos.isEmpty()) {
            throw new ExceptionNoContent("Lista de eventos vacia.");
        }
        return eventos;
    }

    public List<Evento> getEventosByCuentaId(long idCuenta) {
        Cuenta cuenta = this.cuentaService.getCuentaByIdUsuario(idCuenta);
        return cuenta.getEventos();
    }

    public List<Evento> getEventosInvitado(long idUsuario) {
        Usuario usuario = this.usuarioService.getByIdUsuario(idUsuario);
        List<Evento> eventos = this.getDao().getEventosInvitado(usuario.getEmail());
        return eventos;
    }

    public List<Evento> getAllEventosPasados() {
        List<Evento> eventos = this.getDao().getAllEventosPublicosPasados();
        if (eventos.isEmpty()) {
            throw new ExceptionNoContent("Lista de eventos vacia.");
        }
        return eventos;
    }

    public List<Evento> getEventosByCuentaIdPasados(long idCuenta) {
        return this.getEventosByCuentaId(idCuenta).stream().filter(e -> ! e.fechaVigente()).collect(Collectors.toList());
    }

    public List<Evento> getEventosInvitadoPasados(long idUsuario) {
        return this.getEventosInvitado(idUsuario).stream().filter(e -> ! e.fechaVigente()).collect(Collectors.toList());
    }
}
