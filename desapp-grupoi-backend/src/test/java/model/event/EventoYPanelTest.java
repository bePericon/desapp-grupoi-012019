package model.event;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.account.Usuario;
import model.event.Canasta;
import model.event.Evento;

import model.event.Modalidad;
import model.event.PanelDeControl;

import org.joda.time.DateTime;
import org.junit.*;


public class EventoYPanelTest {

    private Evento evento;
    PanelDeControl pControl;
    
    private Usuario user;
    private Usuario user1;
    private Usuario user2;

    @Before
    public void init(){
    	
        Modalidad mod;
        pControl = new PanelDeControl();
        
        user = new Usuario("Alejandro", "Rossi", "ale.h90@gmail.com", new DateTime().minus(20));
        user1 = new Usuario("invitado1", "apellido1", "mail1@gmail.com", new DateTime().minus(25));
        user2 = new Usuario("invitado2", "apellido2", "mail2@gmail.com", new DateTime().minus(27));
        
        pControl.addUser(user);
        pControl.addUser(user1);
        pControl.addUser(user2);
        
        mod = new Canasta();
        Template tem = new Template("nombre", "descripcion");
        tem.setModalidad(mod);

        evento = new Evento(user,"Evento de Prueba");
        evento.setTemplate(tem);
//        evento = new Evento(user,"Evento de Prueba");
//        evento.setModalidad(mod);
        evento.setPanelDeControl(pControl);
    }



    @Test
    public void testEventoAniadirUnInvitado() {
    	
    	evento.enviarInvitacion("mail1@gmail.com");
    	
        assertEquals(user1.getInvitaciones().get(0).getNombreEventoInvitacion(), "Evento de Prueba" );
    }
 

    @Test
    public void testEventoInvitarPorLista() {
    	
    	List<String> listaInvitados = new ArrayList<String>();
    	listaInvitados.add("mail1@gmail.com");
    	listaInvitados.add("mail2@gmail.com");
    	evento.invitarPorLista(listaInvitados);
    	
        assertEquals(user1.getInvitaciones().get(0).getNombreEventoInvitacion(), "Evento de Prueba" );
        assertEquals(user2.getInvitaciones().get(0).getNombreEventoInvitacion(), "Evento de Prueba" );
    }
    
    @Test
    public void testEventoPuedeConfirmarUnInvitado() {

    	evento.enviarInvitacion("mail1@gmail.com");
    	
    	evento.confirmarAsistencia(user1);
    	
    	assertFalse(evento.getAsistentes().isEmpty());
    }
   
    @Test
    public void testPanelReconoceUsuarioRegistrado() {
    	boolean ret;
    	ret = pControl.existeUsuarioRegistrado("ale.h90@gmail.com");
    	assertTrue(ret);
    }

    @Test
    public void testPanelNoReconoceUsuarioSinRegistrar() {
    	boolean ret;
    	ret = pControl.existeUsuarioRegistrado("sarasasasa@sasasa.com");
    	assertFalse(ret);
    }
    

    @Test
    public void testPanelGuardaInvitacionParaNoResgistrado() {

    	evento.enviarInvitacion("noRegistrado@gmail.com");
    	
    	assertTrue(pControl.invitacionesPendientes.size() >0);
    	
    }
    
    
    
}
