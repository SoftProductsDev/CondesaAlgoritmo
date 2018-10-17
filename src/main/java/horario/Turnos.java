package horario;

import tiendas.Tiendas;
import condeso.Condeso;
import java.time.LocalDate;

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

	private LocalDate date;

	private boolean encargado;

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
			int inicio, int fin, LocalDate date, Tiendas tienda, boolean encargado) {
		this.condeso = condeso;
		Id = id;
		this.elemental = elemental;
		this.matutino = matutino;
		this.inicio = inicio;
		this.fin = fin;
		this.date = date;
		this.tienda = tienda;
		this.encargado = encargado;
	}

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

	public void setDate(LocalDate date){ this.date = date;}

	public LocalDate getDate() { return date;
	}

	public Tiendas getTienda(){return tienda;}

	public void setTienda(Tiendas tienda){ this.tienda = tienda;}

	public Turnos duplicate(){
	return new Turnos(condeso, Id, elemental, matutino, inicio, fin, date, tienda, encargado);
	}

}
