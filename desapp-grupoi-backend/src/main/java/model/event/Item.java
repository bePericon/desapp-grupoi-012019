package model.event;

import model.account.Dinero;

public class Item {

	private Dinero costo;
	private String nombreItem;
	private int personasPorUnidad;
	
	
	public Item(Dinero costo, String nombreItem, int personasPorUnidad) {
		this.costo = costo;
		this.nombreItem = nombreItem;
		this.personasPorUnidad = personasPorUnidad; //lo que rinde cada item por persona
		
	}

	

	public Dinero getCosto() {
		return costo;
	}

	public void setCosto(Dinero costo) {
		this.costo = costo;
	}

	public String getNombreItem() {
		return nombreItem;
	}

	public void setNombreItem(String nombreItem) {
		this.nombreItem = nombreItem;
	}

	public int getPersonasPorUnidad() {
		return personasPorUnidad;
	}

	public void setPersonasPorUnidad(int personasPorUnidad) {
		this.personasPorUnidad = personasPorUnidad;
	}
	
	
	
	
	
}
