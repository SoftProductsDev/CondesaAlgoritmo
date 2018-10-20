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

	private Set<Dias> dias;

	private String name;

	public Plantillas() {
		dias = new HashSet<Dias>();
	}

	public Set<Dias> getDias(){return dias; }

	public void setDias(Set<Dias> dias) {
		this.dias = dias;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public DbModel.Plantillas convertToDbModel()
	{
		DbModel.Plantillas result = new DbModel.Plantillas();
		result.setId(Id);
		dias.forEach((k) -> result.getDias().add(k.convertToDbModel()));
		return result;
	}

	private Dias[] getSemana(){
		Dias[] days = new Dias[7];
		for(Dias dia : dias){
			switch(dia.getDay()){
				case MONDAY: days[0] = dia;
				break;
				case TUESDAY: days[1] = dia;
					break;
				case WEDNESDAY: days[2] = dia;
					break;
				case THURSDAY: days[3] = dia;
					break;
				case FRIDAY: days[4] = dia;
					break;
				case SATURDAY: days[5] = dia;
					break;
				case SUNDAY: days[6] = dia;
					break;
			}
		}
		return days;
	}

	public HorarioMaster generateMaster(LocalDate date){
		Dias[] days = getSemana();
		int year = date.getYear();
		Month month = date.getMonth();

		int dias = date.lengthOfMonth();
		HashMap<LocalDate, Dias> master = new HashMap<>();
		LocalDate date2;
		Dias elDia;
		for(int i = 0; i < dias; i++){
			date2 = LocalDate.of(year, month, i+1);
			elDia = generateDay(date2, days);
			master.put(date2, elDia);
		}
		return new HorarioMaster(master);
 	}

 	private Dias generateDay(LocalDate date, Dias[] days){ //TODO checar consistencia
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
