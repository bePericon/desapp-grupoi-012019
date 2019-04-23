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

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void end(){
        this.evento = null;
        this.template = null;
    }

    @Test
    public void testSetTemplate() {
        this.setEventoBaquitaParaCuatro();

        assertNotNull(this.evento.getTemplate());
    }

    @Test
    public void testSetAsistentes_CantidadAsistentesCuatro() {
        this.setEventoBaquitaParaCuatro();
        this.setCuatroAsistentesAlEvento();

        assertEquals(4, this.evento.getTotalAsistentes());
    }

    @Test
    public void testElegirCompradores_CantidadCantidadCompradoresDos() {
        this.setEventoBaquitaParaCuatro();
        this.setCuatroAsistentesAlEvento();

        this.evento.elegirCompradorItem(0, 0);
        this.evento.elegirCompradorItem(0, 1);
        this.evento.elegirCompradorItem(1, 2);
        this.evento.elegirCompradorItem(1, 3);

        assertEquals(2, this.evento.getTotalCompradores());
    }


    // Methods aux
    private void setEventoBaquitaParaCuatro(){
        this.setTemplateBaquitaCompraPreviaAsadoCuatroItems();
        this.evento = getEvento();
        this.evento.setTemplate(this.template);
    }

    private Evento getEvento() {
        Usuario mockUsuario = Mockito.mock(Usuario.class);
        return new Evento(mockUsuario, "Asado");
    }

    private void setTemplateBaquitaCompraPreviaAsadoCuatroItems() {
        Modalidad modBaquita = new BaquitaCompraPrevia();
        this.template = new Template("Asado para cuatro", "Descripcion");
        this.template.agregarItem(new Item(this.getCosto(200), "Carne", 2));
        this.template.agregarItem(new Item(this.getCosto(200), "Carne", 2));
        this.template.agregarItem(new Item(this.getCosto(180), "Coca", 2));
        this.template.agregarItem(new Item(this.getCosto(180), "Coca", 2));
        this.template.setModalidad(modBaquita);
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
