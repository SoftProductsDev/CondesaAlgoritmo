import DbController.HibernateCrud;
import DbModel.Condeso;
import DbModel.Dias;
import DbModel.Tiendas;
import DbModel.Turnos;
import condeso.Contrato;
import condeso.TipoEmpleado;
import junit.framework.TestCase;

import java.time.LocalDate;
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

  public void testCreate(){
    HashMap<Date, Dias> entrega = new HashMap<Date ,Dias>();
    Dias dia = new Dias();
    Date date = new Date();
    dia.setDate(date);

    HashMap<Integer, Turnos> turnos = createTurnos();
    dia.setTurnos(turnos);

    entrega.put(dia.getDate(), dia);

    Condeso condeso = new Condeso();
    condeso.setCaja(true);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setContrato(Contrato.Tipo1);
    //condeso.setEntrega(entrega);
    condeso.setFijos(true);
    condeso.setManana(true);
    condeso.setTarde(true);
    condeso.setLevel(1);
    condeso.setTipo(TipoEmpleado.Encargado);
    condeso.setNombre("Empleado");

    DbController.HibernateCrud.SaveCondeso(condeso);
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
    List<DbModel.Tiendas> tiendas = HibernateCrud.GetAllTiendas();
    for (Tiendas tienda:tiendas) {
      System.out.println(tienda);
    }
  }

}