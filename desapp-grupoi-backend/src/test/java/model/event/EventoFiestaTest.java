package model.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import app.model.account.Cuenta;
import app.model.account.Dinero;
import app.model.account.Usuario;
import app.model.event.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

public class EventoFiestaTest {

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
    public void testFiestaDosInvitadosUnoConfirmadoCalcularCostos() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadFiestaConDosItems();
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);

        // STIMULUS
        this.usuarioUnoCuenta.getInvitaciones().get(0).confirmar(this.usuarioUno);

        // ASSERT
        assertEquals(380, this.evento.getCostoTotal().getMonto(), 0.0);
    }

    @Test
    public void testFiestaDosInvitadosDosConfirmadosCalcularCostos() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadFiestaConDosItems();
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);

        // STIMULUS
        this.usuarioUnoCuenta.getInvitaciones().get(0).confirmar(this.usuarioUno);
        this.usuarioDosCuenta.getInvitaciones().get(0).confirmar(this.usuarioDos);

        // ASSERT
        assertEquals(380, this.evento.getCostoTotal().getMonto(), 0.0);
    }

	@Test
	public void testFiestaAgregoMasVariedadDeItemsYCalcularCostosParaDosPersonas() {
		// FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadFiestaConDosItems();
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);
        this.setMasItemsEnEvento();

        // STIMULUS
        this.usuarioUnoCuenta.getInvitaciones().get(0).confirmar(this.usuarioUno);
        this.usuarioDosCuenta.getInvitaciones().get(0).confirmar(this.usuarioDos);

    	//ASSERT
    	 assertEquals(455, this.evento.getCostoTotal().getMonto(), 0.0);
	}

    @Test
    public void testFiestaNoSeSumaNadieLuegoDeLaFechaLimite() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadFiestaConDosItemsFechaNoVigente();
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);

        // STIMULUS
        this.usuarioUnoCuenta.getInvitaciones().get(0).confirmar(this.usuarioUno);
        this.usuarioDosCuenta.getInvitaciones().get(0).confirmar(this.usuarioDos);

        // ASSERT
    	assertTrue(evento.getAsistentes().isEmpty());
    }

    // Methods aux
    private void setNuevoEvento(String nombre) {
        Usuario mockUsuario = Mockito.mock(Usuario.class);
        this.evento = new Evento(mockUsuario, nombre);
    }

    private void setTemplateModalidadFiestaConDosItems() {
        this.modalidad = new Fiesta(new Date(2019,11,27,10,10,00));
        this.template = new Template("Nuevo template", "Descripcion", this.organizador);
        this.template.agregarItem(new Item(this.getCosto(200), "Carne", 2), 1);
        this.template.agregarItem(new Item(this.getCosto(180), "Coca", 2), 1);
        this.template.setModalidad(this.modalidad);
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

    private void setMasItemsEnEvento() {
        Item iuno = new Item(this.getCosto(50), "Cerveza", 1);
        Item idos = new Item(this.getCosto(25), "Biscochos", 4);

        this.evento.agregarItem(iuno,1 );
        this.evento.agregarItem(idos, 1);
    }

    private void setTemplateModalidadFiestaConDosItemsFechaNoVigente() {
        this.modalidad = new Fiesta(new Date(System.currentTimeMillis()-1000));
        this.template = new Template("Nuevo template", "Descripcion", this.organizador);
        this.template.agregarItem(new Item(this.getCosto(200), "Carne", 2),1);
        this.template.agregarItem(new Item(this.getCosto(180), "Coca", 2),1);
        this.template.setModalidad(this.modalidad);
    }

}
