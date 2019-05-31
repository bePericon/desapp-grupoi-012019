package app.persistence.event;

import app.model.event.Template;
import app.persistence.GenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class TemplateDao extends GenericDao<Template> {

    @Override
    protected Class<Template> getDomainClass() {
        return Template.class;
    }
}
