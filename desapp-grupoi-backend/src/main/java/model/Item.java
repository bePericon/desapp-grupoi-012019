package model;

public class Item {

	private int costo;
	private String nombreItem;
	
	
	public Item(int costo, String nombreItem) {
		this.costo = costo;
		this.nombreItem = nombreItem;
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
	
	
	
	
	
}
