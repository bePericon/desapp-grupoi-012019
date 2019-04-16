package model.account;

import org.joda.time.DateTime;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.Assert.*;

public class UsuarioTest {

    @Mock
    Usuario mockUsuario;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void end(){
        // Lo que se quiera ejecutar despues de cada test. Ej: limpieza de datos.
    }

    /// Metodos auxliares ///
    private DateTime cumpleaños() {
        return new DateTime(1992,11,27,0,0);
    }

    private OngoingStubbing<EnumEstados.EstadoSituacionDeuda> mockUsuarioSituacion(EnumEstados.EstadoSituacionDeuda estado){
        return Mockito.when(this.mockUsuario.getSituacion()).thenReturn(estado);
    }
    ////////////////////////

    @Test
    public void creacionUsuarioCompleto() {
        Usuario usuario = new Usuario("Emmanuel", "Pericon", "epericon@gmail.com", this.cumpleaños());

        assertFalse(usuario.getNombre().isEmpty());
        assertFalse(usuario.getApellido().isEmpty());
        assertFalse(usuario.getEmail().isEmpty());
        assertTrue(usuario.getFechaNac().isEqual(new DateTime(1992,11,27,0,0)));
    }

    @Test
    public void testEstadoSituacionDeuda_usuarioSituacionNormal() {
        this.mockUsuarioSituacion(EnumEstados.EstadoSituacionDeuda.NORMAL);

        assertTrue(this.mockUsuario.getSituacion().esNormal());
    }
    @Test
    public void testEstadoSituacionDeuda_usuarioSituacionCumplidor() {
        this.mockUsuarioSituacion(EnumEstados.EstadoSituacionDeuda.CUMPLIDOR);

        assertTrue(this.mockUsuario.getSituacion().esCumplidor());
    }
    @Test
    public void testEstadoSituacionDeuda_usuarioSituacionMoroso() {
        this.mockUsuarioSituacion(EnumEstados.EstadoSituacionDeuda.MOROSO);

        assertTrue(this.mockUsuario.getSituacion().esMoroso());
    }
}
