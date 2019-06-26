package app.model.web;

import java.util.List;

public class NewEvento {

    private NewTemplate nuevoTemplate;
    private List<String> invitados;

    public NewEvento(NewTemplate nuevoTemplate, List<String> invitados) {
        this.nuevoTemplate = nuevoTemplate;
        this.invitados = invitados;
    }

    public NewTemplate getNuevoTemplate() {
        return nuevoTemplate;
    }

    public void setNuevoTemplate(NewTemplate nuevoTemplate) {
        this.nuevoTemplate = nuevoTemplate;
    }

    public List<String> getInvitados() {
        return invitados;
    }

    public void setInvitados(List<String> invitados) {
        this.invitados = invitados;
    }

    public String getNombre() {
        return this.nuevoTemplate.getTemplate().getNombre();
    }
}
