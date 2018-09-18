package lalo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import condeso.Condeso;
import condeso.CompareCondesos;
import horario.*;
import tiendas.Tiendas;
import horario.HorarioEntrega;

public class lalo {
	private PriorityQueue<Condeso> fila;
	private PriorityQueue<Dias> dias;
	private HashMap<Integer, Integer[][]> disponibilidad;
	private Queue<Turnos> turnos;
	public Set<Condeso> condesos;
	public Set<Tiendas> tiendas;
	public Set<HorarioEntrega> entregas;

	/*private void ListaDeTurnos(){
		for(Tiendas t : tiendas){
			
		}
	}*/

	private ArrayList<Plantillas> getPlantillas(Set<Tiendas> tiendas){
		ArrayList<Plantillas> plantillas = new ArrayList<Plantillas>();
		for(Tiendas tienda : tiendas){
			plantillas.add(tienda.getPlantilla());
		}
		return plantillas;
	}

	public void  GMTodos() {
		//TODO
	}
	public void encargadosGM() {
		//TODO
	}
	public void medioFijos() {
		//TODO
	}
	public void dondeHayJusto() {
		//TODO
	}
	public void pocoONadaDeJuego() {
		//TODO
	}
	public void laloFuncionando() {
		Set<Condeso> noDisponible = new HashSet<Condeso>();
		Set<Turnos> noAsignados = new HashSet<Turnos>();
		Condeso elCondeso;
		fila = new PriorityQueue<>(new CompareCondesos());
		fila.addAll(condesos);

		Turnos elTurno = turnos.remove();
		while(elTurno != null){
			elCondeso = fila.remove();
			while(!checkCondeso(elCondeso, disponibilidad, elTurno)){
				noDisponible.add(elCondeso);
				elCondeso = fila.remove();
			}
			if(elCondeso == null) noAsignados.add(elTurno);
			else AsignarTurno(elCondeso, elTurno);

		}
	}
	private boolean checkCondeso(Condeso elCondeso, HashMap<Integer, Integer[][]> disponibilidad, Turnos elTurno){
		int Id = (int) elCondeso.getId();
		Integer[][] disp = disponibilidad.get(Id);
		int inicio = elTurno.getInicio();
		int fin = elTurno.getFin();
		elTurno.

	}

	private void AsignarTurno(Condeso elCondeso, Turnos elTurno){

	}

	public static void main(String[] args) {
		//TODO
	}
}
