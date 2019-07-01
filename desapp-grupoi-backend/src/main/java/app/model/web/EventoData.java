package app.model.web;

import app.model.event.TemplateItem;

import java.util.List;

public class EventoData {

    private List<TemplateItem> templateItems;
    private long idEvento;

    public EventoData(List<TemplateItem> templateItems, long idEvento) {
        this.templateItems = templateItems;
        this.idEvento = idEvento;
    }

    public List<TemplateItem> getTemplateItems() {
        return templateItems;
    }

    public void setTemplateItems(List<TemplateItem> templateItems) {
        this.templateItems = templateItems;
    }

    public long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(long idEvento) {
        this.idEvento = idEvento;
    }
}
