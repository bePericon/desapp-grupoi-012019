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

    void delete(T entity);

    T getById(Serializable id);

    List<T> getAll();

    void deleteById(Serializable id);

    int count();

    List<T> executeQueryList(String s, Hashtable<String, String> hashtable);
}