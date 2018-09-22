package lalo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

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
			elCondeso = fila.poll();
			while(!checkCondeso(elCondeso, disponibilidad, elTurno)){
				noDisponible.add(elCondeso);
				elCondeso = fila.poll();
			}
			if(elCondeso == null) noAsignados.add(elTurno);
			else AsignarTurno(elCondeso, elTurno);

		}
	}
	private boolean checkCondeso(Condeso elCondeso, HashMap<Integer, Integer[][]> disponibilidad, Turnos elTurno){
		if(!checkDisponibilidad(elCondeso, disponibilidad, elTurno)) return false;
		if(!checkDiasSeguidos(elCondeso, elTurno)) return false;
		if(!checkFinesLibres(elCondeso, elTurno)) return false;
		return true;

	}

	private boolean checkDisponibilidad(Condeso elCondeso, HashMap<Integer, Integer[][]> disponibilidad, Turnos elTurno){
		int Id = (int) elCondeso.getId();
		Integer[][] disp = disponibilidad.get(Id);
		int inicio = elTurno.getInicio();
		int fin = elTurno.getFin();
		LocalDate fecha = elTurno.getDate();
		int day = fecha.getDayOfMonth();
		int desde = disp[0][day-1];
		int hasta = disp[1][day-1];
		if(desde <= inicio && hasta >= fin) return true;
		return false;
	}

	private boolean checkDiasSeguidos(Condeso elCondeso, Turnos elTurno){
		return false;
	}

	private boolean checkFinesLibres(Condeso elCondeso, Turnos elTurno){
		LocalDate fecha = elTurno.getDate();
		DayOfWeek dia = fecha.getDayOfWeek();
		if(dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY) return true;
		if(elCondeso.getFinesLibres() == 1) return false;
		return true;
	}

	private void AsignarTurno(Condeso elCondeso, Turnos elTurno){

	}

	public static void main(String[] args) {
		//TODO
	}
}
