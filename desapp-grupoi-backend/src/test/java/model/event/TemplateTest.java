package model.event;

import app.model.account.Cuenta;
import app.model.account.Dinero;
import app.model.account.Usuario;
import app.model.event.Item;
import app.model.event.Template;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TemplateTest {

    private Template template;
    private Usuario organizador;
    private Cuenta organizadorCuenta;
    private Item itemUno;
    private Item itemDos;
    private List<Item> items;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void end(){
        this.template = null;
        this.organizador = null;
        this.organizadorCuenta= null;
        this.itemUno = null;
        this.itemDos = null;
        this.items = new ArrayList<>();
    }

    @Test
    public void testTemplateDosItems() {
        // FIXTURE
        this.setUsuarios();
        this.setTemplateVacio();
        this.setItems();

        // STIMULUS
        this.template.agregarItem(this.itemUno, 2);
        this.template.agregarItem(this.itemDos, 1);

        // ASSERT
        assertEquals(2, this.template.obtenerCantidadItems());
    }

    private void setItems() {
        this.itemUno = new Item(this.getCosto(200), "Carne", 2);
        this.itemDos = new Item(this.getCosto(180), "Coca", 2);
    }

    private void setTemplateVacio() {
        this.template = new Template("Nuevo template", "Descripcion", this.organizador);
    }

    private void setUsuarios() {
        this.organizador = new Usuario("Orga", "Nizador", "organizador@email.com");

        this.organizadorCuenta = new Cuenta(this.organizador);
    }

    private Dinero getCosto(int costo){
        return new Dinero(costo);
    }

}
