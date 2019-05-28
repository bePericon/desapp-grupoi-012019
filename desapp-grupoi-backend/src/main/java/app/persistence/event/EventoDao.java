package app.persistence.event;

import app.model.account.Usuario;
import app.model.event.*;
import app.persistence.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@Repository
public class EventoDao extends GenericDao<Evento> {

    @Override
    protected Class getDomainClass() {
        return Evento.class;
    }

    public List<Evento> getAllEventosPublicos() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Evento> cq = cb.createQuery(Evento.class);
        Root<Evento> evento= cq.from(Evento.class);
        Join<Evento, Template> eventoTemplateJoin = evento.join("template", JoinType.LEFT);
        cq.where(cb.equal(eventoTemplateJoin.get("visibilidad"), EnumTipos.TipoVisibilidad.PUBLICA));
        cq.select(evento);
        List<Evento> eventosPublicos = this.entityManager.createQuery(cq).getResultList();

        return eventosPublicos;
    }

    public List<Evento> getEventosInvitado(String email) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Evento> cq = cb.createQuery(Evento.class);
        Root<Evento> evento= cq.from(Evento.class);
        Join<Evento, Usuario> eventoUsuarioJoin = evento.join("asistentes", JoinType.LEFT);
        cq.where(cb.equal(eventoUsuarioJoin.get("email"), email));
        cq.select(evento);
        List<Evento> eventosPublicos = this.entityManager.createQuery(cq).getResultList();

        return eventosPublicos;
    }

    public List<Evento> getAllEventosPublicosPasados() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Evento> query = cb.createQuery(Evento.class);
        Root<Evento> evento= query.from(Evento.class);

        Join<Evento, Template> eventoTemplateJoin = evento.join("template", JoinType.LEFT);
        Join<Template, Modalidad> templateModalidadJoin = eventoTemplateJoin.join("modalidad", JoinType.LEFT);

        query
            .select(evento)
            .where(
                    cb.equal(eventoTemplateJoin.get("visibilidad"), EnumTipos.TipoVisibilidad.PUBLICA),
                    cb.lessThan(templateModalidadJoin.get("fechaLimite"), new Date(System.currentTimeMillis()))
            );

        List<Evento> eventosPublicos = this.entityManager.createQuery(query).getResultList();

        return eventosPublicos;
    }

    public List<Evento> getEventosInvitadoEnCurso(String email) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Evento> query = cb.createQuery(Evento.class);
        Root<Evento> evento= query.from(Evento.class);

        Join<Evento, Usuario> eventoUsuarioJoin = evento.join("asistentes", JoinType.LEFT);
        Join<Evento, Template> eventoTemplateJoin = evento.join("template", JoinType.LEFT);
        Join<Template, Modalidad> templateModalidadJoin = eventoTemplateJoin.join("modalidad", JoinType.LEFT);

        query
            .select(evento)
            .where(
                    cb.equal(eventoUsuarioJoin.get("email"), email),
                    cb.greaterThan(templateModalidadJoin.get("fechaLimite"), new Date(System.currentTimeMillis()))
            );

        List<Evento> eventosPublicos = this.entityManager.createQuery(query).getResultList();

        return eventosPublicos;
    }
}
