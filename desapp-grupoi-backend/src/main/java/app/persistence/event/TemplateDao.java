package app.persistence.event;

import app.model.event.EnumTipos;

import app.model.event.Template;
import app.persistence.GenericDao;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class TemplateDao extends GenericDao<Template> {

    @Override
    protected Class<Template> getDomainClass() {
        return Template.class;
    }

	public  List<Template> getTemplatesPublicos() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Template> cq = cb.createQuery(Template.class);
        Root<Template> template= cq.from(Template.class);
        cq.where(cb.equal(template.get("visibilidad"), EnumTipos.TipoVisibilidad.PUBLICA));
        cq.select(template);
        List<Template> templatesPublicos = this.entityManager.createQuery(cq).getResultList();

        return templatesPublicos;
	}
}
