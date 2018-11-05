package lalo;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;


import DbController.HibernateCrud;
import condeso.Condeso;
import condeso.CompareCondesos;
import condeso.Contrato;
import condeso.TipoEmpleado;
import horario.*;
import javafx.scene.layout.Priority;
import tiendas.Tiendas;
import horario.HorarioEntrega;

public class lalo {
	public Set<HorarioEntrega> entregas;
	private Set<Condeso> condesos;
	public Set<Tiendas> tiendas;
	private PriorityQueue<Turnos> turnos;
	//private HashMap<Tiendas, HorarioMaster> horariosMaster; // quitar pues estorba
	private HashMap<Integer, Integer[][]> disponibilidad;
	private List<Turnos> deEncargado;
	private LocalDate fecha;
	private int countFase2 = 0;
	private long start;
	private int countFijos = 0;


	public lalo(Set<Condeso> GMs, List<Turnos> deEncargado, Set<Condeso> condesos, Set<Tiendas> tiendas, HashMap<Integer, Integer[][]> disponibilidad,
	LocalDate fecha){
		start = System.currentTimeMillis();
		this.fecha = fecha;
		this.deEncargado = deEncargado;
		this.disponibilidad = disponibilidad;
		this.condesos = condesos;
		this.tiendas = tiendas;
		for(Condeso elCondeso : condesos){
			List<Tiendas> lasTiendas = elCondeso.getDondePuedeTrabajar();
			if(lasTiendas.size() >= tiendas.size()){
				this.tiendas.clear();
				this.tiendas.addAll(lasTiendas);
				break;
			}
		}

		//horariosMaster = new HashMap<>();
		for(Tiendas laTienda : tiendas) {
			laTienda.getPlantilla().generateMaster(fecha, laTienda);
		}

		addOtrosTurnos(GMs, deEncargado);

	}

	private void addOtrosTurnos(Set<Condeso> GMs, List<Turnos> deEncargado){ //cre que este método es totalmente innecesario
		HashMap<Long, Tiendas> lasTiendas = new HashMap<>();
		for(Tiendas laTienda : tiendas){
			lasTiendas.put(laTienda.getId(), laTienda);
		}
		Dias elDia;
		for(Condeso elGM : GMs){
			Turnos[] losTurno =  elGM.getPersonal();
			for(Turnos elTurno : losTurno){
			if(elTurno != null) {
			    Tiendas tienda = lasTiendas.get(elTurno.getDay().getTienda().getId());
			    Map<LocalDate, Dias> dias = tienda.getMaster().getMes();
				elDia = dias.get(elTurno.getDate()) ;
				elDia.addTurno(elTurno);
				elTurno.setDay(elDia);
			}
			}
		}
		for(Turnos elTurno : deEncargado){
            Tiendas tienda = lasTiendas.get(elTurno.getDay().getTienda().getId());
            Map<LocalDate, Dias> dias = tienda.getMaster().getMes();
            elDia = dias.get(elTurno.getDate()) ;
            elDia.addTurno(elTurno);
            elTurno.setDay(elDia);
		}
	}

	private PriorityQueue<Turnos> generateQueueTurnos(){

		PriorityQueue<Turnos> turnosPriorityQueue = new PriorityQueue<>(new CompareTurnos());

		HorarioMaster elMaster;
		int year;
		Map<LocalDate, Dias> mes;
		Month month;
		Set<Turnos> losTurnos;
		int count = 0;
		for(Tiendas laTienda: tiendas){
			elMaster = laTienda.getMaster();
			year = fecha.getYear();
			month = fecha.getMonth();
			HashMap<LocalDate, Dias> losDias;
			Dias elDia;
			mes = elMaster.getMes();
			int length = fecha.lengthOfMonth();
			for(int i = 0; i < length; i++){
				elDia = mes.get(LocalDate.of(year, month, i+1));
				losTurnos = elDia.getTurnos();
				for(Turnos elTurno : losTurnos){
					if(!elTurno.isOcupado())
					turnosPriorityQueue.add(elTurno);
				}
			}
		}
		//turnosPriorityQueue.addAll(encargados);
		return turnosPriorityQueue;

	}



	public void start(){
		laloFuncionando();

	}

	public void laloFuncionando() {
		Set<Condeso> noDisponible = new HashSet<>();

		//Set<Condeso> yaOcupados = new HashSet<>();
		asignarFijos();
		turnos = generateQueueTurnos();
		Set<Turnos> noAsignados = new HashSet<>();
		PriorityQueue<Condeso> fila = new PriorityQueue<>(new CompareCondesos());
		fila.addAll(condesos);
		System.out.println(disponibilidad.size());
		Turnos elTurno = turnos.poll();
		Turnos last;
		int count = 0;
		int count2 = 0;
		while(elTurno != null){
				Condeso elCondeso = fila.poll();
				System.out.println(fila.size());
				while (!checkCondeso(elCondeso, disponibilidad, elTurno)) {//agregar ese pedo
					noDisponible.add(elCondeso);
					elCondeso = fila.poll();
				}
				if (elCondeso == null){ noAsignados.add(elTurno);
				count2++;
				}
				else{
					System.out.print(count++ + ", ");
					elCondeso.asignarTurno(elTurno);
					fila.add(elCondeso);
				}

				elTurno = turnos.poll();
				fila.addAll(noDisponible);
				noDisponible.clear();


		}

		reacomodar(noAsignados, condesos, disponibilidad);
		for(Tiendas tiendaFinal:tiendas){
			HibernateCrud.UpdateTienda(tiendaFinal);
		}


		System.out.println();
		System.out.println("Turnos totales: " + (count+count2+countFijos));
		System.out.println("Turnos totales asignados: " + (count+countFase2));
		System.out.println("Asignados Fase1: " + count);
		System.out.println("Asignados Fase2: "+ countFase2);
		System.out.println("No asignados: " + (count2-countFase2));
		long elapsedTime =  System.currentTimeMillis() - start;
		System.out.println("Tiempo en segundos: " + (float)elapsedTime/1000F);
		System.out.println("Porcentaje de asignados 1 ronda: " +  (float)count/(count+count2+countFijos)*100 + "%");
		System.out.println("Porcentaje de asignados 2 ronda: " +  (float)countFase2/(count+count2+countFijos)*100 + "%");
		System.out.println("Porcentaje de asignados total: " +  (float)(count+countFase2+countFijos)/(count+count2+countFijos)*100 + "%\n");
		for(Condeso condeso:condesos){
			System.out.println(condeso.getNombre() + ": " + (float)condeso.getHorasAsignadas()/condeso.getMaxHours()*100);
		}

	}

	private void asignarFijos(){
		Set<Condeso> losFijos = getFijos();
		Integer[][] disponibilidad;
		Map<LocalDate, Dias> master;
		Tiendas laTienda;
		for(Condeso elFijo : losFijos){
			if(elFijo.getDondePuedeTrabajar().size() >= 1)
				master = elFijo.getDondePuedeTrabajar().get(0).getMaster().getMes();
			else throw new RuntimeException("No tiene tiendas donde trabajar");
			disponibilidad = this.disponibilidad.get((int)elFijo.getId());
			for(int i = 0; i < disponibilidad[0].length; i++){
				if(i+1 > fecha.lengthOfMonth()) break;
				Turnos elTurno = searchTurno( master.get(LocalDate.of(fecha.getYear(), fecha.getMonth(), i+1)), disponibilidad, elFijo);
				elFijo.asignarTurno(elTurno);
				if(elTurno != null) countFijos++;
			}
		}

	}

	private Turnos searchTurno(Dias elDia, Integer[][] disponibilidad, Condeso elCondeso){
		Set<Turnos> losTurnos = elDia.getTurnos();
		int inicio = disponibilidad[0][elDia.getDate().getDayOfMonth()-1];
		int fin = disponibilidad[1][elDia.getDate().getDayOfMonth()-1];
		List<Turnos> losPosibles = new ArrayList<>();
		for(Turnos elTurno : losTurnos){
			if(elTurno.getInicio() == inicio && elTurno.getFin() == fin && checkEncargado(elCondeso, elTurno)) return elTurno;
			if(elTurno.getInicio() >= inicio && elTurno.getFin() <= fin && checkEncargado(elCondeso, elTurno)) losPosibles.add(elTurno);
		}
		Turnos elBueno;
		if(losPosibles.size() >= 1){
		elBueno = losPosibles.get(0);
		losPosibles.remove(0);
			for(Turnos elTurno : losPosibles){
				if(elBueno.getDuracion() < elTurno.getDuracion()) elBueno = elTurno;
			}
		}
		else return null;
		return elBueno;
	}


	private HashSet<Condeso> getFijos(){
		HashSet<Condeso> losFijos = new HashSet<>();
		for(Condeso elCondeso : condesos){
			if(elCondeso.isFijo()) losFijos.add(elCondeso);
		}
		condesos.removeAll(losFijos);
		return losFijos;
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
		if(elCondeso.getContrato() == Contrato.MiniJob) return true;
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
		if(elCondeso.getLevel() >= elTurno.getMinimo()) return true;
		return false;
	}

	private boolean checkEncargado(Condeso elCondeso, Turnos elTurno){
		if(!elTurno.deEncargado()) return true;
		else if(elCondeso.getTipo() == TipoEmpleado.Encargado || elCondeso.getTipo() == TipoEmpleado.GM) return true;
		else return false;
	}


	private void reacomodar(Set<Turnos> noAsignados, Set<Condeso> condesos, HashMap<Integer, Integer[][]> disponibilidad){
		for(Condeso elCondeso : condesos){
			elCondeso.quinceMas();
		}

		PriorityQueue<Condeso> fila = new PriorityQueue<>(new CompareCondesos());
		List<Condeso> regaladores;
		Set<Turnos> dificiles = new HashSet<>();

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
					countFase2++; //se debe quitar
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
					countFase2++; // a quitar
					break;
					}else{
					fila.addAll(checados);
					checados.clear();
					}

				}
				condesos.addAll(regaladores);
				condesos.addAll(useless);

				if(!Found){
					fila = new PriorityQueue<>(new CompareCondesos());
					regaladores = posibles.get(Reasons.maximoAlcanzado);
					condesos.removeAll(regaladores);
					checados = new HashSet<>();
					fila.addAll(condesos);
					for(Condeso elCondeso : regaladores){
						while(!Found && !fila.isEmpty()){
							candidate = fila.poll();
							Found = porMaximmoAlcanzado(elCondeso, candidate, elTurno);
							checados.add(candidate);
						}
						if(Found){
							countFase2++;
							break;
						}else{
							fila.addAll(checados);
							checados.clear();
						}
					}
					condesos.addAll(regaladores);
					if (!Found) {
						dificiles.add(elTurno);
					}
				}



			}

		}
		insist(dificiles);

	}

	private void insist(Set<Turnos> noAsignados){
		//return;
		Reasons laRazon;
		for(Turnos elTurno : noAsignados){
			Set<Condeso> losCandidatos = findCandidates(elTurno, condesos, disponibilidad);
			for(Condeso elCondeso : losCandidatos){
				laRazon = findReason(elTurno, elCondeso);
			}
		}
			}

	private boolean insistHelper(Turnos elTurno, Condeso elCondeso, Reasons laRazon, List<Condeso> fila){
		if(fila.size() > 10){

		}else{

		}

		return true; //quitar despues
	}

	private boolean porMaximmoAlcanzado(Condeso regalador, Condeso candidate, Turnos elTurno){
		Turnos oferta;
		int length = elTurno.getDate().lengthOfMonth();
		for(int i = 0; i < length; i++){
			oferta = regalador.getPersonal()[i];
			if(oferta != null && checkCondeso(candidate, disponibilidad, oferta)){
				candidate.asignarTurno(regalador.borrarTurno(oferta));
				regalador.asignarTurno(elTurno);
				return true;
			}
		}
		return false;
	}

	private boolean porDiasSeguidos(Condeso regalador, Condeso candidate, Turnos elTurno){
		Turnos oferta;
		int dia = elTurno.getDate().getDayOfMonth()-1;
		int max = regalador.getDiasSeguidos();
		for(int i = 0; i < max; i++){
			if(dia-1-i > 0)
			oferta = regalador.getPersonal()[dia-1-i];
			else break;
			if(oferta != null && checkCondeso(candidate, disponibilidad, oferta)){
				candidate.asignarTurno(regalador.borrarTurno(oferta));
				regalador.asignarTurno(elTurno);
			return true;
			}else if(oferta == null) break;
		}
		int length = elTurno.getDate().lengthOfMonth();

		for(int i = 0; i < max; i++){
			if(dia + 1 + i < length)
			oferta = regalador.getPersonal()[dia + 1 + i];
			else break;
			if(oferta != null && checkCondeso(candidate, disponibilidad, oferta)){
				candidate.asignarTurno(regalador.borrarTurno(oferta));
				regalador.asignarTurno(elTurno);
				return true;
			}else if(oferta == null) break;
		}

		return false;
	}

	private boolean useful(HashMap<Integer, Integer[][]> disponibilidad, Condeso elCondeso, int dia){
		int id = (int) elCondeso.getId();
		Integer[][] disp = disponibilidad.get(id);
		if(disp[1][dia-1] != 0) return true;
		return false;
	}

	private enum Reasons{
		finesOcupados, maximoAlcanzado, turnoEseDia, maximoDiasSeguidos, faltaNivel
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
		List<Condeso> porNivel = new ArrayList<>();

		for(Condeso elCondeso : candidates){
			if(elCondeso.getPersonal()[elTurno.getDate().getDayOfMonth()-1] != null) yaTieneTurno.add(elCondeso);
			else if(!checkDiasSeguidos(elCondeso, elTurno)) diasSeguidos.add(elCondeso);
			else if(!checkFinesLibres(elCondeso, elTurno)) finesDeSemana.add(elCondeso);
			else if(!elCondeso.checkMax(elTurno)) maximoDelMes.add(elCondeso);
			else if(!checkLevel(elCondeso, elTurno)) porNivel.add(elCondeso);

		}

		HashMap<Reasons, List<Condeso>> Razones = new HashMap<>();
		Razones.put(Reasons.turnoEseDia, yaTieneTurno);
		Razones.put(Reasons.maximoDiasSeguidos, diasSeguidos);
		Razones.put(Reasons.maximoAlcanzado, maximoDelMes);
		Razones.put(Reasons.finesOcupados, finesDeSemana);
		Razones.put(Reasons.faltaNivel, porNivel);
		return Razones;
	}

	private Set<Condeso> findCandidates(Turnos elTurno, Set<Condeso> condesos, HashMap<Integer, Integer[][]> disponibilidad){
		Set<Condeso> candidates = new HashSet<>();

		for(Condeso elCondeso : condesos ){
			if(checkDisponibilidad(elCondeso, disponibilidad, elTurno) && checkTienda(elCondeso, elTurno)) candidates.add(elCondeso);
		}
		return candidates;
	}

	private Reasons findReason(Turnos elTurno, Condeso elCondeso){
		if(elCondeso.getPersonal()[elTurno.getDate().getDayOfMonth()-1] != null) return Reasons.turnoEseDia;
		else if(!checkDiasSeguidos(elCondeso, elTurno)) return Reasons.maximoDiasSeguidos;
		else if(!checkFinesLibres(elCondeso, elTurno)) return Reasons.finesOcupados;
		else if(!elCondeso.checkMax(elTurno)) return Reasons.maximoAlcanzado;
		else if(!checkLevel(elCondeso, elTurno)) return Reasons.faltaNivel;
		else return null;
	}

}
