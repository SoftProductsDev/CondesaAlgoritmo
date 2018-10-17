
import DbController.HibernateCrud;
import DbModel.Condeso;
import DbModel.Dias;
import DbModel.HorarioMaster;
import DbModel.Tiendas;
import DbModel.Turnos;
import condeso.Contrato;
import condeso.TipoEmpleado;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import junit.framework.TestCase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HibernateCrudTest extends TestCase {

  public void testGet() {
    List<Condeso> condesos = DbController.HibernateCrud.GetAllCondesos();
    for (Condeso condeso: condesos
    ) {
      System.out.println(condeso.toString());
    }
  }

  public void testCreateCondeso(){
    List<DbModel.Tiendas> tiendas = new ArrayList<>();
    DbModel.Tiendas hbf = new DbModel.Tiendas();
    hbf.setNombre("HBF");
    hbf.setManager("Jorge");
    hbf.setFechaApertura(LocalDate.now());
    //hbf.setId(1);
    HibernateCrud.SaveTienda(hbf);


    DbModel.Tiendas mf = new DbModel.Tiendas();
    mf.setNombre("MF");
    mf.setManager("Leo");
    mf.setFechaApertura(LocalDate.now());
    //mf.setId(2);
    HibernateCrud.SaveTienda(mf);


    DbModel.Tiendas impler = new DbModel.Tiendas();
    impler.setNombre("IMP");
    impler.setManager("Jorge");
    impler.setFechaApertura(LocalDate.now());
    //impler.setId(3);
    HibernateCrud.SaveTienda(impler);


    tiendas.add(impler);
    tiendas.add(mf);
    tiendas.add(hbf);



    Condeso condeso = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.MiniJob);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Pepe");
    condeso.setColor("#f48642");
    condeso.setId(1);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso);

    Condeso condeso2 = new Condeso();
    condeso2.setCaja(false);
    condeso2.setAntiguedad(LocalDate.now());
    condeso2.setContrato(Contrato.otros);
    condeso2.setFijos(true);
    condeso2.setManana(true);
    condeso2.setTarde(true);
    condeso2.setLevel(2);
    condeso2.setTipo(TipoEmpleado.Equipo);
    condeso2.setNombre("Pablo");
    condeso2.setColor("#f1f441");
    condeso2.setId(2);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso2);

    Condeso condeso3 = new Condeso();
    condeso3.setCaja(true);
    condeso3.setAntiguedad(LocalDate.now());
    condeso3.setContrato(Contrato.MiniJob);
    condeso3.setFijos(false);
    condeso3.setManana(true);
    condeso3.setTarde(true);
    condeso3.setLevel(3);
    condeso3.setTipo(TipoEmpleado.GM);
    condeso3.setNombre("Javier");
    condeso3.setColor("#f44141");
    condeso3.setId(3);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso3);

    Condeso condeso4 = new Condeso();
    condeso4.setCaja(true);
    condeso4.setAntiguedad(LocalDate.now());
    condeso4.setContrato(Contrato.MiniJob);
    condeso4.setFijos(true);
    condeso4.setManana(true);
    condeso4.setTarde(true);
    condeso4.setLevel(1);
    condeso4.setTipo(TipoEmpleado.Encargado);
    condeso4.setNombre("Sofia");
    condeso4.setColor("#be41f4");
    condeso4.setId(4);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso4);

    Condeso condeso5 = new Condeso();
    condeso5.setCaja(true);
    condeso5.setAntiguedad(LocalDate.now());
    condeso5.setContrato(Contrato.MiniJob);
    condeso5.setFijos(true);
    condeso5.setManana(true);
    condeso5.setTarde(true);
    condeso5.setLevel(1);
    condeso5.setTipo(TipoEmpleado.Nuevo);
    condeso5.setNombre("Ximena");
    condeso5.setColor("#7f41f4");
    condeso5.setId(5);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso5);

    Condeso condeso6 = new Condeso();
    condeso6.setCaja(true);
    condeso6.setAntiguedad(LocalDate.now());
    condeso6.setContrato(Contrato.MiniJob);
    condeso6.setFijos(true);
    condeso6.setManana(true);
    condeso6.setTarde(true);
    condeso6.setLevel(1);
    condeso6.setTipo(TipoEmpleado.Nuevo);
    condeso6.setNombre("Esteban");
    condeso6.setColor("#61f441");
    condeso6.setId(6);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso6);

    Condeso condeso7 = new Condeso();
    condeso7.setCaja(true);
    condeso7.setAntiguedad(LocalDate.now());
    condeso7.setContrato(Contrato.MiniJob);
    condeso7.setFijos(true);
    condeso7.setManana(true);
    condeso7.setTarde(true);
    condeso7.setLevel(1);
    condeso7.setTipo(TipoEmpleado.Encargado);
    condeso7.setNombre("Jose");
    condeso7.setColor("#41f4ee");
    condeso7.setId(7);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso7);

    Condeso condeso8 = new Condeso();
    condeso8.setCaja(true);
    condeso8.setAntiguedad(LocalDate.now());
    condeso8.setContrato(Contrato.MiniJob);
    condeso8.setFijos(true);
    condeso8.setManana(true);
    condeso8.setTarde(true);
    condeso8.setLevel(1);
    condeso8.setTipo(TipoEmpleado.Encargado);
    condeso8.setNombre("Memo");
    condeso8.setColor("#4c41f4");
    condeso8.setId(8);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso8);

    Condeso condeso9 = new Condeso();
    condeso9.setCaja(true);
    condeso9.setAntiguedad(LocalDate.now());
    condeso9.setContrato(Contrato.MiniJob);
    condeso9.setFijos(true);
    condeso9.setManana(true);
    condeso9.setTarde(true);
    condeso9.setLevel(1);
    condeso9.setTipo(TipoEmpleado.Encargado);
    condeso9.setNombre("Tonio");
    condeso9.setColor("#f4b841");
    condeso9.setId(9);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso9);

    Condeso condeso10 = new Condeso();
    condeso10.setCaja(true);
    condeso10.setAntiguedad(LocalDate.now());
    condeso10.setContrato(Contrato.MiniJob);
    condeso10.setFijos(true);
    condeso10.setManana(true);
    condeso10.setTarde(true);
    condeso10.setLevel(1);
    condeso10.setTipo(TipoEmpleado.Encargado);
    condeso10.setNombre("Abraham");
    condeso10.setColor("#f47041");
    condeso10.setId(10);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso10);

    Condeso condeso11 = new Condeso();
    condeso11.setCaja(true);
    condeso11.setAntiguedad(LocalDate.now());
    condeso11.setContrato(Contrato.MiniJob);
    condeso11.setFijos(true);
    condeso11.setManana(true);
    condeso11.setTarde(true);
    condeso11.setLevel(1);
    condeso11.setTipo(TipoEmpleado.Encargado);
    condeso11.setNombre("Martin");
    condeso11.setColor("#0d5f63");
    condeso11.setId(11);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso11);

    Condeso condeso12 = new Condeso();
    condeso12.setCaja(true);
    condeso12.setAntiguedad(LocalDate.now());
    condeso12.setContrato(Contrato.MiniJob);
    condeso12.setFijos(true);
    condeso12.setManana(true);
    condeso12.setTarde(true);
    condeso12.setLevel(1);
    condeso12.setTipo(TipoEmpleado.Encargado);
    condeso12.setNombre("Pepe");
    condeso12.setColor("#750202");
    condeso12.setId(12);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso12);

    Condeso condeso13 = new Condeso();
    condeso13.setCaja(true);
    condeso13.setAntiguedad(LocalDate.now());
    condeso13.setContrato(Contrato.MiniJob);
    condeso13.setFijos(true);
    condeso13.setManana(true);
    condeso13.setTarde(true);
    condeso13.setLevel(1);
    condeso13.setTipo(TipoEmpleado.Encargado);
    condeso13.setNombre("Esther");
    condeso13.setColor("#6c0c6d");
    condeso13.setId(13);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso13);

    Condeso condeso14 = new Condeso();
    condeso14.setCaja(true);
    condeso14.setAntiguedad(LocalDate.now());
    condeso14.setContrato(Contrato.MiniJob);
    condeso14.setFijos(true);
    condeso14.setManana(true);
    condeso14.setTarde(true);
    condeso14.setLevel(1);
    condeso14.setTipo(TipoEmpleado.Encargado);
    condeso14.setNombre("Jose");
    condeso14.setColor("#b7bdf7");
    condeso14.setId(14);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso14);

    Condeso condeso15 = new Condeso();
    condeso15.setCaja(true);
    condeso15.setAntiguedad(LocalDate.now());
    condeso15.setContrato(Contrato.MiniJob);
    condeso15.setFijos(true);
    condeso15.setManana(true);
    condeso15.setTarde(true);
    condeso15.setLevel(1);
    condeso15.setTipo(TipoEmpleado.Encargado);
    condeso15.setNombre("Dariana");
    condeso15.setColor("#b7f7ed");
    condeso15.setId(15);
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso15);
  }

  public void testCreateHorario() {
    HorarioMaster horarioMaster = new HorarioMaster();
    horarioMaster.setMes(createMes());
    Tiendas tienda = HibernateCrud.GetAllTiendas().get(0);
    tienda.setMaster(horarioMaster);
    HibernateCrud.UpdateTienda(tienda);
  }

  private Map<LocalDate, Dias> createMes() {
    Map<LocalDate, Dias> result = new HashMap<>();
    Dias dia1 = createDia(LocalDate.now().withDayOfMonth(1));
    Dias dia2 = createDia2(LocalDate.now().withDayOfMonth(2));
    Dias dia3 = createDia3(LocalDate.now().withDayOfMonth(3));
    Dias dia4 = createDia4(LocalDate.now().withDayOfMonth(4));
    Dias dia5 = createDia5(LocalDate.now().withDayOfMonth(5));
    Dias dia6 = createDia6(LocalDate.now().withDayOfMonth(6));
    Dias dia7 = createDia7(LocalDate.now().withDayOfMonth(7));
    result.put(dia1.getDate(), dia1);
    result.put(dia2.getDate(), dia2);
    result.put(dia3.getDate(), dia3);
    result.put(dia4.getDate(), dia4);
    result.put(dia5.getDate(), dia5);
    result.put(dia6.getDate(), dia6);
    result.put(dia7.getDate(), dia7);
    return result;
  }

  private Dias createDia(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos());
    return result;
  }

  private Dias createDia2(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos2());
    return result;
  }

  private Dias createDia3(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos3());
    return result;
  }

  private Dias createDia4(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos4());
    return result;
  }

  private Dias createDia5(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos5());
    return result;
  }
  private Dias createDia6(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos6());
    return result;
  }

  private Dias createDia7(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos7());
    return result;
  }

  private Set<Turnos> createTurnos3() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(16);
    turno1.setCondeso(condesos.get(2));

    Turnos turno2 = new Turnos();
    turno2.setInicio(16);
    turno2.setFin(21);
    turno2.setCondeso(condesos.get(1));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(15);
    turno3.setCondeso(condesos.get(6));

    Turnos turno4 = new Turnos();
    turno4.setInicio(17);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(7));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(11));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(14);
    turno6.setCondeso(condesos.get(13));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(14));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(4));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos4() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(17);
    turno1.setCondeso(condesos.get(1));

    Turnos turno2 = new Turnos();
    turno2.setInicio(15);
    turno2.setFin(20);
    turno2.setCondeso(condesos.get(2));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(16);
    turno3.setCondeso(condesos.get(3));

    Turnos turno4 = new Turnos();
    turno4.setInicio(16);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(4));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(5));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(15);
    turno6.setCondeso(condesos.get(6));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(7));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(8));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos5() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(16);
    turno1.setCondeso(condesos.get(3));

    Turnos turno2 = new Turnos();
    turno2.setInicio(16);
    turno2.setFin(21);
    turno2.setCondeso(condesos.get(1));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(15);
    turno3.setCondeso(condesos.get(2));

    Turnos turno4 = new Turnos();
    turno4.setInicio(17);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(12));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(11));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(14);
    turno6.setCondeso(condesos.get(7));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(14));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(4));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos6() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(16);
    turno1.setCondeso(condesos.get(9));

    Turnos turno2 = new Turnos();
    turno2.setInicio(16);
    turno2.setFin(21);
    turno2.setCondeso(condesos.get(4));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(15);
    turno3.setCondeso(condesos.get(10));

    Turnos turno4 = new Turnos();
    turno4.setInicio(17);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(7));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(11));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(14);
    turno6.setCondeso(condesos.get(8));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(6));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(5));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos7() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(16);
    turno1.setCondeso(condesos.get(2));

    Turnos turno2 = new Turnos();
    turno2.setInicio(16);
    turno2.setFin(21);
    turno2.setCondeso(condesos.get(1));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(15);
    turno3.setCondeso(condesos.get(7));

    Turnos turno4 = new Turnos();
    turno4.setInicio(17);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(3));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(11));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(14);
    turno6.setCondeso(condesos.get(5));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(14));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(4));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos2() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(16);
    turno1.setCondeso(condesos.get(9));

    Turnos turno2 = new Turnos();
    turno2.setInicio(16);
    turno2.setFin(21);
    turno2.setCondeso(condesos.get(1));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(15);
    turno3.setCondeso(condesos.get(10));

    Turnos turno4 = new Turnos();
    turno4.setInicio(17);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(7));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(11));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(14);
    turno6.setCondeso(condesos.get(13));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(14));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(4));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(12);
    turno1.setFin(20);
    turno1.setCondeso(condesos.get(0));

    Turnos turno2 = new Turnos();
    turno2.setInicio(8);
    turno2.setFin(14);
    turno2.setCondeso(condesos.get(1));

    Turnos turno3 = new Turnos();
    turno3.setInicio(8);
    turno3.setFin(14);
    turno3.setCondeso(condesos.get(2));

    Turnos turno4 = new Turnos();
    turno4.setInicio(14);
    turno4.setFin(22);
    turno4.setCondeso(condesos.get(3));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(22);
    turno5.setCondeso(condesos.get(4));

    Turnos turno6 = new Turnos();
    turno6.setInicio(12);
    turno6.setFin(18);
    turno6.setCondeso(condesos.get(5));

    Turnos turno7 = new Turnos();
    turno7.setInicio(16);
    turno7.setFin(22);
    turno7.setCondeso(condesos.get(6));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(7));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  /*public void testCreateTienda()
  {
    DbModel.Tiendas tienda = new DbModel.Tiendas();
    tienda.setNombre("HBF");
    HibernateCrud.SaveTienda(tienda);
  }*/

  public void testGetTiendas()
  {
    List<Tiendas> tiendas = HibernateCrud.GetAllTiendas();
    for (Tiendas tienda:tiendas
    ) {
      System.out.println(tienda);
    }
  }

}