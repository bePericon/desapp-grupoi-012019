package app.persistence.account;

import app.model.account.UsuarioPrueba;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManagerFactory;

@Repository
public class UsuarioDao implements IUsuarioDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UsuarioDao(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

//    @Override
//    public void create(UsuarioPrueba employee) {
//        entityManager.persist(employee);
//    }
//
//    @Override
//    public void update(UsuarioPrueba employee) {
//        entityManager.merge(employee);
//    }

    @Override
    public UsuarioPrueba getById(long id) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        UsuarioPrueba u = session.get(UsuarioPrueba.class, id);
        session.getTransaction().commit();
        return u;
    }

//    @Override
//    public void delete(long id) {
//        UsuarioPrueba employee = getEmployeeById(id);
//        if (employee != null) {
//            entityManager.remove(employee);
//        }
//    }
}