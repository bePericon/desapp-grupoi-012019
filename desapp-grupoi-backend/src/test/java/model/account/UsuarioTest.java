package model.account;

import org.joda.time.DateTime;
import org.junit.*;

import static org.junit.Assert.*;

public class UsuarioTest {

    @Test
    public void creacionUsuarioCompleto() {
        Usuario usuario = new Usuario("Emmanuel", "Pericon", "epericon@gmail.com", this.cumpleaños());

        assertFalse(usuario.getNombre().isEmpty());
        assertFalse(usuario.getApellido().isEmpty());
        assertFalse(usuario.getEmail().isEmpty());
        assertTrue(usuario.getFechaNac().isEqual(new DateTime(1992,11,27,0,0)));
    }

    // Metodos auxliares
    private DateTime cumpleaños() {
        return new DateTime(1992,11,27,0,0);
    }
}
