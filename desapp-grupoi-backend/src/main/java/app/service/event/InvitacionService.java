package app.service.event;

import app.model.account.Usuario;
import app.model.event.Evento;
import app.model.event.Invitacion;

import app.persistence.event.InvitacionDao;
import app.service.GenericService;
import app.service.account.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvitacionService extends GenericService<Evento> {

    @Autowired
    private InvitacionDao dao;

    // Services
//    @Autowired
//    private CuentaService cuentaService;
    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected InvitacionDao getDao() {
        return this.dao;
    }

    /*
     * retorna las invitaciones del usuario
     */
    public List<Invitacion> getInvitacionesEnCurso(long idUsuario) {
        Usuario usuario = this.usuarioService.getByIdUsuario(idUsuario);
        List<Invitacion> invitaciones = this.getDao().getInvitacionesPendientes(usuario.getEmail());
        return invitaciones;
    }
}
