package app.model.web;

import app.model.event.Evento;

import java.util.List;

public class Invitaciones {

    List<String> emails;
//    long idEvento;
    Evento evento;

    public Invitaciones(List<String> emails, Evento evento){ //, long idEvento) {
        this.emails = emails;
//        this.idEvento = idEvento;
        this.evento = evento;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

//    public long getIdEvento() {
//        return idEvento;
//    }
//
//    public void setIdEvento(long idEvento) {
//        this.idEvento = idEvento;
//    }
}
