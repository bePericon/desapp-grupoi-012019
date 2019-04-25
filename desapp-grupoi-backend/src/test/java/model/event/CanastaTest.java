package model.event;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import model.account.Usuario;

public class CanastaTest {

	private Evento evento;
    private PanelDeControl pControl;
    private Canasta canasta;
    private Usuario user;
    private Usuario user1;
    private Usuario user2;
    private Item asado;
    private Item gaseosa;
    private Item picada;

//    @Before
//    public void init(){
//
//        pControl = new PanelDeControl();
//        registrarTresUsuariosAlSistema();
//
//        canasta = new Canasta();
//
//        evento = new Evento(user, "Canastita");
//        evento.setModalidad(canasta);
//        evento.setPanelDeControl(pControl);
//
//        invitarYconfirmarAsistenciaUsuarioUnoYDos();
//
//    	canasta.setOrganizador(user);
//    }
//
//
//
//	@Test
//	public void usuarioDosPuedeElegirUnaGaseosaParaComprar() {
//
//		//FIXTURE
//		cargarAsadoGaseosaYPicadaACanasta();
//
//		//STIMULET
//		canasta.elegirItemEnParticularPorIndice(user, 1);
//
//    	//ASSERT
//    	 assertFalse( canasta.getItemsComprados().isEmpty());
//	}
//
//	@Test
//	public void usuario2CompraUnItemCualquieraYEsAsado() {
//
//		// FIXTURE
//		cargarAsadoGaseosaYPicadaACanasta();
//
//		//STIMULET
//		canasta.elegirCuaquierItem(user2);
//
//    	//ASSERT
//    	 assertTrue(canasta.getItemsComprados().get(0).getItem().getNombreItem().equals("asado por kilo"));
//	}
	
//	@Test
//	public void usuariosNoInvitadosNoPuedenComprar() {
//		// FIXTURE
//		 Usuario user3 = new Usuario("invitado3", "apellido3", "mail3@gmail.com", new DateTime().minus(5));
//
//		//STIMULET
//		 canasta.elegirCuaquierItem(user3);
//
//    	//ASSERT
//    	 assertTrue(canasta.getItemsComprados().isEmpty());
//	}
	
	
//	@Test
//	public void usuariosSinConfirmarNoPuedeComprar() {
//		// FIXTURE
//		 Usuario user3 = new Usuario("invitado3", "apellido3", "mail3@gmail.com", new DateTime().minus(5));
//		 pControl.addUser(user3);
//		 evento.enviarInvitacion("mail3@gmail.com");
//		//STIMULET
//		 canasta.elegirCuaquierItem(user3);
//
//		 //ASSERT
//		 assertTrue(canasta.getItemsComprados().isEmpty());
//	}
	
	
	//TODO: luego de tener lo de baquita
//	@Test
//	public void seGeneraUnaListaDeGastosPorPersonaLuegoDeHacerseLasCompras() {
//		// FIXTURE
//		
//
//		//STIMULET
//		
//
//    	//ASSERT
////    	 assertEquals(500, canasta.costoTotal.getMonto(), 0.0);
//	}
	
	
	
	

	
	
//	Metodos Auxiliares
	
//	private void cargarAsadoGaseosaYPicadaACanasta() {
//		List<Item> listaItems = crearListaDeItemsConAsadoGaseosaYPicada();
//		canasta.setItemsAComprar(listaItems);
//	}
//
//
//	private List<Item> crearListaDeItemsConAsadoGaseosaYPicada() {
//		  asado = new Item(150,"asado por kilo",6);
//	      gaseosa = new Item(100,"cocacola 3L", 4);
//	      picada = new Item(100,"picadita de queso", 4);
//
//	      List<Item> listaItems = new ArrayList<Item>();
//	      listaItems.add(asado);
//	      listaItems.add(gaseosa);
//	      listaItems.add(picada);
//
//	      return listaItems;
//	}
//
//	public void registrarTresUsuariosAlSistema() {
//        user = new Usuario("Alejandro", "Rossi", "ale.h90@gmail.com", new DateTime().minus(20));
//        user1 = new Usuario("invitado1", "apellido1", "mail1@gmail.com", new DateTime().minus(25));
//        user2 = new Usuario("invitado2", "apellido2", "mail2@gmail.com", new DateTime().minus(27));
//
//        pControl.addUser(user);
//        pControl.addUser(user1);
//        pControl.addUser(user2);
//	}
//
//	public void invitarYconfirmarAsistenciaUsuarioUnoYDos() {
//		 List<String> listaInvitados = new ArrayList<String>();
//	     listaInvitados.add("mail1@gmail.com");
//	     listaInvitados.add("mail2@gmail.com");
//
//	     evento.invitarPorLista(listaInvitados);
//	     evento.confirmarAsistencia(user1);
//	     evento.confirmarAsistencia(user2);
//
//	}
	
	
}
