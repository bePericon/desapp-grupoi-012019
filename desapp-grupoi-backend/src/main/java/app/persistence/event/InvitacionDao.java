package app.persistence.event;

import app.model.account.Usuario;
import app.model.event.*;
import app.persistence.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;

import java.util.List;

@Repository
public class InvitacionDao extends GenericDao<Evento> {

    @Override
    protected Class getDomainClass() {
        return Evento.class;
    }


    //TODO: implementar
    public List<Invitacion> getInvitacionesPendientes(String email) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Invitacion> cq = cb.createQuery(Invitacion.class);
        Root<Invitacion> invitacion = cq.from(Invitacion.class);
        Join<Invitacion, Usuario> eventoUsuarioJoin = invitacion.join("asistentes", JoinType.LEFT);
        cq.where(cb.equal(eventoUsuarioJoin.get("email"), email));
        cq.select(invitacion);
        List<Invitacion> eventosPublicos = this.entityManager.createQuery(cq).getResultList();

        return eventosPublicos;
    }


}
