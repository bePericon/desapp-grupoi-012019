package app.persistence.account;

import app.model.account.Usuario;
import app.persistence.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


@Repository
public class UsuarioDao extends GenericDao<Usuario> {

    public UsuarioDao() {
        super();
    }

    @Override
    protected Class getDomainClass() {
        return Usuario.class;
    }

    public Usuario getByEmail(String email) {
        Query query = this.entityManager.createQuery("from " + this.persistentClass.getName() + " where email = :email");
        query.setParameter("email", email);
        return (query.getResultList().size() > 0) ? (Usuario) query.getResultList().get(0) : null;
    }
}