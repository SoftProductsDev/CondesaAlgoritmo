package horario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
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

	/*@Column(name = "elemental")
	private boolean elemental;

	@Column(name = "matutino")
	private boolean matutino;

	@Column(name = "ocupado")
	private boolean ocupado = false;*/

	@Column(name = "inicio")
	private int inicio;

	@Column(name = "fin")
	private int fin;

	@JoinColumn
	@OneToOne
	private Condeso condeso;

	@Column
	private TipoTurno tipoTurno;


	//private boolean elemental;
	//private boolean matutino;
	@Transient
	private boolean noOptions = false;
	@Transient
	private int minimo;
	@Transient
	private Dias elDia;
	@Transient
	private boolean encargado;
	@Transient
	private long idTienda;
	@Transient
	private LocalDate fecha;
	@Transient
	private List<Hora> misHoras = new ArrayList<>();

	public void resetMinimo(){
		minimo = 1;
	}

	public Turnos(){};

	public LocalDate getFecha(){return fecha;}

	public void setFecha(LocalDate fecha){
		this.fecha = fecha;
	}

	public void setIdTienda(int id){
		idTienda = id;
	}

	public long getIdTienda(){return idTienda;}

	public void setHoras() {
		if(encargado) return;
		HashMap<Integer, Hora> horas = elDia.getHoras();
		Hora laHora;
		for (int i = inicio; i <= fin; i++) {
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


	public Turnos(Condeso condeso, int inicio, int fin, Dias elDia, boolean encargado) {
		this.condeso = condeso;
		//this.matutino = matutino;
		this.inicio = inicio;
		this.fin = fin;
		this.elDia = elDia;
		this.encargado = encargado;
	}

	/*public Turnos(long idTienda, int inicio, int fin, LocalDate fecha){ // solo para el caso de los GMs
		this.idTienda = idTienda;
		this.inicio = inicio;
		this.fin = fin;
		this.fecha = fecha;
		encargado = true;
	}*/

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
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public int getDuracion() {
		return fin - inicio;
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
	}

	public void setTipoTurno(TipoTurno tipo){this.tipoTurno = tipo;}

	public TipoTurno getTipoTurno(){return tipoTurno;}


	public LocalDate getDate() { return elDia.getDate();
	}

	public Tiendas getTienda(){return elDia.getTienda();}


	public Turnos duplicate(){
	return new Turnos(condeso, inicio, fin, elDia, encargado);
	}

	@Override
	public int compareTo(Turnos o2) {
		if(this.getInicio() != o2.getInicio()) return  Integer.compare(this.getInicio(), o2.getInicio());
		else{
			return this.getTipoTurno().compareTo(o2.getTipoTurno());
		}
	}
}



