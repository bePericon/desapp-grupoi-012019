package model.event;

import app.model.account.Dinero;
import app.model.account.Usuario;
import app.model.event.Evento;
import app.model.event.Item;
import app.model.event.Template;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class EventoTest {

    private Evento evento;
    private Template template;
    private Usuario organizador;
    private Usuario usuarioUno;
    private Usuario usuarioDos;

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
    }

    @Test
    public void testEventoCrearUnEventoSinInvitados() {
        // FIXTURE
        Usuario mockUsuario = Mockito.mock(Usuario.class);

        // STIMULUS
        this.evento = new Evento(mockUsuario, "Asado");

        // ASSERT
        assertEquals(0, this.evento.getCantidadInvitados());
    }

    @Test
    public void testEventoCrearUnEventoConDosInvitados() {
        // FIXTURE
        this.setNuevoEvento("Asado");

        // STIMULUS
        this.evento.agregarInvitado("invitado-uno@email.com");
        this.evento.agregarInvitado("invitado-dos@email.com");

        // ASSERT
        assertEquals(2, this.evento.getCantidadInvitados());
    }

    @Test
    public void testEventoLosInvitadosConfirmarAsistenciaEventoConDosAsistentes() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.evento.agregarInvitado("invitado-uno@email.com");
        this.evento.agregarInvitado("invitado-dos@email.com");
        this.seEnvianInvitaciones();

        // STIMULUS
        this.usuarioUno.getInvitaciones().get(0).confirmar(this.usuarioUno);
        this.usuarioDos.getInvitaciones().get(0).confirmar(this.usuarioDos);

        // ASSERT
        assertEquals(2, this.evento.getCantidadAsistentes());
    }

    @Test
    public void testEventoCargarUnTemplateConItems() {
        // FIXTURE
        this.setNuevoEvento("Asado");
        this.setTemplateSinModalidadConDosItems();

        // STIMULUS
        this.evento.setTemplate(this.template);

        // ASSERT
        assertNotNull(this.evento.getTemplate());
    }

    @Test
    public void testEventoCargarItemsSinTemplates() {
        // FIXTURE
        this.setNuevoEvento("Asado");
        Item iuno = Mockito.mock(Item.class);
        Item idos = Mockito.mock(Item.class);

        // STIMULUS
        this.evento.agregarItem(iuno);
        this.evento.agregarItem(idos);

        // ASSERT
        assertEquals(2, this.evento.getCantidadItems());
    }


    // Methods aux
    private void setNuevoEvento(String nombre) {
        Usuario mockUsuario = Mockito.mock(Usuario.class);
        this.evento = new Evento(mockUsuario, nombre);
    }

    private void setTemplateSinModalidadConDosItems() {
        this.template = new Template("Nuevo template", "Descripcion", this.organizador);
        this.template.agregarItem(new Item(this.getCosto(200), "Carne", 2));
        this.template.agregarItem(new Item(this.getCosto(180), "Coca", 2));
    }

    private Dinero getCosto(int costo){
        return new Dinero(costo);
    }

    private void setUsuarios() {
        this.organizador = new Usuario("Orga", "Nizador", "organizador@email.com");
        this.usuarioUno = new Usuario("Usuario", "Uno", "invitado-uno@email.com");
        this.usuarioDos = new Usuario("Usuario", "Dos", "invitado-dos@email.com");
    }

    private void seEnvianInvitaciones() {
        this.usuarioUno.agregarInvitacion(this.evento.getInvitados().get(0));
        this.usuarioDos.agregarInvitacion(this.evento.getInvitados().get(1));
    }

}
