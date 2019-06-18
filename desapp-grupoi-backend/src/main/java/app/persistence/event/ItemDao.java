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

}
