package lalo;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;


import condeso.Condeso;
import condeso.CompareCondesos;
import horario.HorarioEntrega;
import horario.Turnos;
import tiendas.Tiendas;
import horario.HorarioMaster;
import horario.HorarioPersonal;
import horario.HorarioEntrega;

public class lalo {
	public Set<HorarioEntrega> entregas;
	public Set<Condeso> condesos;
	public Set<Tiendas> tiendas;
	private PriorityQueue<Turnos> turnos;

	private PriorityQueue<Condeso> fila;
	private HashMap<Integer, Integer[][]> disponibilidad;


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
		Set<Condeso> noDisponible = new HashSet<>();
		Set<Turnos> noAsignados = new HashSet<>();
		Set<Condeso> yaOcupados = new HashSet<>();

		Turnos elTurno = turnos.poll();
		Turnos last;
		while(elTurno != null){
			last = elTurno;
			while(last.getDate().getDayOfMonth() == elTurno.getDate().getDayOfMonth()) {
				Condeso elCondeso = fila.poll();
				while (!checkCondeso(elCondeso, disponibilidad, elTurno)) {
					noDisponible.add(elCondeso);
					elCondeso = fila.poll();
				}
				if (elCondeso == null) noAsignados.add(elTurno);
				else{
					AsignarTurno(elCondeso, elTurno);
					yaOcupados.add(elCondeso);
				}
				last = elTurno;
				elTurno = turnos.poll();
				fila.addAll(noDisponible);
				noDisponible.clear();
			}
			fila.addAll(yaOcupados);
			yaOcupados.clear();
		}

		reacomodar(noAsignados, condesos, disponibilidad);

	}

	private boolean checkCondeso(Condeso elCondeso, HashMap<Integer, Integer[][]> disponibilidad, Turnos elTurno){
		if(elCondeso == null) return true;
		if(!checkDisponibilidad(elCondeso, disponibilidad, elTurno)) return false;
		if(!checkDiasSeguidos(elCondeso, elTurno)) return false;
		if(!checkFinesLibres(elCondeso, elTurno)) return false;
		if(!checkMax(elCondeso, elTurno)) return false;
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
		//TODO
		return false;
	}

	private boolean checkFinesLibres(Condeso elCondeso, Turnos elTurno){
		LocalDate fecha = elTurno.getDate();
		DayOfWeek dia = fecha.getDayOfWeek();
		if(dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY) return true;
		if(elCondeso.getFinesLibres() == 1) return false;
		return true;
	}

	private boolean checkMax(Condeso elCondeso, Turnos elTurnos){
		return false;
	}

	private void AsignarTurno(Condeso elCondeso, Turnos elTurno){
		//TODO
	}

	private void reacomodar(Set<Turnos> noAsignados, Set<Condeso> condesos, HashMap<Integer, Integer[][]> disponibilidad){
		//TODO
	}


	public static void main(String[] args) {
		//TODO
	}
}
