package model.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import model.account.Usuario;

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
        
        evento = new Evento(user, "Festichulli");
        evento.setModalidad(fiesta);
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

    	((Fiesta) fiesta).calcularCompras(); 
    	
    	fiesta.calcularCostos(); // TODO: esto deberia ejecutarse al confirmar, a hacemos con AOP
    	
    	 assertEquals(350, fiesta.costoTotal.getMonto(), 0.0);
    }
    
	@Test
	public void testFiestaAgregoMasVariedadDeItemsYCalculaMercaderiaParaDosPersonas() {
		 
		/// FIXTURE
		 birra = new Item(50,"preciosa",1);
		 galletitas = new Item(25,"don satur",1);
	
		 fiesta.addItemsAComprar(birra);
		 fiesta.addItemsAComprar(galletitas);
		 
		 //ESTIMULET
		((Fiesta) fiesta).calcularCompras(); //Solo si la casteo
    	
    	fiesta.calcularCostos(); // TODO: esto deberia ejecutarse al confirmar, a hacemos con AOP?
    	
    	//ASSERT
    	 assertEquals(500, fiesta.costoTotal.getMonto(), 0.0);
	}
    
    @Test
    public void testFiestaCalculaMercaderiaParaCincoPersonas() {
    	
    	 birra = new Item(50,"preciosa",1);
		 galletitas = new Item(25,"don satur",1);
	
		 fiesta.addItemsAComprar(birra);
		 fiesta.addItemsAComprar(galletitas);
		 
		 Usuario user3 = new Usuario("invitado3", "apellido3", "mail3@gmail.com", new DateTime().minus(5));
		 Usuario user4 = new Usuario("invitado4", "apellido4", "mail4@gmail.com", new DateTime().minus(10));
		 Usuario user5 = new Usuario("invitado5", "apellido5", "mail5@gmail.com", new DateTime().minus(15));
		 
		 pControl.addUser(user3);
		 pControl.addUser(user4);
		 pControl.addUser(user5);
		 
		 evento.enviarInvitacion("mail3@gmail.com");
		 evento.enviarInvitacion("mail4@gmail.com");
		 evento.enviarInvitacion("mail5@gmail.com");
		 
		 evento.confirmarAsistencia(user3);
		 evento.confirmarAsistencia(user4);
		 evento.confirmarAsistencia(user5);
		 
		((Fiesta) fiesta).calcularCompras(); //Solo si la casteo
   	
		fiesta.calcularCostos(); // TODO: esto deberia ejecutarse al confirmar, a hacemos con AOP?
   	
		assertEquals(925, fiesta.costoTotal.getMonto(), 0.0);
    	
    }
    
//    @Test
//    public void testFiestaRecalculaAlConfirmarUnaPersonaMas() {
//    	
//    	
//    }
    
    @Test
    public void testFiestaNoSeSumaNadieLuegoDeLaFechaLimite() {
    	
        Date ayer = new Date(); 
        Calendar c1 = Calendar.getInstance(); 
        c1.setTime(ayer); 
        c1.add(Calendar.DATE, -1); //Se resta un dia
        ayer = c1.getTime();
    	
        Modalidad fiesta2 = new Fiesta(ayer);
        fiesta2.setItemsAComprar(listaItems);
        
        evento = new Evento(user, "fiesta dos");
        evento.setPanelDeControl(pControl);
        evento.setModalidad(fiesta2);
        
        fiesta.setOrganizador(user);

    	evento.enviarInvitacion("mail1@gmail.com");
    	
    	evento.confirmarAsistencia(user1);
    	
    	
    	assertTrue(evento.getAsistentes().isEmpty());
    }
    

    
}
