package model.account;

import org.joda.time.DateTime;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
        assertTrue(this.usuarioTest.getFechaNac().isEqual(new DateTime(1992,11,27,0,0)));
    }

    @Test
    public void testEsContraseñaCorrectaSontraseñaCorrecta() {
        this.usuarioTest.setContrasenia("12345678");

        assertTrue(this.usuarioTest.esContraseniaCorrecta("12345678"));
    }

    // Methods aux
    private DateTime cumpleaños() {
        return new DateTime(1992,11,27,0,0);
    }

}
