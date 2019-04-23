package model.event;

import static org.junit.Assert.*;

import model.account.Usuario;
import model.event.Evento;
import model.event.Fiesta;
import model.event.Modalidad;
import model.event.PanelDeControl;

import org.joda.time.DateTime;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

public class FiestaTest {

    private Evento evento;
    private PanelDeControl pControl;
    private Modalidad fiesta;
    private Usuario user;
    private Usuario user1;
    private Usuario user2;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        pControl = new PanelDeControl();
        
        user = new Usuario("Alejandro", "Rossi", "ale.h90@gmail.com", new DateTime().minus(20));
        user1 = new Usuario("invitado1", "apellido1", "mail1@gmail.com", new DateTime().minus(25));
        user2 = new Usuario("invitado2", "apellido2", "mail2@gmail.com", new DateTime().minus(27));
        
        pControl.addUser(user);
        pControl.addUser(user1);
        pControl.addUser(user2);
        
        
        Date maniana = new Date(); 
        Calendar c = Calendar.getInstance(); 
        c.setTime(maniana); 
        c.add(Calendar.DATE, 1); //Se suma un dia
        maniana = c.getTime();
        
        fiesta = new Fiesta(maniana);
        
        evento = new Evento(user, "Festichulli", fiesta);
        evento.setPanelDeControl(pControl);
    }


    @Test
    public void testFiesta_creaFiesta() {
    	
    	
    }
 

//    @Test
//    public void testFiesta_calculaMercaderiaParaDosPersonas() {
//    	
//    	
//    }
//    
//    @Test
//    public void testFiesta_calculaMercaderiaParaCincoPersonas() {
//    	
//    	
//    }
//    
//    @Test
//    public void testFiesta_recalculaAlConfirmarUnaPersonaMas() {
//    	
//    	
//    }
//    
//    @Test
//    public void testFiesta_noSeSumaNadieLuegoDeLaFechaLimite() {
//    	
//    	
//    }
//    
//    @Test
//    public void testFiesta_noRecalculaSiConfirmanLuegoDeFechaLimite() {
//    	
//    	
//    }
//    
//    @Test
//    public void testFiesta_seSumanLosGastosAlOrganizador() {
//    	
//    	
//    	
//    }
//    
//    @Test
//    public void testFiesta_seSumanLosGastosRecalculadosAlOrganizador() {
//    	
//    	
//    }
//    
//    @Test
//    public void testFiesta_noSeSumanLosGastosAlOrganizadorSiConfirmanDespuesDeFechaLimite() {
//    	
//    	
//    	
//    }
    
}
