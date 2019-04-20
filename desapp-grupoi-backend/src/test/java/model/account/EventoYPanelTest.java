package model.account;

import static org.junit.Assert.*;

import model.event.Canasta;
import model.event.Evento;
import model.event.Invitacion;
import model.event.Modalidad;
import model.event.PanelDeControl;

import org.joda.time.DateTime;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class EventoYPanelTest {

    private Evento evento;
    private PanelDeControl pControl;
    private Modalidad mod;
    private Usuario user;
    private Usuario user2;
    private Usuario user3;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        pControl = new PanelDeControl();
        
        user = new Usuario("Alejandro", "Rossi", "ale.h90@gmail.com", new DateTime().minus(20));
        user2 = new Usuario("invitado1", "apellido1", "mail1@gmail.com", new DateTime().minus(25));
        user3 = new Usuario("invitado2", "apellido2", "mail2@gmail.com", new DateTime().minus(27));
        
        pControl.addUser(user);
        pControl.addUser(user2);
        pControl.addUser(user3);
        
        mod = new Canasta();
        
        evento = new Evento(user,"Evento de Prueba", mod);
        evento.setPanelDeControl(pControl);
    }

//    @After
//    public void end(){
//    }

    @Test
    public void testEvento_aniadirUnInvitado() {
    	
    	evento.enviarInvitacion("mail1@gmail.com");
    	
        assertEquals(user2.getInvitaciones().get(0).getNombreEventoInvitacion(), "Evento de Prueba" );
    }
    
//    invitaRegistradoEinvitacionesPendientesNoSeModifica

//    @Test
//    public void testEvento_invitarPorLista() {
//
////        assertEquals( );
//    }
//    
//    @Test
//    public void testEvento_puedeConfirmarUnInvitado() {
//
////        assertEquals( );
//    }
//    
//    @Test
//    public void testPanel_diceSiExisteUsuarioRegistrado() {
//
////        assertEquals( );
//    }
//    
//    
//    @Test
//    public void testPanel_daInvitacionParaUserRegistrado() {
//
////        assertEquals( );
//    }
//    
//    @Test
//    public void testPanel_daInvitacionParaNoResgistrado() {
//
////        assertEquals( );
//    }
    
    
}
