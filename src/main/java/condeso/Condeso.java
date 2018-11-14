package condeso;
import horario.HorarioEntrega;
import horario.HorarioMaster;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.lang.Math;

import horario.Turnos;

import java.time.Month;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import tiendas.Tiendas;

import java.util.List;

@Entity
@Table(name = "condeso")
public class Condeso {

	@Id
	private long id;
	@Column
	private TipoEmpleado tipo;
	@Column
	private String nombre;
	@Column
	private String abreviacion;
	@Column
	private boolean fijos;
	@Column
	private int level;
	@Column
	private boolean manana;
	@Column
	private boolean lunch;
	@Column
	private boolean tarde;
	@Column
	private boolean caja;
	@Column
	@Convert(converter= DbModel.LocalDateAttributeConverter.class)
	private LocalDate antiguedad;
	@Column
	private boolean masculino;
	@Column
	private boolean femenino;
	@Column
	private String color;

	@JoinColumn
	@OneToOne
	private HorarioEntrega entrega;

	@JoinColumn
	@ManyToOne
	private HorarioMaster master;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<tiendas.Tiendas> dondePuedeTrabajar;

	@Column
	private Contrato contrato;

	@ElementCollection
	private Map<Month, Integer> horasMes;

	public Condeso() {
	}
	@Transient
	private int priorityValue;
	@Transient
	private int diasSeguidos = 5;
	@Transient
	private int finesLibres;
	@Transient
	private Turnos[] personal = new Turnos[31];
	@Transient
	private int horasAsignadas = 0;
	@Transient
	private int maxHours;
	@Transient
	private int minHours;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TipoEmpleado getTipo() {
		return tipo;
	}

	public void setTipo(TipoEmpleado tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isFijos() {
		return fijos;
	}

	public void setFijos(boolean fijos) {
		this.fijos = fijos;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPriorityValue() {
		return priorityValue;
	}

	public void setPriorityValue(int priorityValue) {
		this.priorityValue = priorityValue;
	}

	public boolean isManana() {
		return manana;
	}

	public void setManana(boolean manana) {
		this.manana = manana;
	}

	public boolean isTarde() {
		return tarde;
	}

	public void setTarde(boolean tarde) {
		this.tarde = tarde;
	}

	public boolean isCaja() {
		return caja;
	}

	public void setCaja(boolean caja) {
		this.caja = caja;
	}

	public LocalDate getAntiguedad() {
		return antiguedad;
	}

	public Map<Month, Integer> getHorasMes() {
		return horasMes;
	}

	public void setHorasMes(Map<Month, Integer> horasMes) {
		this.horasMes = horasMes;
	}

	public boolean isFijo(){
		if(contrato == Contrato.Fijo) return true;
		return false;
	}

	public void setAntiguedad(LocalDate antiguedad) {
		this.antiguedad = antiguedad;
	}

	public int getDiasSeguidos(LocalDate fecha) {
		int dia = fecha.getDayOfMonth();
		int counter = 0;
		while(dia - 2- counter > 0 && personal[dia-2-counter] != null){
			counter++;
		}
		int i = 0;
		while(dia + i < 31 && personal[dia + i] != null){
			i++;
			counter++;
		}
		return counter;
	}

	public int getDiasSeguidos(){return diasSeguidos;}

	public void setDiasSeguidos(int diasSeguidos){this.diasSeguidos = diasSeguidos;}

	public int getFinesLibres() {
		return finesLibres;
	}

	public void setFinesLibres(LocalDate date) {
		int length = date.getMonth().length(date.isLeapYear());
		DayOfWeek first = LocalDate.of(date.getYear(), date.getMonth(), 1).getDayOfWeek();
		length = length % 7;
		DayOfWeek last = first.plus(length);
		if(last.getValue() < first.getValue()) finesLibres = 5;
		else finesLibres = 4;
	}

	public HorarioEntrega getEntrega() {
		return entrega;
	}

	public void setEntrega(HorarioEntrega entrega) {
		this.entrega = entrega;
	}

	public HorarioMaster getMaster() {
		return master;
	}

	public void setMaster(HorarioMaster master) {
		this.master = master;
	}

	public Turnos[] getPersonal() {
		return personal;
	}

	public void setPersonal(Turnos[] personal) {
		this.personal = personal;
	}

	public List<Tiendas> getDondePuedeTrabajar() {
		return dondePuedeTrabajar;
	}

	public void setDondePuedeTrabajar(List<Tiendas> dondePuedeTrabajar) {
		this.dondePuedeTrabajar = dondePuedeTrabajar;
	}

	public String getAbreviacion() {
		return abreviacion;
	}

	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}

	public boolean isLunch() {
		return lunch;
	}

	public void setLunch(boolean lunch) {
		this.lunch = lunch;
	}

	public boolean isMasculino() {
		return masculino;
	}

	public void setMasculino(boolean masculino) {
		this.masculino = masculino;
	}

	public boolean isFemenino() {
		return femenino;
	}

	public void setFemenino(boolean femenino) {
		this.femenino = femenino;
	}

	public void setFinesLibres(int finesLibres) {
		this.finesLibres = finesLibres;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
		switch(contrato){
			case MiniJob: maxHours = 47;
			minHours = 0;
			break;
			case otros:
			case Fijo:	maxHours = 220;
			minHours = 50;
			break;

		}
	}

	public void cincoMas(){
		if(contrato ==Contrato.MiniJob) return;
		maxHours *= 1.05;
	}

	public int getHoras(){ return (maxHours + minHours)/2;}

	public void setHorasAsignadas(int horasAsignadas){this.horasAsignadas = horasAsignadas;}

	public int getHorasAsignadas(){return horasAsignadas;}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BooleanProperty Manana() {
		return new SimpleBooleanProperty(manana);
	}

	public ObservableValue<Boolean> Tarde() {
		return  new SimpleBooleanProperty(tarde);
	}

	public ObservableValue<Boolean> Nivel() {
		return  new SimpleBooleanProperty(caja);
	}

	public void asignarTurno(Turnos elTurno){
		if(elTurno == null) return;
		if(elTurno.isOcupado()) elTurno.getCondeso().borrarTurno(elTurno);
		int day = elTurno.getDate().getDayOfMonth();
		elTurno.setCondeso(this);
		elTurno.getDay().resetMinimoTurnos();
		elTurno.change();
		personal[day-1] = elTurno;
		horasAsignadas += elTurno.getDuracion();
		DayOfWeek dia = elTurno.getDate().getDayOfWeek();
		if(dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY){
			switch (dia){
				case SUNDAY: if(day-2 > 0 && personal[day-2] == null) finesLibres--;
				break;
				case SATURDAY: if(day < personal.length && personal[day] == null) finesLibres--;
				break;
				default: break;
			}
		}

	}

	public int horasRestantes(){
		return maxHours - horasAsignadas;
	}

	public Turnos cambiarTurno(Turnos elTurno){
		int day = elTurno.getDate().getDayOfMonth();
		elTurno.getDay().resetMinimoTurnos();
		elTurno.getDay().resetMinimoTurnos();
		Turnos aRegresar = personal[day-1];
		personal[day-1].setCondeso(null);
		int horas = personal[day-1].getDuracion();
		int horasNuevas = elTurno.getDuracion();
		personal[day-1] = elTurno;
		elTurno.setCondeso(this);
		elTurno.change();
		aRegresar.change();
		horasAsignadas += (horasNuevas - horas);
		return aRegresar;
	}

	public void setMaxHours(int maxHours){
		//this.maxHours = contrato == Contrato.MiniJob ? Math.min(maxHours, 45) :  Math.max(maxHours, 50);

		switch(contrato){
			case Fijo: this.maxHours = 220;
			break;
			case MiniJob: if(maxHours == 0) this.maxHours = 47;
				else this.maxHours = Math.min(maxHours, 47);
			break;
			case otros: if(maxHours == 0) this.maxHours = 220;
			else if(maxHours < 50) this.maxHours = 50;
			else this.maxHours = maxHours;
			break;
		}
		}

	public int getMaxHours(){return maxHours;}

	public Turnos borrarTurno(Turnos elTurno){
		int dia = elTurno.getDate().getDayOfMonth() - 1;
		Turnos supuesto = personal[dia];
		if(elTurno == supuesto){
		    LocalDate date = elTurno.getDate();
		    personal[dia] = null;
		    horasAsignadas-= supuesto.getDuracion();
		    elTurno.setCondeso(null);
		    DayOfWeek day = date.getDayOfWeek();
		    if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY){
		        switch (day){
                    case SATURDAY: if(dia < date.getMonth().length(date.isLeapYear()) && personal[dia+1] == null) finesLibres++;
                    break;
                    case SUNDAY: if(dia > 0 && personal[dia-1] == null) finesLibres++;
                    break;
                }
            }
            return elTurno;
		}
		return null;
	} // falta checar fines de semana

	public int getMinHours(){return minHours;}

	public void setMinHours(int minHours){
		//this.minHours = minHours;
		//this.minHours = contrato == Contrato.MiniJob ? Math.min(minHours, 45) :  Math.max(minHours, 50);

		switch(contrato){
			case Fijo: this.minHours = 50;
			break;
			case MiniJob:if(minHours > 47) this.minHours = 47;
			else this.minHours = minHours;
			break;
			case otros: if(minHours < 50) this.minHours = 50;
			else this.minHours = minHours;
			break;
		}
	}

	public boolean checkMax(Turnos elTurno){ if(maxHours <= horasAsignadas + elTurno.getDuracion()) return false;
	return true;}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return  nombre + ": " +getHorasAsignadas();
	}

	public ObservableValue<Boolean> Lunch() {
		return  new SimpleBooleanProperty(lunch);
	}

	public void printCondeso(){
		System.out.println(nombre);
		List<Turnos> losTurnos = new ArrayList<>();
		for(int i =0; i < personal.length; i++){
			Turnos elturno = personal[i];
			if(elturno != null){
				losTurnos.add(elturno);
				System.out.print(i + "\t");
			}
		}
		System.out.println();
		for(int i = 0; i < losTurnos.size(); i++){
			Turnos elTurno = losTurnos.get(i);
			System.out.print(elTurno.getInicio() + "\t");
		}
		System.out.println();
		for (int i = 0; i < losTurnos.size(); i++){
			Turnos elTurno = losTurnos.get(i);
			System.out.print(elTurno.getFin() + "\t");
		}
	}

	public void checkMaxMin() {
		if(maxHours == 0){
			switch (contrato){
				case MiniJob: maxHours = 47;
				break;
				case otros: maxHours = 220;
				break;
				case Fijo: maxHours = 220;
				break;
			}
		if(minHours == 0){
			switch (contrato){
				case otros:
				case Fijo: minHours = 50;
				break;
			}
		}
		if(contrato == Contrato.MiniJob && minHours >= 47){
			minHours = 47;
		}
		}
	}

	public boolean isGM(){
		if(tipo == TipoEmpleado.GM) return true;
		return false;
	}

	public ArrayList<Turnos> turnosToList(){
		ArrayList<Turnos> losTurnos = new ArrayList<>();
		for(Turnos elTurno : personal){
			if(elTurno != null) losTurnos.add(elTurno);
		}
		return losTurnos;
	}

	public ArrayList<Turnos> getTurnosSeguidos(int dia){
		if(dia < 1 || dia > 31) return null;
		ArrayList<Turnos> losTurnos = new ArrayList<>();
		int counter = 0;
		Turnos elTurno;
		while(dia - 2- counter > 0 && (elTurno = personal[dia-2-counter]) != null){
			counter++;
			losTurnos.add(elTurno);
		}
		int i = 0;
		while(dia + i < 31 && (elTurno = personal[dia + i]) != null){
			i++;
			losTurnos.add(elTurno);
		}
		return losTurnos;
	}
}
