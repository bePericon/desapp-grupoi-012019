package app.service.event;

import app.error.exception.ExceptionBadRequest;
import app.error.exception.ExceptionNotFound;
import app.model.web.NewTemplate;
import app.model.account.Cuenta;
import app.model.account.Usuario;
import app.model.event.*;
import app.model.event.EnumTipos.TipoVisibilidad;
import app.persistence.event.TemplateDao;
import app.service.GenericService;
import app.service.account.CuentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
public class TemplateService extends GenericService<Template> {

    @Autowired
    private TemplateDao dao;

    // Services

    @Autowired
    private CuentaService cuentaService;

    @Override
    protected TemplateDao getDao() {
        return this.dao;
    }

    public Template createNuevoTemplate(long idUsuario, NewTemplate nuevoTemplate) {

        if(!this.esValido(nuevoTemplate.getTemplate()))
            throw new ExceptionBadRequest("Los datos del template no son validos.");

        Cuenta cuenta = this.cuentaService.getCuentaByIdUsuario(idUsuario);
        Template template = this.crearTemplate(nuevoTemplate, cuenta.getUsuario());
        this.getDao().save(template);

        cuenta.agregarTemplate(template);
        this.cuentaService.update(cuenta);
        return template;
    }

    public Template getTemplateById(long idTemplate) {
        Template template = this.getDao().getById(idTemplate);

        if(template == null)
            throw new ExceptionNotFound("El template no fue encontrado.");

        return template;
    }

    public List<Template> getTemplatesByIdUsuario(long idUsuario) {
        Cuenta cuenta = this.cuentaService.getCuentaByIdUsuario(idUsuario);
        return cuenta.getTemplates();
    }

    private boolean esValido(Template nuevoTemplate) {
        boolean tieneNombre = !nuevoTemplate.getNombre().isEmpty();
        boolean tieneDescripcion = !nuevoTemplate.getDescripcion().isEmpty();

        return tieneNombre && tieneDescripcion;
    }

    private Template crearTemplate(NewTemplate newT, Usuario organizador) {

        Template template = new Template(newT.getTemplate().getNombre(),
                newT.getTemplate().getDescripcion(),
                organizador);

        if(newT.getTemplateItems().size() > 0)
            template.setTemplateItems(newT.getTemplateItems());

        switch (newT.getTipoModalidad()){
            case FIESTA:
                template.setModalidad(new Fiesta(newT.getFechaLimite()));
                break;
            case CANASTA:
                template.setModalidad(new Canasta(newT.getFechaLimite()));
                break;
            case BAQUITA_COMPRA:
                template.setModalidad(new BaquitaCompraPrevia(newT.getFechaLimite()));
                break;
            case BAQUITA_RECOLECCION:
                template.setModalidad(new BaquitaRecoleccionPrevia(newT.getFechaLimite()));
                break;
        }
        return template;
    }

	public  List<Template>  getTemplatesPublicos() {
		List<Template> templates = this.getDao().getTemplatesPublicos();
		return templates;
	}

	public Template actualizarTemplateAPublico(long idTemplate) {
		Template tActual = this.getTemplateById(idTemplate);
		tActual.setVisibilidad(TipoVisibilidad.PUBLICA);
		this.update(tActual);
		return tActual;
	}
}
