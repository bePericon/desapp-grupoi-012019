package app.model.web;

import java.util.List;

public class Invitaciones {

    List<String> emails;
    long idEvento;

    public Invitaciones(List<String> emails, long idEvento) {
        this.emails = emails;
        this.idEvento = idEvento;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(long idEvento) {
        this.idEvento = idEvento;
    }
}
