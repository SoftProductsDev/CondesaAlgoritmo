package condeso;
import horario.HorarioEntrega;
import horario.HorarioMaster;
import horario.HorarioPersonal;
import java.time.LocalDate;
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
	private HorarioPersonal personal;
	private List<Tiendas> dondePuedeTrabajar;
	private Contrato contrato;

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

	public int getDiasSeguidos() {
		return diasSeguidos;
	}

	public void setDiasSeguidos(int diasSeguidos) {
		this.diasSeguidos = diasSeguidos;
	}

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

	public HorarioPersonal getPersonal() {
		return personal;
	}

	public void setPersonal(HorarioPersonal personal) {
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
}
