package model.event;

public class Item {

	private int costo;
	private String nombreItem;
	private int personasPorUnidad;
	
	
	public Item(int costo, String nombreItem, int personasPorUnidad) {
		this.costo = costo;
		this.nombreItem = nombreItem;
		this.personasPorUnidad = personasPorUnidad; //lo que rinde cada item por persona
		
	}

	

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
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
