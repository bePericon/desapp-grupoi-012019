package model.event;

import model.account.Usuario;

import java.util.Date;

public class Fiesta extends Modalidad {

	
	private Date deadline;

	public Fiesta(Usuario organizador, Date deadline) {
		super(organizador);
		this.deadline=deadline;
	}

	
	public Date getDeadline() {
		return deadline;
	}
	
	//no puede cambiar la fecha del deadline
	
	
}
