package horario;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jdk.nashorn.api.tree.Tree;
import org.apache.poi.hssf.record.TableRecord;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import tiendas.Tiendas;

@Entity
@Table(name = "plantillas")
public class Plantillas {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@JoinColumn
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Dias> dias;

	@Column(name = "nombre")
	private  String nombre;

	public List<Dias> getDias(){return dias; }

	/*public void setDia(int dia, Dias elDia) {
		if( dia < 1 || dia > 7) return;
		dias.get(dia-1) = elDia;
	}*/

	public Plantillas(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDias(List<Dias> dias) {
		this.dias = dias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public HorarioMaster generateMaster(LocalDate date, Tiendas laTienda){
		int year = date.getYear();
		Month month = date.getMonth();
		List<LocalDate> diasCerrado = laTienda.getDiasDeCierre();
		int dias = date.lengthOfMonth();
		Map<LocalDate, Dias> master = laTienda.getMaster().getMes();
		LocalDate date2;
		Dias elDia;
		for(int i = 0; i < dias; i++){
			date2 = LocalDate.of(year, month, i+1);
			if(!diasCerrado.contains(date2)){
			elDia = generateDay(date2, this.dias, master);
			}
		}
		return laTienda.getMaster();
 	}

 	private static Dias generateDay(LocalDate date, List<Dias> days, Map<LocalDate, Dias> master){ //TODO checar consistencia
		int dia = date.getDayOfWeek().getValue()-1;
		Set<Turnos> turnos = new HashSet<>();
		Dias elDia = days.get(dia);
		Set<Turnos> losTurnos = elDia.getTurnos(); //falta resolver

		Dias theDay = master.get(date);
		if(theDay == null){
			theDay = new Dias(date);
			master.put(date, elDia);
			
		}

		Turnos turno;

		for(Turnos elTurno : losTurnos){
			turno = elTurno.duplicate();
			turno.setDay(theDay);
			turnos.add(turno);
		}
		theDay.setTurnos(turnos);
		return theDay;

	}

	@Override
	public String toString(){
		return nombre;
	}
}
