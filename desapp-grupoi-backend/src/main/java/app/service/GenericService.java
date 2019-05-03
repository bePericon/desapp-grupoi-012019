package app.service;

import app.persistence.GenericDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import utils.HibernateUtil;

import javax.persistence.EntityManagerFactory;
import java.io.Serializable;

public class GenericService<T> extends HibernateUtil {

    private GenericDao<T> dao;

    @Autowired
    public GenericService(EntityManagerFactory factory, GenericDao dao) {
        super(factory);
        this.dao = dao;
    }

    public T getById(final Serializable id) {
        T t = null;
        Session session = null;
        Transaction tsn = null;
        try{
            session = this.getSessionFactory().openSession();
            session.beginTransaction();

            this.dao.setSession(session);
            t = dao.getById(id);

            session.getTransaction().commit();
        }catch (Exception ex) {
            ex.printStackTrace();
            tsn.rollback();
        }
        finally {
            session.close();
            return t;
        }
    }
}
