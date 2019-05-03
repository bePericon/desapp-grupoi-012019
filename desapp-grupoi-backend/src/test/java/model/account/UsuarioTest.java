package model.account;

import app.model.account.Cuenta;
import app.model.account.Usuario;
import org.joda.time.DateTime;
import org.junit.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.Assert.*;

public class UsuarioTest {

    private Usuario usuarioTest;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        this.usuarioTest = new Usuario();
    }

    @Test
    public void testCreacionUsuarioCompletoDatosCorrectos() {
       this.usuarioTest = new Usuario("Emmanuel", "Pericon", "epericon@gmail.com", this.cumpleaños());
       this.usuarioTest.setCuenta(Mockito.mock(Cuenta.class));

        assertNotNull(this.usuarioTest.getCuenta());
        assertFalse(this.usuarioTest.getNombre().isEmpty());
        assertFalse(this.usuarioTest.getApellido().isEmpty());
        assertFalse(this.usuarioTest.getEmail().isEmpty());
        assertTrue(this.usuarioTest.getFechaNac().equals(new Date(1992,11,27,0,0)));
    }

    @Test
    public void testEsContraseñaCorrectaSontraseñaCorrecta() {
        this.usuarioTest.setContrasenia("12345678");

        assertTrue(this.usuarioTest.esContraseniaCorrecta("12345678"));
    }

    // Methods aux
    private Date cumpleaños() {
        return new Date(1992,11,27,0,0);
    }

}
