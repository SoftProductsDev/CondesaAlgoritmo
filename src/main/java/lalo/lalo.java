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
					elCondeso.asignarTurno(elTurno);
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
		if(!checkTienda(elCondeso, elTurno)) return false;
		if(!checkTurnosEseDia(elCondeso, elTurno)) return false;
		if(!checkDiasSeguidos(elCondeso, elTurno)) return false;
		if(!checkFinesLibres(elCondeso, elTurno)) return false;
		if(!elCondeso.checkMax()) return false;
		if(!checkLevel(elCondeso, elTurno)) return false;
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
		if(elCondeso.getDiasSeguidos(elTurno.getDate()) >= elCondeso.getDiasSeguidos()) return false;
		return true;
	}

	private boolean checkFinesLibres(Condeso elCondeso, Turnos elTurno){
		LocalDate fecha = elTurno.getDate();
		DayOfWeek dia = fecha.getDayOfWeek();
		if(dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY) return true;
		if(elCondeso.getFinesLibres() == 1) return false;
		return true;
	}

	private boolean checkTurnosEseDia(Condeso elCondeso, Turnos elTurno){
		int dia  = elTurno.getDate().getDayOfMonth()-1;
		Turnos[] turnos = elCondeso.getPersonal();
		if(turnos[dia] == null) return true;
		return false;
	}

	private boolean checkTienda(Condeso elCondeso, Turnos elTurno){
		List<Tiendas> tiendas= elCondeso.getDondePuedeTrabajar();
		Tiendas laTienda = elTurno.getTienda();
		if(tiendas.contains(laTienda)) return true;
		return false;
	}

	private boolean checkLevel(Condeso elCondeso, Turnos elTurno){
		//TODO
		return true;
	}


	private void reacomodar(Set<Turnos> noAsignados, Set<Condeso> condesos, HashMap<Integer, Integer[][]> disponibilidad){
		PriorityQueue<Condeso> fila = new PriorityQueue<>(new CompareCondesos());
		boolean Found = false;
		List<Condeso> aChecar;
		for(Turnos elTurno : noAsignados){
			Set<Condeso> candidates = findCandidates(elTurno, condesos, disponibilidad);
			HashMap<Reasons, List<Condeso>> posibles = checkReason(elTurno, candidates); // candidatos divididos por razones
			condesos.removeAll(candidates);
			fila.addAll(condesos);
			int dia = elTurno.getDate().getDayOfMonth();

			aChecar = posibles.get(Reasons.turnoEseDia);
			Condeso candidate;
			Set<Condeso> checados = new HashSet<>();
			for(Condeso elCondeso : aChecar) {
				while (!Found && !fila.isEmpty()) {     // intentar reacomodar los que tienen turno ese mismo día
				candidate = fila.poll();
				Found = offerTurn(elCondeso, candidate, elTurno);
				//TODO
				}
				if(Found) break;
			}




		}

	}

	private enum Reasons{
		finesOcupados, maximoAlcanzado, turnoEseDia, maximoDiasSeguidos
	}

	private boolean offerTurn(Condeso elCondeso, Condeso candidate, Turnos elTurno){
		int dia = elTurno.getDate().getDayOfMonth();
		Turnos turno = elCondeso.getPersonal()[dia-1];
		if(checkCondeso(candidate, disponibilidad, turno)) {
			elCondeso.cambiarTurno(elTurno);
			candidate.asignarTurno(turno);
			return true;
		}
		return false;
	}

	private HashMap<Reasons, List<Condeso>> checkReason(Turnos elTurno, Set<Condeso> candidates){
		List<Condeso> yaTieneTurno = new ArrayList<>();
		List<Condeso> diasSeguidos = new ArrayList<>();
		List<Condeso> maximoDelMes = new ArrayList<>();
		List<Condeso> finesDeSemana = new ArrayList<>();

		for(Condeso elCondeso : candidates){
			if(elCondeso.getPersonal()[elTurno.getDate().getDayOfMonth()-1] != null) yaTieneTurno.add(elCondeso);
			if(!checkDiasSeguidos(elCondeso, elTurno)) diasSeguidos.add(elCondeso);
			if(!elCondeso.checkMax()) maximoDelMes.add(elCondeso);
			if(!checkFinesLibres(elCondeso, elTurno)) finesDeSemana.add(elCondeso);
		}

		HashMap<Reasons, List<Condeso>> Razones = new HashMap<>();
		Razones.put(Reasons.turnoEseDia, yaTieneTurno);
		Razones.put(Reasons.maximoDiasSeguidos, diasSeguidos);
		Razones.put(Reasons.maximoAlcanzado, maximoDelMes);
		Razones.put(Reasons.finesOcupados, finesDeSemana);
		return Razones;
	}

	private Set<Condeso> findCandidates(Turnos elTurno, Set<Condeso> condesos, HashMap<Integer, Integer[][]> disponibilidad){
		Set<Condeso> candidates = new HashSet<>();

		for(Condeso elCondeso : condesos ){
			if(checkDisponibilidad(elCondeso, disponibilidad, elTurno) && checkTienda(elCondeso, elTurno)) candidates.add(elCondeso);
		}
		return candidates;
	}

	public static void main(String[] args) {
		//TODO
	}
}
