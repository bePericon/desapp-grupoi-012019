package app.persistence.event;

import app.model.event.EnumTipos;
import app.model.event.Evento;
import app.model.event.Template;
import app.persistence.GenericDao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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
        Join<Evento, Template> eventoTemplateJoin = template.join("template", JoinType.LEFT);
        cq.where(cb.equal(eventoTemplateJoin.get("visibilidad"), EnumTipos.TipoVisibilidad.PUBLICA));
        cq.select(template);
        List<Template> templatesPublicos = this.entityManager.createQuery(cq).getResultList();

//      List<Template> templatesPublicos = new ArrayList<Template>();
        return templatesPublicos;
	}
}
