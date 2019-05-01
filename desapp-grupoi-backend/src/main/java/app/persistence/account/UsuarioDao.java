package app.persistence.account;

import app.model.account.UsuarioPrueba;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UsuarioDao implements IUsuarioDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(UsuarioPrueba employee) {
        entityManager.persist(employee);
    }

    @Override
    public void update(UsuarioPrueba employee) {
        entityManager.merge(employee);
    }

    @Override
    public UsuarioPrueba getEmployeeById(long id) {
        return entityManager.find(UsuarioPrueba.class, id);
    }

    @Override
    public void delete(long id) {
        UsuarioPrueba employee = getEmployeeById(id);
        if (employee != null) {
            entityManager.remove(employee);
        }
    }
}