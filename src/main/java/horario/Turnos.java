package horario;

import tiendas.Tiendas;
import condeso.Condeso;
import java.time.LocalDate;
import java.util.HashMap;

public class Turnos {
	private Condeso condeso;
	private long Id;
	private boolean elemental;
	private boolean matutino;
	private boolean noOptions = false;
	private int inicio;
	private int fin;
	private Tiendas tienda;
	private TipoTurno tipoTurno;
	private int minimo;
	private Dias elDia;
	private boolean encargado;

	public void setHoras() {
		HashMap<Integer, Hora> horas = elDia.getHoras();
		for (int i = inicio; i <= fin; i++) {
			horas.get(i).addTurno(this);
		}
	}

	public int getMinimo(){return minimo;}

	public void setMi11nimo(int minimo){
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
		result.setElemental(elemental);
		result.setInicio(inicio);
		result.setMatutino(matutino);
		return  result;
	}

	public Turnos(Condeso condeso, long id, boolean elemental, boolean matutino,
			int inicio, int fin, Dias elDia, boolean encargado) {
		this.condeso = condeso;
		Id = id;
		this.elemental = elemental;
		this.matutino = matutino;
		this.inicio = inicio;
		this.fin = fin;
		this.elDia = elDia;
		this.encargado = encargado;
	}

	public Dias getDay(){return elDia;}

	public void setDay(Dias elDia){this.elDia = elDia;}

	public boolean noOptionst(){return noOptions;}

	public void setNoOptions(boolean Options){
		noOptions = Options;
	}

	public boolean isElemental() {
		return elemental;
	}

	public void setElemental(boolean elemental) {
		this.elemental = elemental;
	}

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

	public boolean isMatutino() {
		return matutino;
	}

	public void setMatutino(boolean matutino) {
		this.matutino = matutino;
	}

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
	return new Turnos(condeso, Id, elemental, matutino, inicio, fin, elDia, encargado);
	}



}



