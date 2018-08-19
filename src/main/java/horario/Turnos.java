package horario;

import java.util.Set;
import condeso.Contrato;

public class Turnos {
	private long Id;
	private boolean elemental;
	private boolean matutino;
	private boolean ocupado = false;
	private boolean noOptions = false;
	private int inicio;
	private int fin;
	private int duracion;

	public Turnos(boolean elemental, int inicio, int fin, int duracion, boolean matutino) {
		this.elemental = elemental;
		this.inicio = inicio;
		this.fin = fin;
		this.duracion = duracion;
		this.matutino = matutino;
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
		result.setDuracion(duracion);
		result.setMatutino(matutino);
		result.setOcupado(ocupado);
		return  result;
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
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public boolean isMatutino() {
		return matutino;
	}

	public void setMatutino(boolean matutino) {
		this.matutino = matutino;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
	
}
