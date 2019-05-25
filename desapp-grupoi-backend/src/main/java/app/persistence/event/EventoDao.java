package app.persistence.event;

import app.model.account.Usuario;
import app.model.event.EnumTipos;
import app.model.event.Evento;
import app.model.event.Invitacion;
import app.model.event.Template;
import app.persistence.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class EventoDao extends GenericDao<Evento> {

    @Override
    protected Class getDomainClass() {
        return Evento.class;
    }

    public List<Evento> getAllEventosPublicos() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        // Uso de criteria
        CriteriaQuery<Evento> cq = cb.createQuery(Evento.class);
        // Creamos el root: select * from ENTIDAD
        Root<Evento> evento= cq.from(Evento.class);
        // Join
        Join<Evento, Template> eventoTemplateJoin = evento.join("template", JoinType.LEFT);
        // Clausula Where
        cq.where(cb.equal(eventoTemplateJoin.get("visibilidad"), EnumTipos.TipoVisibilidad.PUBLICA));
        cq.select(evento);
        List<Evento> eventosPublicos = this.entityManager.createQuery(cq).getResultList();

        return eventosPublicos;
    }

    public List<Evento> getEventosInvitado(String email) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        // Uso de criteria
        CriteriaQuery<Evento> cq = cb.createQuery(Evento.class);
        // Creamos el root: select * from ENTIDAD
        Root<Evento> evento= cq.from(Evento.class);
        // Join
        Join<Evento, Usuario> eventoUsuarioJoin = evento.join("asistentes", JoinType.LEFT);
        // Clausula Where
        cq.where(cb.equal(eventoUsuarioJoin.get("email"), email));
        cq.select(evento);
        List<Evento> eventosPublicos = this.entityManager.createQuery(cq).getResultList();

        return eventosPublicos;
    }
}
