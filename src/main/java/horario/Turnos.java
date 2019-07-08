package horario;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;
import tiendas.Tiendas;
import condeso.Condeso;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "turnos")
public class Turnos implements Comparable<Turnos> {
	@javax.persistence.Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "inicio")
	private int start;

	@Column(name = "fin")
	private int finish;

	@JoinColumn
	@ManyToOne
	private transient Condeso condeso;

	private long condesoId;
	private String condesoName;
	private String condesoColor;
	private String condesoAbreviate;
	public long shopId;
	public String shopName;
	public String shopColor;
	public String shopManager;

	@Column
	@Enumerated(EnumType.STRING)
	private ShiftType shiftType;

	@Transient
	private LocalDate date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCondesoName() {
		return condesoName;
	}

	public void setCondesoName(String condesoName) {
		this.condesoName = condesoName;
	}

	public String getCondesoColor() {
		return condesoColor;
	}

	public void setCondesoColor(String condesoColor) {
		this.condesoColor = condesoColor;
	}

	public long getCondesoId() {
		return condesoId;
	}

	public void setCondesoId(long condesoId) {
		this.condesoId = condesoId;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isNoOptions() {
		return noOptions;
	}

	public Dias getElDia() {
		return elDia;
	}

	public void setElDia(Dias elDia) {
		this.elDia = elDia;
	}

	public boolean isEncargado() {
		return encargado;
	}

	public long getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(long idTienda) {
		this.idTienda = idTienda;
	}

	public List<Hora> getMisHoras() {
		return misHoras;
	}

	public void setMisHoras(List<Hora> misHoras) {
		this.misHoras = misHoras;
	}

	public String getCondesoAbreviate() {
		return condesoAbreviate;
	}

	public void setCondesoAbreviate(String condesoAbreviate) {
		this.condesoAbreviate = condesoAbreviate;
	}

	//private boolean elemental;
	//private boolean matutino;
	@Transient
	private transient boolean noOptions = false;
	@Transient
	private transient int minimo = 1;
	@Transient
	private transient Dias elDia;
	@Transient
	private transient boolean encargado;
	@Transient
	private transient long idTienda;
	@Transient
	private transient LocalDate fecha;
	@Transient
	private transient List<Hora> misHoras = new ArrayList<>();

	public void resetMinimo(){
		minimo = 1;
	}

	public Turnos(){};

	public LocalDate getFecha(){return date;}

	public void setFecha(LocalDate fecha){
		this.date = fecha;
	}

	public void setIdTienda(int id)
	{

	}


	public void setHoras() {
		if(encargado) return;
		HashMap<Integer, Hora> horas = elDia.getHoras();
		Hora laHora;
		for (int i = start; i <= finish; i++) {
			laHora = horas.get(i);
			if(laHora == null){
				laHora = new Hora(elDia, elDia.getPromedioMinimo());
				horas.put(i, laHora);
			}
			laHora.addTurno(this);
			misHoras.add(laHora);
		}
	}

	public void change(){
		for(Hora laHora : misHoras){
  			laHora.change(this);
		}
	}

	public int getMinimo(){return minimo;}

	public void setMinimo(int minimo){
	    if(minimo > this.minimo) this.minimo = Math.min(minimo, 3);
	}


	public Turnos(Condeso condeso, int start, int finish, Dias elDia, boolean encargado, ShiftType ShiftType) {
		this.condeso = condeso;
		if(condeso != null)
		{
			this.condesoId = condeso.getId();
			this.condesoColor = condeso.getColor();
			this.condesoName = condeso.getNombre();
			this.condesoAbreviate = condeso.getAbreviacion();
		}
		this.start = start;
		this.finish = finish;
		this.elDia = elDia;
		this.encargado = encargado;
		this.shiftType = ShiftType;
		minimo = 1;
		if(elDia == null) throw new RuntimeException("dia es null");
		elDia.addTurno(this);
		this.shopId = elDia.getTienda().getId();
		this.shopColor = elDia.getTienda().getColor();
		this.shopName = elDia.getTienda().getNombre();
		this.shopManager = elDia.getTienda().getManager();
		this.date = elDia.getDate();
	}

	public void checkDia(){if(elDia == null)throw  new RuntimeException("dia es null");}

	public Dias getDay(){return elDia;}

	public void setDay(Dias elDia){this.elDia = elDia;}

	public boolean noOptionst(){return noOptions;}

	public void setNoOptions(boolean Options){
		noOptions = Options;
	}

	/*public boolean isElemental() {
		return elemental;
	}

	public void setElemental(boolean elemental) {
		this.elemental = elemental;
	}*/

	public int getInicio() {
		return start;
	}

	public void setInicio(int start) {
		this.start = start;
	}

	public int getFin() {
		return finish;
	}

	public void setFin(int fin) {
		this.finish = fin;
	}

	public int getDuracion() {
		return finish - start;
	}

	/*public boolean isMatutino() {
		return matutino;
	}

	public void setMatutino(boolean matutino) {
		this.matutino = matutino;
	}*/

	public boolean isOcupado() {
		if(condeso == null)
		return false;
		else return  true;
	}

	public boolean deEncargado(){return encargado;}

	public void setEncargado(boolean encargado){this.encargado = encargado;}


	public Condeso getCondeso() {
		return condeso;
	}

	public void setCondeso(Condeso condeso) {
		this.condeso = condeso;
		if(condeso != null){
			this.condesoId = condeso.getId();
			this.condesoColor = condeso.getColor();
			this.condesoName = condeso.getNombre();
			this.condesoAbreviate = condeso.getAbreviacion();
		}
	}

	public void setShiftType(ShiftType tipo){this.shiftType = tipo;}

	public ShiftType getShiftType(){return shiftType;}


	public LocalDate getDate() { return elDia.getDate();
	}

	public Tiendas getTienda(){return elDia.getTienda();}


	public Turnos duplicate(Dias elDia){
	return new Turnos(condeso, start, finish, elDia, encargado, shiftType);
	}

	@Override
	public int compareTo(Turnos o2) {
		if(this.getInicio() != o2.getInicio()) return  Integer.compare(this.getInicio(), o2.getInicio());
		else{
			return this.getShiftType().compareTo(o2.getShiftType());
		}
	}

	//returns true if overlaps
	public Boolean overlapGUI(Turnos turnos){
		if(this.getShiftType() == turnos.getShiftType() && this != turnos){
			if((this.getInicio() >= turnos.getInicio() && turnos.getInicio() <= this.getFin()||
					(this.getInicio() >= turnos.getFin() && turnos.getFin() <= this.getFin()))){
				return true;
			}
			else return false;
		}
		else return false;
	}

	public boolean isG(){
		if(getShiftType() == ShiftType.G) return true;
		return false;
	}

	public boolean getMin(int level) {
		minimo = 1;
		for(Hora laHora: misHoras){
			laHora.change2(this);
		}
		if(minimo <= level) return true;
		return false;
	}

	public boolean checkUnos() {
		for(Hora laHora : misHoras){
			if(!laHora.checkUnos()) return false;
		}
		return true;
	}
}



