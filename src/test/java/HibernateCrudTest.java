
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
    condeso2.setCaja(false);
    condeso2.setAntiguedad(LocalDate.now());
    condeso2.setContrato(Contrato.Tipo2);
    condeso2.setFijos(true);
    condeso2.setManana(true);
    condeso2.setTarde(true);
    condeso2.setLevel(2);
    condeso2.setTipo(TipoEmpleado.Equipo);
    condeso2.setNombre("Pablo");
    condeso2.setColor("#f1f441");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso2);

    Condeso condeso3 = new Condeso();
    condeso3.setCaja(true);
    condeso3.setAntiguedad(LocalDate.now());
    condeso3.setContrato(Contrato.Tipo3);
    condeso3.setFijos(false);
    condeso3.setManana(true);
    condeso3.setTarde(true);
    condeso3.setLevel(3);
    condeso3.setTipo(TipoEmpleado.GM);
    condeso3.setNombre("Javier");
    condeso3.setColor("#f44141");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso3);

    Condeso condeso4 = new Condeso();
    condeso4.setCaja(true);
    condeso4.setAntiguedad(LocalDate.now());
    condeso4.setContrato(Contrato.Tipo1);
    condeso4.setFijos(true);
    condeso4.setManana(true);
    condeso4.setTarde(true);
    condeso4.setLevel(1);
    condeso4.setTipo(TipoEmpleado.Encargado);
    condeso4.setNombre("Sofia");
    condeso4.setColor("#be41f4");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso4);

    Condeso condeso5 = new Condeso();
    condeso5.setCaja(true);
    condeso5.setAntiguedad(LocalDate.now());
    condeso5.setContrato(Contrato.Tipo1);
    condeso5.setFijos(true);
    condeso5.setManana(true);
    condeso5.setTarde(true);
    condeso5.setLevel(1);
    condeso5.setTipo(TipoEmpleado.Nuevo);
    condeso5.setNombre("Ximena");
    condeso5.setColor("#7f41f4");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso5);

    Condeso condeso6 = new Condeso();
    condeso6.setCaja(true);
    condeso6.setAntiguedad(LocalDate.now());
    condeso6.setContrato(Contrato.Tipo1);
    condeso6.setFijos(true);
    condeso6.setManana(true);
    condeso6.setTarde(true);
    condeso6.setLevel(1);
    condeso6.setTipo(TipoEmpleado.Nuevo);
    condeso6.setNombre("Esteban");
    condeso6.setColor("#61f441");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso6);

    Condeso condeso7 = new Condeso();
    condeso7.setCaja(true);
    condeso7.setAntiguedad(LocalDate.now());
    condeso7.setContrato(Contrato.Tipo1);
    condeso7.setFijos(true);
    condeso7.setManana(true);
    condeso7.setTarde(true);
    condeso7.setLevel(1);
    condeso7.setTipo(TipoEmpleado.Encargado);
    condeso7.setNombre("Jose");
    condeso7.setColor("#41f4ee");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso7);

    Condeso condeso8 = new Condeso();
    condeso8.setCaja(true);
    condeso8.setAntiguedad(LocalDate.now());
    condeso8.setContrato(Contrato.Tipo1);
    condeso8.setFijos(true);
    condeso8.setManana(true);
    condeso8.setTarde(true);
    condeso8.setLevel(1);
    condeso8.setTipo(TipoEmpleado.Encargado);
    condeso8.setNombre("Memo");
    condeso8.setColor("#4c41f4");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso8);

    Condeso condeso9 = new Condeso();
    condeso9.setCaja(true);
    condeso9.setAntiguedad(LocalDate.now());
    condeso9.setContrato(Contrato.Tipo1);
    condeso9.setFijos(true);
    condeso9.setManana(true);
    condeso9.setTarde(true);
    condeso9.setLevel(1);
    condeso9.setTipo(TipoEmpleado.Encargado);
    condeso9.setNombre("Tonio");
    condeso9.setColor("#f4b841");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso9);

    Condeso condeso10 = new Condeso();
    condeso10.setCaja(true);
    condeso10.setAntiguedad(LocalDate.now());
    condeso10.setContrato(Contrato.Tipo1);
    condeso10.setFijos(true);
    condeso10.setManana(true);
    condeso10.setTarde(true);
    condeso10.setLevel(1);
    condeso10.setTipo(TipoEmpleado.Encargado);
    condeso10.setNombre("Abraham");
    condeso10.setColor("#f47041");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso10);

    Condeso condeso11 = new Condeso();
    condeso11.setCaja(true);
    condeso11.setAntiguedad(LocalDate.now());
    condeso11.setContrato(Contrato.Tipo1);
    condeso11.setFijos(true);
    condeso11.setManana(true);
    condeso11.setTarde(true);
    condeso11.setLevel(1);
    condeso11.setTipo(TipoEmpleado.Encargado);
    condeso11.setNombre("Martin");
    condeso11.setColor("#0d5f63");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso11);

    Condeso condeso12 = new Condeso();
    condeso12.setCaja(true);
    condeso12.setAntiguedad(LocalDate.now());
    condeso12.setContrato(Contrato.Tipo1);
    condeso12.setFijos(true);
    condeso12.setManana(true);
    condeso12.setTarde(true);
    condeso12.setLevel(1);
    condeso12.setTipo(TipoEmpleado.Encargado);
    condeso12.setNombre("Pepe");
    condeso12.setColor("#750202");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso12);

    Condeso condeso13 = new Condeso();
    condeso13.setCaja(true);
    condeso13.setAntiguedad(LocalDate.now());
    condeso13.setContrato(Contrato.Tipo1);
    condeso13.setFijos(true);
    condeso13.setManana(true);
    condeso13.setTarde(true);
    condeso13.setLevel(1);
    condeso13.setTipo(TipoEmpleado.Encargado);
    condeso13.setNombre("Esther");
    condeso13.setColor("#6c0c6d");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso13);

    Condeso condeso14 = new Condeso();
    condeso14.setCaja(true);
    condeso14.setAntiguedad(LocalDate.now());
    condeso14.setContrato(Contrato.Tipo1);
    condeso14.setFijos(true);
    condeso14.setManana(true);
    condeso14.setTarde(true);
    condeso14.setLevel(1);
    condeso14.setTipo(TipoEmpleado.Encargado);
    condeso14.setNombre("Jose");
    condeso14.setColor("#b7bdf7");
    //condeso.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso14);

    Condeso condeso15 = new Condeso();
    condeso15.setCaja(true);
    condeso15.setAntiguedad(LocalDate.now());
    condeso15.setContrato(Contrato.Tipo1);
    condeso15.setFijos(true);
    condeso15.setManana(true);
    condeso15.setTarde(true);
    condeso15.setLevel(1);
    condeso15.setTipo(TipoEmpleado.Encargado);
    condeso15.setNombre("Dariana");
    condeso15.setColor("#b7f7ed");
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