package app.service.event;


import app.model.event.Item;
import app.persistence.event.ItemDao;
import app.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemService extends GenericService<Item> {

    @Autowired
    private ItemDao dao;

    @Override
    protected ItemDao getDao() {
        return this.dao;
    }

    /*
     * Retorna todos los items disponibles.
     */
    public List<Item> getAllItems() {
        return this.getDao().getAll();
    }


    public Item createNuevoItem(Item nuevoItem) {
        this.getDao().save(nuevoItem);
        return nuevoItem;
    }
}
