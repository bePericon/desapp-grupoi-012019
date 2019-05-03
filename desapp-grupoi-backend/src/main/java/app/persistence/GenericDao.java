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


    public void save(T entity){}

    public void delete(T entity){}

    public void update(T entity){}

    public T getById(final Serializable id) {
        return this.session.get(this.persistentClass, id);
    }

    public List<T> getAll(){
        return new ArrayList<>();
    }

    public void deleteById(Serializable id){}

    public int count(){
        return 0;
    }
}
