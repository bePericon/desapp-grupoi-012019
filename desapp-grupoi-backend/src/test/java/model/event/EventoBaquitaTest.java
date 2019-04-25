package model.event;

import model.account.Dinero;
import model.account.Usuario;
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
    public void testSetTemplate() {
        // FIXTURE
        this.setTemplateBaquitaCompraPreviaAsadoCuatroItems();
        this.evento = this.getEvento();

        // STIMULUS
        this.evento.setTemplate(this.template);

        // ASSERT
        assertNotNull(this.evento.getTemplate());
    }

//    @Test
//    public void testElegirCompradores_CantidadCantidadCompradoresDos() {
//        this.setEventoBaquitaParaCuatro();
//        this.setCuatroAsistentesAlEvento();
//
//        this.evento.elegirCompradorItem(0, 0);
//        this.evento.elegirCompradorItem(0, 1);
//        this.evento.elegirCompradorItem(1, 2);
//        this.evento.elegirCompradorItem(1, 3);
//
//        assertEquals(2, this.evento.getTotalCompradores());
//    }


    // Methods aux
    private void setTemplateBaquitaCompraPreviaAsadoCuatroItems() {
        Modalidad modBaquita = new BaquitaCompraPrevia();
        this.template = new Template("Asado para cuatro", "Descripcion", this.organizador);
        this.template.agregarItem(new Item(this.getCosto(200), "Carne", 2));
        this.template.agregarItem(new Item(this.getCosto(200), "Carne", 2));
        this.template.agregarItem(new Item(this.getCosto(180), "Coca", 2));
        this.template.agregarItem(new Item(this.getCosto(180), "Coca", 2));
        this.template.setModalidad(modBaquita);
    }

    private void setEventoBaquitaParaCuatro(){
        this.setTemplateBaquitaCompraPreviaAsadoCuatroItems();
        this.evento = this.getEvento();
        this.evento.setTemplate(this.template);
    }

    private Evento getEvento() {
        Usuario mockUsuario = Mockito.mock(Usuario.class);
        return new Evento(mockUsuario, "Asado");
    }

    private void setUsuarios() {
        this.organizador = new Usuario("Orga", "Nizador", "organizador@email.com");
        this.usuarioUno = new Usuario("Usuario", "Uno", "invitado-uno@email.com");
        this.usuarioDos = new Usuario("Usuario", "Dos", "invitado-dos@email.com");
    }

    private void setCuatroAsistentesAlEvento() {
        Usuario usuarioUno = new Usuario("UsuarioUno", "ApellidoUno" );
        Usuario UsuarioDos = new Usuario("UsuarioDos", "ApellidoDos" );
        Usuario mockUsuarioTres = Mockito.mock(Usuario.class);
        Usuario mockUsuarioCuatro = Mockito.mock(Usuario.class);

        this.evento.agregarAsistente(usuarioUno);
        this.evento.agregarAsistente(UsuarioDos);
        this.evento.agregarAsistente(mockUsuarioTres);
        this.evento.agregarAsistente(mockUsuarioCuatro);
    }

    private Dinero getCosto(int costo){
        return new Dinero(costo);
    }
}
