package app.service;

import app.persistence.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import util.HibernateUtil;

import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

public class GenericService<T> extends HibernateUtil {

    private GenericDao<T> dao;

    T obj = null;
    List<T> listObjs = null;

    @Autowired
    public GenericService(EntityManagerFactory factory, GenericDao dao) {
        super(factory);
        this.dao = dao;
    }

    public List<T> getAll() {
        try{
            this.openSessionBeginTransaction();

            this.dao.setSession(this.getSession());
            this.listObjs = this.dao.getAll();

            this.transactionCommit();
        }catch (Exception ex) {
            ex.printStackTrace();
            this.transactionRollback();
        }
        finally {
            this.sessionClose();
            return this.listObjs;
        }
    }

    public T getById(final Serializable id) {
        try{
            this.openSessionBeginTransaction();

            this.dao.setSession(this.getSession());
            this.obj = dao.getById(id);

            this.transactionCommit();
        }catch (Exception ex) {
            ex.printStackTrace();
            this.transactionRollback();
        }
        finally {
            this.sessionClose();
            return this.obj;
        }
    }

    public void save(final T object) {
        try{
            this.openSessionBeginTransaction();

            this.dao.setSession(this.getSession());
            this.dao.save(object);

            this.transactionCommit();
        }catch (Exception ex) {
            ex.printStackTrace();
            this.transactionRollback();
        }
        finally {
            this.sessionClose();
        }
    }

    public void delete(final T object) {
        try{
            this.openSessionBeginTransaction();

            this.dao.setSession(this.getSession());
            this.dao.delete(object);

            this.transactionCommit();
        }catch (Exception ex) {
            ex.printStackTrace();
            this.transactionRollback();
        }
        finally {
            this.sessionClose();
        }
    }

    public void deleteById(final Serializable id) {
        try{
            this.openSessionBeginTransaction();

            this.dao.setSession(this.getSession());
            this.dao.deleteById(id);

            this.transactionCommit();
        }catch (Exception ex) {
            ex.printStackTrace();
            this.transactionRollback();
        }
        finally {
            this.sessionClose();
        }
    }

    protected List<T> executeQueryList(String queryString, Hashtable<String, String> hashtable) {
        try{
            this.openSessionBeginTransaction();

            this.dao.setSession(this.getSession());
            this.listObjs = this.dao.executeQueryList(queryString, hashtable);

            this.transactionCommit();
        }catch (Exception ex) {
            ex.printStackTrace();
            this.transactionRollback();
        }
        finally {
            this.sessionClose();
            return this.listObjs;
        }
    }
}
