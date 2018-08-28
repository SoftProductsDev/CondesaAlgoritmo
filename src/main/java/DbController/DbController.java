package DbController;

import DbModel.HorarioEntrega;
import DbModel.HorarioMaster;
import DbModel.HorarioPersonal;
import DbModel.Tiendas;
import condeso.Condeso;
import java.util.ArrayList;
import java.util.List;
import javax.rmi.CORBA.Tie;
import sun.security.pkcs11.Secmod.DbMode;

public class DbController {
  public static String SaveCondeso(Condeso condeso)
  {
    DbModel.Condeso dbCondeso = convertCondesoToDb(condeso);
    HibernateCrud.SaveCondeso(dbCondeso);

    return "Se ha añadido Condeso:" + dbCondeso;
  }

  public static String UpdateCondeso(Condeso updatedCondeso){
    DbModel.Condeso dbCondeso = convertCondesoToDb(updatedCondeso);
    dbCondeso.setId(updatedCondeso.getId());
    HibernateCrud.UpdateCondeso(dbCondeso);

    return "Condeso actualizado";
  }
  public static String DeleteCondeso(Condeso deletedCondeso){
    DbModel.Condeso dbCondeso = convertCondesoToDb(deletedCondeso);
    dbCondeso.setId(deletedCondeso.getId());
    HibernateCrud.UpdateCondeso(dbCondeso);

    return "Condeso borrado";
  }
  public static Condeso GetCondeso(Condeso condesoBuscado){
    return null;
  }
  public static List<Condeso> GetAllCondesos(){
    return null;
  }

  private static List<Tiendas> convertListToDbModel(List<tiendas.Tiendas> dondePuedeTrabajar) {
    List<Tiendas> result = new ArrayList<>();
    for (tiendas.Tiendas tienda: dondePuedeTrabajar) {
      Tiendas dbTienda = new Tiendas();
      HorarioMaster master = new HorarioMaster();
      master.setMes(tienda.getMaster().convertToDbModel());
      dbTienda.setMaster(master);
      dbTienda.setId(tienda.getId());
      dbTienda.setNombre(tienda.getNombre());
      dbTienda.setPlantilla(tienda.getPlantilla().convertToDbModel());
    }
    return  result;
  }

  private static DbModel.Condeso convertCondesoToDb(condeso.Condeso condeso)
  {
    DbModel.Condeso dbCondeso = new DbModel.Condeso();
    dbCondeso.setTipo(condeso.getTipo());
    dbCondeso.setNombre(condeso.getNombre());
    dbCondeso.setTarde(condeso.isTarde());
    dbCondeso.setManana(condeso.isManana());
    dbCondeso.setLevel(condeso.getLevel());
    dbCondeso.setFijos(condeso.isFijos());
    dbCondeso.setContrato(condeso.getContrato());
    dbCondeso.setAntiguedad(condeso.getAntiguedad());
    dbCondeso.setCaja(condeso.isCaja());

    dbCondeso.setDondePuedeTrabajar(convertListToDbModel(condeso.getDondePuedeTrabajar()));

    HorarioEntrega entrega = new HorarioEntrega();
    entrega.setMes(condeso.getEntrega().convertToDbModel());
    dbCondeso.setEntrega(entrega);

    HorarioMaster master = new HorarioMaster();
    master.setMes(condeso.getMaster().convertToDbModel());
    dbCondeso.setMaster(master);

    HorarioPersonal personal = new HorarioPersonal();
    personal.setMes(condeso.getPersonal().convertToDbModel());
    dbCondeso.setPersonal(personal);

    return dbCondeso;
  }
}
