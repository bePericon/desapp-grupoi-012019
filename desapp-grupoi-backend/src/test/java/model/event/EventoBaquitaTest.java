package model.event;

import app.model.account.Dinero;
import app.model.account.Usuario;
import app.model.event.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;

public class EventoBaquitaTest {

    private Evento evento;
    private Template template;
    private Usuario organizador;
    private Usuario usuarioUno;
    private Usuario usuarioDos;
    private Usuario usuarioTres;
    private Modalidad modalidad;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void end(){
        this.evento = null;
        this.template = null;
        this.organizador = null;
        this.usuarioUno = null;
        this.usuarioDos = null;
        this.usuarioTres = null;
        this.modalidad = null;
    }

    @Test
    public void testBaquita_CostoPorAsistente() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadBaquitaCompraPreviaConDosItems();
        this.evento.agregarInvitado("invitado-uno@email.com");
        this.evento.agregarInvitado("invitado-dos@email.com");
        this.evento.agregarInvitado("invitado-tres@email.com");
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);
        this.todosAceptanLasInvitaciones();

        // STIMULUS
        Dinero dinero = this.evento.getCostoUsuario();

        // ASSERT
        assertEquals(126.66, dinero.getMonto(), 0.09);
    }


    // Methods aux

    private void setNuevoEvento(String nombre) {
        Usuario mockUsuario = Mockito.mock(Usuario.class);
        this.evento = new Evento(mockUsuario, nombre);
    }
    private void setTemplateModalidadBaquitaCompraPreviaConDosItems() {
        this.modalidad = new BaquitaCompraPrevia();
        this.template = new Template("Nuevo template", "Descripcion", this.organizador);
        this.template.agregarItem(new Item(this.getCosto(200), "Carne", 2));
        this.template.agregarItem(new Item(this.getCosto(180), "Coca", 2));
        this.template.setModalidad(this.modalidad);
    }

    private Dinero getCosto(int costo){
        return new Dinero(costo);
    }

    private void setUsuarios() {
        this.organizador = new Usuario("Orga", "Nizador", "organizador@email.com");
        this.usuarioUno = new Usuario("Usuario", "Uno", "invitado-uno@email.com");
        this.usuarioDos = new Usuario("Usuario", "Dos", "invitado-dos@email.com");
        this.usuarioTres = new Usuario("Usuario", "Tres", "invitado-tres@email.com");
    }

    private void seEnvianInvitaciones() {
        this.usuarioUno.agregarInvitacion(this.evento.getInvitados().get(0));
        this.usuarioDos.agregarInvitacion(this.evento.getInvitados().get(1));
        this.usuarioTres.agregarInvitacion(this.evento.getInvitados().get(2));
    }

    private void todosAceptanLasInvitaciones() {
        this.usuarioUno.getInvitaciones().get(0).confirmar(this.usuarioUno);
        this.usuarioDos.getInvitaciones().get(0).confirmar(this.usuarioDos);
        this.usuarioTres.getInvitaciones().get(0).confirmar(this.usuarioTres);
    }

    private void setMasItemsEnEvento() {
        Item iuno = new Item(this.getCosto(50), "Cerveza", 1);
        Item idos = new Item(this.getCosto(25), "Biscochos", 4);

        this.evento.agregarItem(iuno);
        this.evento.agregarItem(idos);
    }
}
