package model.event;


import java.util.ArrayList;
import java.util.List;

import model.account.Dinero;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.account.Usuario;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class EventoCanastaTest {

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
	public void testUsuarioDosPuedeElegirUnaGaseosaParaComprar() {
		//FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.modalidad = new Canasta();
        this.evento.setModalidad(this.modalidad);
        this.evento.agregarItem(new Item(this.getCosto(200), "Carne", 2));
        this.evento.agregarItem(new Item(this.getCosto(180), "Coca", 2));
        this.evento.agregarInvitado("invitado-uno@email.com");
        this.evento.agregarInvitado("invitado-dos@email.com");
        this.seEnvianInvitaciones();
        this.usuarioDos.getInvitaciones().get(0).confirmar(this.usuarioDos);

        // STIMULUS
		this.evento.elegirItemPorIndice(this.usuarioDos, 1); // Coca

    	// ASSERT
    	assertEquals(1, this.evento.getCantidadItemsComprados());
	}

    @Test
    public void testSaberCuantoVaAGastarElUsuarioDos() {
        //FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.modalidad = new Canasta();
        this.evento.setModalidad(this.modalidad);
        this.evento.agregarItem(new Item(this.getCosto(200), "Carne", 2));
        this.evento.agregarItem(new Item(this.getCosto(180), "Coca", 2));
        this.evento.agregarInvitado("invitado-uno@email.com");
        this.evento.agregarInvitado("invitado-dos@email.com");
        this.seEnvianInvitaciones();
        this.usuarioDos.getInvitaciones().get(0).confirmar(this.usuarioDos);

        // STIMULUS
        this.evento.elegirItemPorIndice(this.usuarioDos, 1); // Coca

        // ASSERT
        assertEquals(180, this.evento.getCostoUsuario(this.usuarioDos).getMonto(), 0.0);
    }

    // Methods aux
    private void setNuevoEvento(String nombre) {
        Usuario mockUsuario = Mockito.mock(Usuario.class);
        this.evento = new Evento(mockUsuario, nombre);
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
