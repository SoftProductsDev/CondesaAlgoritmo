package condeso;
import horario.HorarioEntrega;
import horario.HorarioMaster;
import horario.HorarioPersonal;

import java.time.DayOfWeek;
import java.time.LocalDate;

import horario.Turnos;
import tiendas.Tiendas;

import java.util.List;

public class Condeso {
	private long Id;
    private TipoEmpleado tipo;
    private String nombre;
	private boolean fijos;
	private int level;
	private int priorityValue;
	private boolean manana;
	private boolean tarde;
	private boolean caja;
	private LocalDate antiguedad;
	private int diasSeguidos;
	private int finesLibres;
	private HorarioEntrega entrega;
	private HorarioMaster master;
	private Turnos[] personal = new Turnos[31];
	private List<Tiendas> dondePuedeTrabajar;
	private Contrato contrato;
	private int horas;
	private int horasAsignadas = 0;
	private int maxHours;
	//Hex Color Format
	private String color;

	public Condeso(TipoEmpleado tipo, String nombre,
			boolean fijos, int level, boolean manana,
			boolean tarde, boolean caja, LocalDate antiguedad,
			List<Tiendas> dondePuedeTrabajar, Contrato contrato) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.fijos = fijos;
		this.level = level;
		this.manana = manana;
		this.tarde = tarde;
		this.caja = caja;
		this.antiguedad = antiguedad;
		this.dondePuedeTrabajar = dondePuedeTrabajar;
		this.contrato = contrato;
	}

	public Condeso(String nombre, String color) {
		this.nombre = nombre;
		this.color = color;
	}

	@Override
  public String toString() {
    return "Condeso{" +
        "tipo=" + tipo +
        ", nombre='" + nombre + '\'' +
        ", fijos=" + fijos +
        ", level=" + level +
        ", manana=" + manana +
				", tarde=" + tarde +
        ", caja=" + caja +
        ", antiguedad=" + antiguedad +
        ", dondePuedeTrabajar=" + dondePuedeTrabajar +
        ", contrato=" + contrato +
        '}';
  }

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
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

	public void setAntiguedad(LocalDate antiguedad) {
		this.antiguedad = antiguedad;
	}

	public int getDiasSeguidos(LocalDate fecha) {
		int dia = fecha.getDayOfMonth();
		int counter = 0;
		while(dia - 2- counter > 0 && personal[dia-2-counter] != null){
			counter++;
		}
		return counter;
	}

	public int getDiasSeguidos(){return diasSeguidos;}

	public void setDiasSeguidos(int diasSeguidos){this.diasSeguidos = diasSeguidos;}

	public int getFinesLibres() {
		return finesLibres;
	}

	public void setFinesLibres(int finesLibres) {
		this.finesLibres = finesLibres;
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

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public int getHoras(){ return horas;}

	public void setHoras(int horas){ this.horas = horas;}

	public void setHorasAsignadas(int horasAsignadas){this.horasAsignadas = horasAsignadas;}

	public int getHorasAsignadas(){return horasAsignadas;}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void asignarTurno(Turnos elTurno){
		int day = elTurno.getDate().getDayOfMonth();
		personal[day-1] = elTurno;
		horasAsignadas += elTurno.getDuracion();
		DayOfWeek dia = elTurno.getDate().getDayOfWeek();
		if(dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY){
			switch (dia){
				case SUNDAY: if(personal[day-2] == null) finesLibres--;
				break;
				case SATURDAY: if(personal[day] == null) finesLibres--;
				break;
				default: break;
			}
		}

	}

	public void setMaxHours(int maxHours){this.maxHours = maxHours;}

	public int getMaxHours(){return maxHours;}

	public boolean checkMax(){ if(maxHours <= horasAsignadas) return false;
	return true;}
}
