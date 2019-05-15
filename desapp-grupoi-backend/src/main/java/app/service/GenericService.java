package app.service;

import app.persistence.IGenericDao;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class GenericService<T extends Serializable> {

    protected abstract IGenericDao<T> getDao();

    @Transactional(readOnly = true)
    public List<T> getAll() {
        return this.getDao().getAll();
    }

    @Transactional(readOnly = true)
    public T getById(final Serializable id) {
        return getDao().getById(id);
    }

    public void save(final T entity) {
        this.getDao().save(entity);
    }

    public void deleteById(final Serializable id) {
        this.getDao().deleteById(id);
    }
}
