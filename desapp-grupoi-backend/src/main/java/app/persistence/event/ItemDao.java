package app.persistence.event;

import app.model.event.*;
import app.persistence.GenericDao;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ItemDao extends GenericDao<Item> {

    @Override
    protected Class getDomainClass() {
        return Item.class;
    }
    
    public List<Item> getAllItemsDisponibles() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> cq = cb.createQuery(Item.class);
        Root<Item> item = cq.from(Item.class);
 
        cq.select(item);
        List<Item> itemsDisponibles = this.entityManager.createQuery(cq).getResultList();

        return itemsDisponibles;
    }
    
    
    public void guardarItem(Item item) {
//        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
//        CriteriaQuery<Item> cq = cb.createQuery(Item.class);
//        Root<Item> item = cq.from(Item.class);
// 
//        cq.select(item);
//        List<Item> itemsDisponibles = this.entityManager.createQuery(cq).getResultList();
//
//        return itemsDisponibles;
    }

}
