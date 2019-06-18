package app.persistence.event;

import app.model.account.Usuario;
import app.model.event.*;
import app.persistence.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;

import java.util.List;

@Repository
public class InvitacionDao extends GenericDao<Invitacion> {

    @Override
    protected Class getDomainClass() {
        return Invitacion.class;
    }

}
