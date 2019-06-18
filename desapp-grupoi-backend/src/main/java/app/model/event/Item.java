package app.model.event;

import app.model.account.Dinero;

import java.io.Serializable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item")
public class Item implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@OneToOne(cascade=CascadeType.ALL)
	private Dinero costo;

	private String nombreItem;
	private int personasPorUnidad;

	public Item() {
	}

	public Item(Dinero costo, String nombreItem, int personasPorUnidad) {
		this.costo = costo;
		this.nombreItem = nombreItem;
		this.personasPorUnidad = personasPorUnidad; //lo que rinde cada item por persona
		
	}

	// Getters y Setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
