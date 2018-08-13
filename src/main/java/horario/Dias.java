package horario;

import java.sql.Date;
import java.util.HashMap;

public class Dias {
	// key = inicio del turno
	// value = turno
	private HashMap<Integer,Turnos> turnos = new HashMap<Integer,Turnos>();
	private Date date;

	public Dias(Date date) {
		this.date = date;
	}
	public HashMap<Integer,Turnos> getTurnos() {
		return turnos;
	}
	public void setTurnos(HashMap<Integer,Turnos> turnos) {
		this.turnos = turnos;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
