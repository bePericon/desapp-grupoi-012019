package app.persistence;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

/**
 * Interface for generic DAO
 *
 * @param <T>
 */
public interface IGenericDao<T> {

    void save(T entity);

    void update(T entity);

    T getById(Serializable id);

    void deleteById(Serializable id);

    List<T> getAll();
}