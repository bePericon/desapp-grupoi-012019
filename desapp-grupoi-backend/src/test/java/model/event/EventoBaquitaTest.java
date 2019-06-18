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

import java.util.Date;

import static org.junit.Assert.*;

public class EventoBaquitaTest {

    private Evento evento;
    private Template template;
    private Usuario organizador;
    private Usuario usuarioUno;
    private Usuario usuarioDos;
    private Usuario usuarioTres;
    private Modalidad modalidad;
    private Cuenta organizadorCuenta;
    private Cuenta usuarioUnoCuenta;
    private Cuenta usuarioDosCuenta;
    private Cuenta usuarioTresCuenta;

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
        this.organizadorCuenta= null;
        this.usuarioUnoCuenta= null;
        this.usuarioDosCuenta= null;
        this.usuarioTresCuenta= null;
    }

    @Test
    public void testBaquitaCostoPorAsistente() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadBaquitaCompraPreviaConTresItems();
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);
        this.todosAceptanLasInvitaciones();

        // STIMULUS
        Dinero dinero = this.evento.getCostoUsuario();

        // ASSERT
        assertEquals(160, dinero.getMonto(), 0.0);
    }

    @Test
    public void testBaquitaCompraPreviaUnCompradorDosDeudas() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadBaquitaCompraPreviaConTresItems();
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);
        this.todosAceptanLasInvitaciones();
        this.evento.elegirItemPorIndice(this.usuarioDos, 0); // Carne
        this.evento.elegirItemPorIndice(this.usuarioDos, 1); // Coca
        this.evento.elegirItemPorIndice(this.usuarioDos, 2); // Papas

        // STIMULUS
        Dinero costoTotal = this.evento.getCostoTotal();
        Baquita mod = (BaquitaCompraPrevia)this.evento.getModalidad();


        // ASSERT
        assertEquals(2, mod.getCantidadDeudas());
    }

    @Test
    public void testBaquitaCompraPreviaDosCompradores() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadBaquitaCompraPreviaConTresItems();
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);
        this.todosAceptanLasInvitaciones();
        this.evento.elegirItemPorIndice(this.usuarioDos, 0); // Carne
        this.evento.elegirItemPorIndice(this.usuarioDos, 1); // Coca
        this.evento.elegirItemPorIndice(this.usuarioUno, 2); // Papas

        // STIMULUS
        Dinero costoTotal = this.evento.getCostoTotal();
        Baquita mod = (BaquitaCompraPrevia)this.evento.getModalidad();


        // ASSERT
        assertEquals(2, mod.getCantidadDeudas());
        assertEquals(60, mod.getDeudas().get(0).getMonto().getMonto(), 0.0);
        assertEquals(160, mod.getDeudas().get(1).getMonto().getMonto(), 0.0);
    }

    @Test
    public void testBaquitaRecoleccionPreviaCostoTotal() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadBaquitaRecoleccionPreviaConTresItems();
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);
        this.todosAceptanLasInvitaciones();

        // STIMULUS
        Dinero costoTotal = this.evento.getCostoTotal();

        // ASSERT
        assertEquals(480, costoTotal.getMonto(), 0.0);
    }

    @Test
    public void testBaquitaRecoleccionPreviaPagarDeuda() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadBaquitaRecoleccionPreviaConTresItems();
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);
        this.todosAceptanLasInvitaciones();

        // STIMULUS
        Dinero costoTotal = this.evento.getCostoTotal();
        BaquitaRecoleccionPrevia mod = (BaquitaRecoleccionPrevia)this.evento.getModalidad();
        mod.pagarDeuda(this.usuarioUno, this.evento.getCostoUsuario());

        // ASSERT
        assertEquals(1, mod.getDeudas().size(), 0.0);
        assertEquals(160, mod.getDeudas().get(0).getMonto().getMonto(), 0.0);
    }

    @Test
    public void testBaquitaRecoleccionPreviaDeudasPagadas() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadBaquitaRecoleccionPreviaConTresItems();
        this.seEnvianInvitaciones();
        this.evento.setTemplate(this.template);
        this.todosAceptanLasInvitaciones();

        // STIMULUS
        Dinero costoTotal = this.evento.getCostoTotal();
        BaquitaRecoleccionPrevia mod = (BaquitaRecoleccionPrevia)this.evento.getModalidad();
        mod.pagarDeuda(this.usuarioUno, this.evento.getCostoUsuario());
        mod.pagarDeuda(this.usuarioDos, this.evento.getCostoUsuario());
        mod.pagarDeuda(this.usuarioTres, this.evento.getCostoUsuario());

        // ASSERT
        assertTrue(mod.estanDeudasPagadas());
    }

    // Methods aux

    private void setNuevoEvento(String nombre) {
        Usuario mockUsuario = Mockito.mock(Usuario.class);
        this.evento = new Evento(mockUsuario, nombre);
    }
    private void setTemplateModalidadBaquitaCompraPreviaConTresItems() {
        this.modalidad = new BaquitaCompraPrevia(new Date(2030,11,27,10,10,00));
        this.agregarTresItemsEnTemplate();
        this.template.setModalidad(this.modalidad);
    }

    private void agregarTresItemsEnTemplate() {
        this.template = new Template("Nuevo template", "Descripcion", this.organizador);
        this.template.agregarItem(new Item(this.getCosto(200), "Carne", 2));
        this.template.agregarItem(new Item(this.getCosto(180), "Coca", 2));
        this.template.agregarItem(new Item(this.getCosto(100), "Papas", 2));
    }

    private void setTemplateModalidadBaquitaRecoleccionPreviaConTresItems() {
        this.modalidad = new BaquitaRecoleccionPrevia(new Date(2030,11,27,10,10,00));
        this.agregarTresItemsEnTemplate();
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

        this.organizadorCuenta = new Cuenta(this.organizador);
        this.usuarioUnoCuenta = new Cuenta(this.usuarioUno);
        this.usuarioDosCuenta = new Cuenta(this.usuarioDos);
        this.usuarioTresCuenta = new Cuenta(this.usuarioTres);
    }

    private void seEnvianInvitaciones() {
        this.usuarioUnoCuenta.agregarInvitacion(new Invitacion(this.usuarioUno.getEmail(), this.evento));
        this.usuarioDosCuenta.agregarInvitacion(new Invitacion(this.usuarioDos.getEmail(), this.evento));
        this.usuarioTresCuenta.agregarInvitacion(new Invitacion(this.usuarioTres.getEmail(), this.evento));
    }

    private void todosAceptanLasInvitaciones() {
        this.usuarioUnoCuenta.getInvitaciones().get(0).confirmar(this.usuarioUno);
        this.usuarioDosCuenta.getInvitaciones().get(0).confirmar(this.usuarioDos);
        this.usuarioTresCuenta.getInvitaciones().get(0).confirmar(this.usuarioTres);
    }
}
