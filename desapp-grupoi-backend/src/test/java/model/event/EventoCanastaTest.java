package model.event;


import app.model.account.Cuenta;
import app.model.account.Dinero;
import app.model.account.Usuario;
import app.model.event.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

import static org.junit.Assert.*;

public class EventoCanastaTest {

    private Evento evento;
    private Template template;
    private Usuario organizador;
    private Usuario usuarioUno;
    private Usuario usuarioDos;
    private Modalidad modalidad;
    private Cuenta organizadorCuenta;
    private Cuenta usuarioUnoCuenta;
    private Cuenta usuarioDosCuenta;

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
        this.organizadorCuenta= null;
        this.usuarioUnoCuenta= null;
        this.usuarioDosCuenta= null;
    }

	@Test
	public void testUsuarioDosPuedeElegirUnaGaseosaParaComprar() {
		//FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.modalidad = new Canasta(new Date(2030,11,27,10,10,00));
        this.evento.setModalidad(this.modalidad);
        this.evento.agregarItem(new Item(this.getCosto(200), "Carne", 2),1);
        this.evento.agregarItem(new Item(this.getCosto(180), "Coca", 2),1);
        this.seEnvianInvitaciones();
        this.usuarioDosCuenta.getInvitaciones().get(0).confirmar(this.usuarioDos);

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
        this.modalidad = new Canasta(new Date(2030,11,27,10,10,00));
        this.evento.setModalidad(this.modalidad);
        this.evento.agregarItem(new Item(this.getCosto(200), "Carne", 2),1);
        this.evento.agregarItem(new Item(this.getCosto(180), "Coca", 2),1);
        this.seEnvianInvitaciones();
        this.usuarioDosCuenta.getInvitaciones().get(0).confirmar(this.usuarioDos);

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

        this.organizadorCuenta = new Cuenta(this.organizador);
        this.usuarioUnoCuenta = new Cuenta(this.usuarioUno);
        this.usuarioDosCuenta = new Cuenta(this.usuarioDos);
    }

    private void seEnvianInvitaciones() {
        this.usuarioUnoCuenta.agregarInvitacion(new Invitacion(this.usuarioUno.getEmail(), this.evento));
        this.usuarioDosCuenta.agregarInvitacion(new Invitacion(this.usuarioDos.getEmail(), this.evento));
    }
}
