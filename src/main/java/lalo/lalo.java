package lalo;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;


import condeso.Condeso;
import condeso.CompareCondesos;
import condeso.TipoEmpleado;
import horario.HorarioEntrega;
import horario.Turnos;
import tiendas.Tiendas;
import horario.HorarioMaster;
import horario.Dias;
import horario.HorarioPersonal;
import horario.HorarioEntrega;

public class lalo {
	public Set<HorarioEntrega> entregas;
	public Set<Condeso> condesos;
	public Set<Tiendas> tiendas;
	private Queue<Turnos> turnos;
	private HashMap<Tiendas, HorarioMaster> horariosMaster;

	public lalo(HashMap<Tiendas, HorarioMaster> horariosMaster, Set<Condeso> condesos, Set<Tiendas> tiendas){
		this.horariosMaster = horariosMaster;
		this.condesos = condesos;
		this.tiendas = tiendas;
		turnos = generateQueueTurnos(horariosMaster);

	}

	private Queue<Turnos> generateQueueTurnos(HashMap<Tiendas, HorarioMaster> horariosMaster /*, Queue<Turnos> encargados*/){
		Queue<Turnos> elementales =;

		Queue<Turnos> noElementales;
		HorarioMaster elMaster;
		int year;
		HashMap<LocalDate, Dias> mes;
		Month month;
		Set<Turnos> losTurnos;
		for(Tiendas laTienda: tiendas){
			elMaster = horariosMaster.get(laTienda);
			year = elMaster.getYear();
			month = elMaster.getMonth();
			HashMap<LocalDate, Dias> losDias;
			Dias elDia;
			mes = elMaster.getMes();
			int length = LocalDate.of(year, month, 1).lengthOfMonth();
			for(int i = 0; i < length; i++){
				elDia = mes.get(LocalDate.of(year, month, i+1));
				losTurnos = elDia.getTurnos();
				for(Turnos elTurno : losTurnos){
					if(elTurno.isElemental()) elementales.add(elTurno);
					else noElementales.add(elTurno);
				}
			}
		}
		elementales.addAll(noElementales);
		// encargados.addAll(elementales);
		return elementales;

	}

	private PriorityQueue<Condeso> fila;
	private HashMap<Integer, Integer[][]> disponibilidad;


	/*public void  GMTodos() {
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
	}*/

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
		if(!checkEncargado(elCondeso, elTurno)) return false;
		if(!checkDiasSeguidos(elCondeso, elTurno)) return false;
		if(!checkFinesLibres(elCondeso, elTurno)) return false;
		if(!elCondeso.checkMax(elTurno)) return false;
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
		if(elCondeso.getLevel() > 1 ) return true;
		Tiendas laTienda = elTurno.getTienda();
		HashMap<LocalDate, Dias> elMaster = horariosMaster.get(laTienda).getMes();
		Dias elDia = elMaster.get(elTurno.getDate());

		//TODO
		return true;
	}  //falta agregarlo en muchas partes

	private boolean checkEncargado(Condeso elCondeso, Turnos elTurno){
		if(!elTurno.deEncargado()) return true;
		else if(elCondeso.getTipo() == TipoEmpleado.Encargado || elCondeso.getTipo() == TipoEmpleado.GM) return true;
		else return false;
	}


	private void reacomodar(Set<Turnos> noAsignados, Set<Condeso> condesos, HashMap<Integer, Integer[][]> disponibilidad){
		PriorityQueue<Condeso> fila = new PriorityQueue<>(new CompareCondesos());
		List<Condeso> regaladores;

		for(Turnos elTurno : noAsignados){
			boolean Found = false;
			Set<Condeso> candidates = findCandidates(elTurno, condesos, disponibilidad);
			HashMap<Reasons, List<Condeso>> posibles = checkReason(elTurno, candidates); // candidatos divididos por razones

			fila.addAll(condesos);
			int dia = elTurno.getDate().getDayOfMonth();
			regaladores = posibles.get(Reasons.turnoEseDia);
			condesos.removeAll(regaladores);
			Condeso candidate;
			Set<Condeso> checados = new HashSet<>();
			Set<Condeso> useless = new HashSet<>();
			boolean first = true;
			for(Condeso elCondeso : regaladores) {
				while (!Found && !fila.isEmpty()) {     // intentar reacomodar los que tienen turno ese mismo día
				candidate = fila.poll();
				Found = changeTurn(elCondeso, candidate, elTurno);
				if(first){
				if(useful(disponibilidad, candidate, dia)) checados.add(candidate);
				else useless.add(candidate);} else {
				checados.add(candidate);
				}
				}
				if(first) first = false;
				if(Found){
					break;
				}else{
					fila.addAll(checados);
				}
			}
			condesos.addAll(regaladores);

			if(!Found){ //intentar reacomodar a los que tienen muchos turnos
				fila = new PriorityQueue<>(new CompareCondesos());
				regaladores = posibles.get(Reasons.maximoDiasSeguidos);
				condesos.removeAll(regaladores);
				checados = new HashSet<>();
				useless = new HashSet<>();
				fila.addAll(condesos);
				if(elTurno.getDate().getDayOfWeek()  == DayOfWeek.SATURDAY || elTurno.getDate().getDayOfWeek() == DayOfWeek.SUNDAY){
					for(Condeso elCondeso : regaladores){
						if(!checkFinesLibres(elCondeso, elTurno)) { useless.add(elCondeso); regaladores.remove(elCondeso);}
					}
				}
				for(Condeso elCondeso : regaladores){
					while(!Found && !fila.isEmpty()){
						candidate = fila.poll();
						Found = porDiasSeguidos(elCondeso, candidate, elTurno);
						checados.add(candidate);
					}
					if(Found){
					break;
					}else{
					fila.addAll(checados);
					}

				}
				condesos.addAll(regaladores);
				condesos.addAll(useless);

				if(!Found){

				}

			}




		}

	}

	private boolean porDiasSeguidos(Condeso regalador, Condeso candidate, Turnos elTurno){
		Turnos oferta;
		int dia = elTurno.getDate().getDayOfMonth()-1;
		int max = regalador.getDiasSeguidos();
		for(int i = 0; i < max; i++){
			oferta = regalador.getPersonal()[dia-1-i];
			if(checkCondeso(candidate, disponibilidad, oferta)){
				candidate.asignarTurno(regalador.borrarTurno(oferta));
				regalador.asignarTurno(elTurno);
			return true;
			}
		}
		return false;
	}

	private boolean useful(HashMap<Integer, Integer[][]> disponibilidad, Condeso elCondeso, int dia){
		Integer[][] disp = disponibilidad.get(elCondeso.getId());
		if(disp[1][dia-1] != 0) return true;
		return false;
	}

	private enum Reasons{
		finesOcupados, maximoAlcanzado, turnoEseDia, maximoDiasSeguidos
	}

	private boolean changeTurn(Condeso elCondeso, Condeso candidate, Turnos elTurno){
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
			else if(!checkDiasSeguidos(elCondeso, elTurno)) diasSeguidos.add(elCondeso);
			else if(!checkFinesLibres(elCondeso, elTurno)) finesDeSemana.add(elCondeso);
			else if(!elCondeso.checkMax(elTurno)) maximoDelMes.add(elCondeso);

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

}
