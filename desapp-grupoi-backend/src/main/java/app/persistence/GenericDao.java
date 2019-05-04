package app.persistence;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDao<T> implements IGenericDao<T>, Serializable {

    private Session session;
    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }


    protected Class<T> persistentClass = this.getDomainClass();
    protected abstract Class<T> getDomainClass();

    public void save(T entity){
        this.session.saveOrUpdate(entity);
    }

    public void delete(T entity) {
        this.session.delete(entity);
    }

    public void deleteById(Serializable id){
        T obj = this.getById(id);
        this.delete(obj);
    }

    public T getById(final Serializable id) {
        return this.session.get(this.persistentClass, id);
    }

    public List<T> getAll(){
        List<T> objs = this.session.createQuery("from "+this.persistentClass.getName()+" o").list();
        return objs;
    }

    public int count(){
        List<Long> list = this.session.createQuery("select count(*) from " + this.persistentClass.getName() + " o").list();
        Long count = list.get(0);
        return count.intValue();
    }
}
