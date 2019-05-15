package app.persistence;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Repository
public abstract class GenericDao<T> implements IGenericDao<T>, Serializable {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> persistentClass = getDomainClass();

    protected abstract Class<T> getDomainClass();

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public T getById(Serializable id) {
        return entityManager.find(this.getDomainClass(), id);
    }

    public void deleteById(Serializable id) {
        T entity = getById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    public List<T> getAll(){
        return entityManager.createQuery("from " + this.persistentClass.getName() + " o").getResultList();
    }
}
