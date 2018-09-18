package horario;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Dias {
	// key = inicio del turno
	// value = turno
	private long Id;
	private HashMap<Integer,Turnos> turnos = new HashMap<Integer,Turnos>();
	private Date date;
	private int dia;

	public  DbModel.Dias convertToDbModel()
	{
		DbModel.Dias result = new DbModel.Dias();
		result.setDate(date);
		result.setTurnos(convertTurnosToDbModel());
		return result;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public void setDia(int dia){this.dia  = dia;}

	public int getDia(){return dia;}

	private Map<Integer, DbModel.Turnos> convertTurnosToDbModel() {
	  HashMap<Integer, DbModel.Turnos> result = new HashMap<>();
	  turnos.forEach((k,v) -> result.put(k, v.convertToDbModel()));
	  return  result;
   }

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
