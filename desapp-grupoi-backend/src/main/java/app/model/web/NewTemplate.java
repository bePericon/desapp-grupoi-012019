package app.model.web;

import app.model.event.*;
import app.model.event.EnumTipos.TipoModalidad;

import java.util.Date;
import java.util.List;

public class NewTemplate {

    private Template template;
    private Date fechaLimite;
    private TipoModalidad tipoModalidad;
    private List<TemplateItem> templateItems;

    public NewTemplate(Template template, Date fechaLimite, TipoModalidad tipoModalidad, List<TemplateItem> items) {
        this.template = template;
        this.fechaLimite = fechaLimite;
        this.tipoModalidad = tipoModalidad;
        this.templateItems = items;
    }

    //Getters y setters
    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public TipoModalidad getTipoModalidad() {
        return tipoModalidad;
    }

    public void setTipoModalidad(TipoModalidad tipoModalidad) {
        this.tipoModalidad = tipoModalidad;
    }

    public List<TemplateItem> getTemplateItems() {
        return templateItems;
    }

    public void setTemplateItems(List<TemplateItem> templateItems) {
        this.templateItems = templateItems;
    }
}
