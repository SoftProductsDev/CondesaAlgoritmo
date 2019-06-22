package condeso;
import DbController.HibernateCrud;
import horario.HorarioEntrega;
import horario.HorarioMaster;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.lang.Math;

import horario.Turnos;

import java.time.Month;
import java.util.*;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.hibernate.annotations.*;
import tiendas.Tiendas;

@Entity
@Table(name = "condeso")
public class Condeso {

	@Id
	private long id;
	@Column
	private TipoEmpleado type;
	@Column
	private String name;
	@Column
	private String nameAbreviate;
	@Column
	private boolean fijos;
	@Column
	private int level;
	@Column
	private boolean morning;
	@Column
	private boolean lunch;
	@Column
	private boolean evening;
	@Column
	private boolean register;
	@Column
	private LocalDate dateTime;
	@Column
	private boolean male;
	@Column
	private boolean female;
	@Column
	private String color;
	public int phonenumber;
	public String mail;

	@Transient
	private Availability deliverySchedule;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<tiendas.Tiendas> shop;

	@Column
	private Contrato contract;

	@JoinColumn
	@OneToMany( fetch = FetchType.EAGER)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private transient Map<LocalDate, HorasMes> horasMes;

	private List<HorasMes>  monthHours;

	public Condeso() {
		horasMes = new HashMap<>();
	}
	@Transient
	private transient int priorityValue;
	@Transient
	private transient int diasSeguidos = 5;
	@Transient
	private transient int finesLibres;
	@Transient
	private transient Turnos[] personal = new Turnos[31];
	@Transient
	private transient int horasAsignadas = 0;
	@Transient
	private transient int maxHours;
	@Transient
	private transient int minHours;
	@Transient
	private transient LocalDate fecha;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TipoEmpleado getTipo() {
		return type;
	}

	public void setTipo(TipoEmpleado tipo) {
		this.type = tipo;
	}

	public String getNombre() {
		return name;
	}

	public void setNombre(String nombre) {
		this.name = nombre;
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

	public LocalDate getFecha(){return fecha;}

	public void setFecha(LocalDate fecha){this.fecha = fecha;}

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
		return morning;
	}

	public void setManana(boolean manana) {
		this.morning = manana;
	}

	public boolean isTarde() {
		return evening;
	}

	public void setTarde(boolean tarde) {
		this.evening = tarde;
	}

	public boolean isCaja() {
		return register;
	}

	public void setCaja(boolean caja) {
		this.register = caja;
	}

	public LocalDate getAntiguedad() {
		return dateTime;
	}

	public Map<LocalDate, HorasMes> getHorasMes() {
		return horasMes;
	}

	public void setHorasMes(Map<LocalDate, HorasMes> horasMes) {
		this.horasMes = horasMes;
	}

	public void setHorasMes(LocalDate date, HorasMes integer){
		this.horasMes.put(date, integer);
	}

	public void setHorasMes(LocalDate month){
		HorasMes horas = new HorasMes();
		horas.setHoras(horasAsignadas);
		horas.setDate(month);
		this.horasMes.put(month,  horas);
	}

	public boolean isFijo(){
		if(contract == Contrato.Fijo) return true;
		return false;
	}

	public void setAntiguedad(LocalDate antiguedad) {
		this.dateTime = antiguedad;
	}

	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String pmail) {
		this.mail = pmail;
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

	public Availability getEntrega() {
		return deliverySchedule;
	}

	public void setEntrega(Availability entrega) {
		this.deliverySchedule = entrega;
	}

	public Turnos[] getPersonal() {
		return personal;
	}

	public void setPersonal(Turnos[] personal) {
		this.personal = personal;
	}

	public List<Tiendas> getDondePuedeTrabajar() {
		return shop;
	}

	public void setDondePuedeTrabajar(List<Tiendas> dondePuedeTrabajar) {
		this.shop = dondePuedeTrabajar;
	}

	public String getAbreviacion() {
		return nameAbreviate;
	}

	public void setAbreviacion(String abreviacion) {
		this.nameAbreviate = abreviacion;
	}

	public boolean isLunch() {
		return lunch;
	}

	public void setLunch(boolean lunch) {
		this.lunch = lunch;
	}

	public boolean isMasculino() {
		return male;
	}

	public void setMasculino(boolean masculino) {
		this.male = masculino;
	}

	public boolean isFemenino() {
		return female;
	}

	public void setFemenino(boolean femenino) {
		this.female = femenino;
	}

	public void setFinesLibres(int finesLibres) {
		this.finesLibres = finesLibres;
	}

	public Contrato getContrato() {
		return contract;
	}

	public void setContrato(Contrato contrato) {
		this.contract = contrato;
		switch(contrato){
			case MiniJob: maxHours = 45;
			minHours = 0;
			break;
			case otros:
			case Fijo:	maxHours = 220;
			minHours = 50;
			break;

		}
	}

	public void cincoMas(){
		if(contract ==Contrato.MiniJob) return;
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
		return new SimpleBooleanProperty(morning);
	}

	public ObservableValue<Boolean> Tarde() {
		return  new SimpleBooleanProperty(evening);
	}

	public ObservableValue<Boolean> Nivel() {
		return  new SimpleBooleanProperty(register);
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

		switch(contract){
			case Fijo: this.maxHours = 220;
			break;
			case MiniJob: if(maxHours == 0) this.maxHours = 45;
				else this.maxHours = Math.min(maxHours, 45);
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

		switch(contract){
			case Fijo: this.minHours = 50;
			break;
			case MiniJob:if(minHours > 45) this.minHours = 45;
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
		if(fecha == null)
		return  name + ": " +getHorasAsignadas();
		else{
		HorasMes horas =	horasMes.get(LocalDate.of(fecha.getYear(), fecha.getMonth(), 1));
		if(horas != null)
		return name + ": " +horas.getHoras();
		else
		return name + ": 0";
		}
	}

	public ObservableValue<Boolean> Lunch() {
		return  new SimpleBooleanProperty(lunch);
	}

	public void printCondeso(){
		System.out.println(name);
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
			switch (contract){
				case MiniJob: maxHours = 45;
				break;
				case otros: maxHours = 220;
				break;
				case Fijo: maxHours = 220;
				break;
			}
		if(minHours == 0){
			switch (contract){
				case otros:
				case Fijo: minHours = 50;
				break;
			}
		}
		if(contract == Contrato.MiniJob && minHours >= 45){
			minHours = 45;
		}
		}
	}

	public boolean isGM(){
		if(type == TipoEmpleado.GM) return true;
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

	public void resetCondeso(){
		this.horasAsignadas = 0;
		this.personal = new Turnos[31];
		if(fecha != null)
		setFinesLibres(fecha);
		else finesLibres = 5;

	}

	public List<HorasMes> getMonthHours() {
		return monthHours;
	}

	public void setMonthHours(List<HorasMes> monthHours) {
		this.monthHours = monthHours;
	}
}
