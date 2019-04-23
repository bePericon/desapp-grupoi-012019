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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventoFiestaTest {

    private Evento evento;
    private PanelDeControl pControl;
    private Modalidad fiesta;
    private Usuario user;
    private Usuario user1;
    private Usuario user2;
    private List<Item> listaItems;
    private Item asado;
    private Item gaseosa;
    private Item picada;
    private Item birra;
    private Item galletitas;

    @Before
    public void init(){
//        MockitoAnnotations.initMocks(this);
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
        
        asado = new Item(150,"asado por kilo",6);
        gaseosa = new Item(100,"cocacola 3L", 4);
        picada = new Item(100,"picadita de queso", 4);
        
        listaItems = new ArrayList<Item>();
        listaItems.add(asado);
        listaItems.add(gaseosa);
        listaItems.add(picada);
        
        fiesta = new Fiesta(maniana);
        fiesta.setItemsAComprar(listaItems);
        
        evento = new Evento(user, "Festichulli", fiesta);
        evento.setPanelDeControl(pControl);
      
        List<String> listaInvitados = new ArrayList<String>();
    	listaInvitados.add("mail1@gmail.com");
    	listaInvitados.add("mail2@gmail.com");
    	
    	evento.invitarPorLista(listaInvitados);
    	evento.confirmarAsistencia(user1);
    	evento.confirmarAsistencia(user2);
    	
    	fiesta.setOrganizador(user);
    }
 

    @Test
    public void testFiesta_calculaMercaderiaParaDosPersonas() {

//    	fiesta.calcularCompras();// POR QUE CARAJO NO ME TOMA ESTA FUNCION?
//    	evento.getModalidad(). calcularCompras(); // tampoco me la toma asi

    	((Fiesta) fiesta).calcularCompras(); //Solo si la casteo
    	
    	fiesta.calcularCostos(); // TODO: esto deberia ejecutarse al confirmar, a hacemos con AOP
    	
    	 assertEquals(fiesta.costoTotal.getMonto(), 350, 0.0);
    }
    
	@Test
	public void testFiesta_agregoMasVariedadDeItemsYCalculaMercaderiaParaDosPersonas() {
		 birra = new Item(50,"preciosa",1);
		 galletitas = new Item(25,"don satur",1);
	
		 fiesta.addItemsAComprar(birra);
		 fiesta.addItemsAComprar(galletitas);
		 
		((Fiesta) fiesta).calcularCompras(); //Solo si la casteo
    	
    	fiesta.calcularCostos(); // TODO: esto deberia ejecutarse al confirmar, a hacemos con AOP
    	
    	 assertEquals(fiesta.costoTotal.getMonto(), 500, 0.0);
	}
    
//    @Test
//    public void testFiesta_calculaMercaderiaParaCincoPersonas() {
//    	
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
