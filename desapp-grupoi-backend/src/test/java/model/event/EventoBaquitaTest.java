package model.event;

import model.account.Dinero;
import model.account.Usuario;
import org.joda.time.DateTime;
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
    public void testBaquitaCostoPorAsistente() {
        // FIXTURE
        this.setUsuarios();
        this.setNuevoEvento("Asado");
        this.setTemplateModalidadBaquitaCompraPreviaConTresItems();
        this.agregarInvitados();
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
        this.agregarInvitados();
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
        this.agregarInvitados();
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
        this.agregarInvitados();
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
        this.agregarInvitados();
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
        this.agregarInvitados();
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
        this.modalidad = new BaquitaCompraPrevia();
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
        this.modalidad = new BaquitaRecoleccionPrevia();
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
    }

    private void agregarInvitados() {
        this.evento.agregarInvitado("invitado-uno@email.com");
        this.evento.agregarInvitado("invitado-dos@email.com");
        this.evento.agregarInvitado("invitado-tres@email.com");
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
}
