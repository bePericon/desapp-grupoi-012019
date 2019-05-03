package app.persistence.account;

import app.model.account.Usuario;
import app.persistence.GenericDao;
import app.persistence.IGenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDao extends GenericDao<Usuario> implements IGenericDao<Usuario> {

    @Override
    protected Class getDomainClass() {
        return Usuario.class;
    }
}