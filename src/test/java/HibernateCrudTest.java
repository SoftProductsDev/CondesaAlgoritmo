
import DbController.HibernateCrud;
import DbModel.Condeso;
import DbModel.Dias;
import DbModel.Tiendas;
import DbModel.Turnos;
import condeso.Contrato;
import condeso.TipoEmpleado;
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
    hbf.setId(1);


    DbModel.Tiendas mf = new DbModel.Tiendas();
    mf.setNombre("MF");
    hbf.setManager("Leo");
    hbf.setFechaApertura(LocalDate.now());
    hbf.setId(2);


    DbModel.Tiendas impler = new DbModel.Tiendas();
    hbf.setNombre("IMP");
    hbf.setManager("Jorge");
    hbf.setFechaApertura(LocalDate.now());
    hbf.setId(3);


    tiendas.add(impler);
    tiendas.add(mf);
    tiendas.add(hbf);


    Condeso condeso = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Pepe");
    condeso.setColor("#f48642");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso);

    Condeso condeso2 = new Condeso();
    condeso.setCaja(false);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo2);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(2);
    condeso.setTipo(TipoEmpleado.Equipo);
    condeso.setNombre("Pablo");
    condeso.setColor("#f1f441");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso2);

    Condeso condeso3 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo3);
    condeso.setFijos(false);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(3);
    condeso.setTipo(TipoEmpleado.GM);
    condeso.setNombre("Javier");
    condeso.setColor("#f44141");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso3);

    Condeso condeso4 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Sofia");
    condeso.setColor("#be41f4");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso4);

    Condeso condeso5 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Nuevo);
    condeso.setNombre("Ximena");
    condeso.setColor("#7f41f4");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso5);

    Condeso condeso6 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Nuevo);
    condeso.setNombre("Esteban");
    condeso.setColor("#61f441");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso6);

    Condeso condeso7 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Jose");
    condeso.setColor("#41f4ee");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso7);

    Condeso condeso8 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Memo");
    condeso.setColor("#4c41f4");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso8);

    Condeso condeso9 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Tonio");
    condeso.setColor("#f4b841");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso9);

    Condeso condeso10 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Abraham");
    condeso.setColor("#f47041");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso10);

    Condeso condeso11 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Martin");
    condeso.setColor("#0d5f63");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso11);

    Condeso condeso12 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Pepe");
    condeso.setColor("#750202");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso12);

    Condeso condeso13 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Esther");
    condeso.setColor("#6c0c6d");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso13);

    Condeso condeso14 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Jose");
    condeso.setColor("#b7bdf7");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso14);

    Condeso condeso15 = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Dariana");
    condeso.setColor("#b7f7ed");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso15);
  }

  private HashMap<Integer, Turnos> createTurnos() {
    HashMap<Integer, Turnos> result = new HashMap<Integer, Turnos>();
    Turnos turno = new Turnos();
    turno.setOcupado(false);
    turno.setMatutino(true);
    turno.setDuracion(4);
    turno.setInicio(8);
    turno.setElemental(false);
    turno.setFin(12);

    result.put(turno.getInicio(), turno);

    return  result;
  }

  public void testCreateTienda()
  {
    DbModel.Tiendas tienda = new DbModel.Tiendas();
    tienda.setNombre("HBF");
    HibernateCrud.SaveTienda(tienda);
  }

  public void testGetTiendas()
  {
    List<Tiendas> tiendas = HibernateCrud.GetAllTiendas();
    for (Tiendas tienda:tiendas
    ) {
      System.out.println(tienda);
    }
  }

}