package horario;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Id;

public class Plantillas {
	private long Id;

	private Dias[] dias;

	private String name;

	public Plantillas() {
		dias = new Dias[7];
	}

	public Dias[] getDias(){return dias; }

	public void setDia(int dia, Dias elDia) {
		if( dia < 1 || dia > 7) return;
		dias[dia-1] = elDia;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}


	public HorarioMaster generateMaster(LocalDate date){
		int year = date.getYear();
		Month month = date.getMonth();

		int dias = date.lengthOfMonth();
		HashMap<LocalDate, Dias> master = new HashMap<>();
		LocalDate date2;
		Dias elDia;
		for(int i = 0; i < dias; i++){
			date2 = LocalDate.of(year, month, i+1);
			elDia = generateDay(date2, this.dias);
			master.put(date2, elDia);
		}
		return new HorarioMaster(master);
 	}

 	private static Dias generateDay(LocalDate date, Dias[] days){ //TODO checar consistencia
		int dia = date.getDayOfWeek().getValue()-1;
		Set<Turnos> turnos = new HashSet<>();
		Dias elDia = days[dia];
		Set<Turnos> losTurnos = elDia.getTurnos(); //falta resolver
		Dias theDay = new Dias(date);
		Turnos turno;

		for(Turnos elTurno : losTurnos){
			turno = elTurno.duplicate();
			turno.setDay(theDay);
			turnos.add(turno);
		}
		theDay.setTurnos(turnos);
		return theDay;

	}


}
