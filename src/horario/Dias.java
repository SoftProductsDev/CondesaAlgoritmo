package horario;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Dias {
	// key = inicio del turno
	// value = turno
	private Set<Turnos> turnos = new HashSet<Turnos>();
	Date date = new Date(0);
	public Dias(Date date) {
		this.date = date;
	}
	public Set<Turnos> getTurnos() {
		return turnos;
	}

	public void setTurnos(Set<Turnos> turnos) {
		this.turnos = turnos;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
