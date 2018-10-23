package horario;

import tiendas.Tiendas;
import condeso.Condeso;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Turnos {
	private Condeso condeso;
	private long Id;
	//private boolean elemental;
	//private boolean matutino;
	private boolean noOptions = false;
	private int inicio;
	private int fin;
	private Tiendas tienda;
	private TipoTurno tipoTurno;
	private int minimo;
	private Dias elDia;
	private boolean encargado;
	private int idTienda;
	private LocalDate fecha;
	private List<Hora> misHoras = new ArrayList<>();

	public LocalDate getFecha(){return fecha;}

	public void setFecha(LocalDate fecha){
		this.fecha = fecha;
	}

	public void setIdTienda(int id){
		idTienda = id;
	}

	public int getIdTienda(){return idTienda;}

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
	    if(minimo > this.minimo) this.minimo = minimo;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public DbModel.Turnos convertToDbModel()
	{
		DbModel.Turnos result = new DbModel.Turnos();
		//result.setElemental(elemental);
		result.setInicio(inicio);
		//result.setMatutino(matutino);
		return  result;
	}

	public Turnos(Condeso condeso, long id,
			int inicio, int fin, Dias elDia, boolean encargado) {
		this.condeso = condeso;
		Id = id;
		//this.matutino = matutino;
		this.inicio = inicio;
		this.fin = fin;
		this.elDia = elDia;
		this.encargado = encargado;
	}

	public Turnos(int idTienda, int inicio, int fin, LocalDate fecha){ // solo para el caso de los GM
		this.idTienda = idTienda;
		this.inicio = inicio;
		this.fin = fin;
		this.fecha = fecha;
		encargado = true;
	}

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
	return new Turnos(condeso, Id, inicio, fin, elDia, encargado);
	}



}



