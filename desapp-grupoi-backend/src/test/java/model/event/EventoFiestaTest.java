package model.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.account.Dinero;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.account.Usuario;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class EventoFiestaTest {

    private Evento evento;
    private Template template;
    private Usuario organizador;
    private Usuario usuarioUno;
    private Usuario usuarioDos;
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
        this.modalidad = null;
    }

    @Test
    public void testFiestaDosInvitadosUnoConfirmadoCalcularCostos() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadFiestaConDosItems();
        this.evento.agregarInvitado("invitado-uno@email.com");
        this.evento.agregarInvitado("invitado-dos@email.com");
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);

        // STIMULUS
        this.usuarioUno.getInvitaciones().get(0).confirmar(this.usuarioUno);

        // ASSERT
        assertEquals(380, this.evento.getCostoTotal().getMonto(), 0.0);
    }

    @Test
    public void testFiestaDosInvitadosDosConfirmadosCalcularCostos() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadFiestaConDosItems();
        this.evento.agregarInvitado("invitado-uno@email.com");
        this.evento.agregarInvitado("invitado-dos@email.com");
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);

        // STIMULUS
        this.usuarioUno.getInvitaciones().get(0).confirmar(this.usuarioUno);
        this.usuarioDos.getInvitaciones().get(0).confirmar(this.usuarioDos);

        // ASSERT
        assertEquals(380, this.evento.getCostoTotal().getMonto(), 0.0);
    }

	@Test
	public void testFiestaAgregoMasVariedadDeItemsYCalcularCostosParaDosPersonas() {
		// FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadFiestaConDosItems();
        this.evento.agregarInvitado("invitado-uno@email.com");
        this.evento.agregarInvitado("invitado-dos@email.com");
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);
        this.setMasItemsEnEvento();

        // STIMULUS
        this.usuarioUno.getInvitaciones().get(0).confirmar(this.usuarioUno);
        this.usuarioDos.getInvitaciones().get(0).confirmar(this.usuarioDos);

    	//ASSERT
    	 assertEquals(505, this.evento.getCostoTotal().getMonto(), 0.0);
	}

    @Test
    public void testFiestaNoSeSumaNadieLuegoDeLaFechaLimite() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadFiestaConDosItemsFechaNoVigente();
        this.evento.agregarInvitado("invitado-uno@email.com");
        this.evento.agregarInvitado("invitado-dos@email.com");
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);

        // STIMULUS
        this.usuarioUno.getInvitaciones().get(0).confirmar(this.usuarioUno);
        this.usuarioDos.getInvitaciones().get(0).confirmar(this.usuarioDos);

        // ASSERT
    	assertTrue(evento.getAsistentes().isEmpty());
    }

    // Methods aux
    private void setNuevoEvento(String nombre) {
        Usuario mockUsuario = Mockito.mock(Usuario.class);
        this.evento = new Evento(mockUsuario, nombre);
    }

    private void setTemplateModalidadFiestaConDosItems() {
        this.modalidad = new Fiesta(new DateTime(2019,11,27,10,10,00));
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
    }

    private void seEnvianInvitaciones() {
        this.usuarioUno.agregarInvitacion(this.evento.getInvitados().get(0));
        this.usuarioDos.agregarInvitacion(this.evento.getInvitados().get(1));
    }

    private void setMasItemsEnEvento() {
        Item iuno = new Item(this.getCosto(50), "Cerveza", 1);
        Item idos = new Item(this.getCosto(25), "Biscochos", 4);

        this.evento.agregarItem(iuno);
        this.evento.agregarItem(idos);
    }

    private void setTemplateModalidadFiestaConDosItemsFechaNoVigente() {
        this.modalidad = new Fiesta(DateTime.now().minus(2));
        this.template = new Template("Nuevo template", "Descripcion", this.organizador);
        this.template.agregarItem(new Item(this.getCosto(200), "Carne", 2));
        this.template.agregarItem(new Item(this.getCosto(180), "Coca", 2));
        this.template.setModalidad(this.modalidad);
    }

}
