package persistence.account;

import model.account.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsuarioDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List getAll(){
        return this.entityManager
                .createQuery("SELECT u FROM Usuario")
                .getResultList();
    }

    public void create(Usuario usuario) {
        entityManager.persist(usuario);
    }


    public void update(Usuario usuario) {
        entityManager.merge(usuario);
    }


    public Usuario getUsuarioById(long id) {
        return entityManager.find(Usuario.class, id);
    }


    public void delete(long id) {
        Usuario usuario = getUsuarioById(id);
        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }
}
