package horario;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class HorarioInterface {
  private HashMap<Date, Dias> mes;

  public HashMap<Date, Dias> getMes() {
    return mes;
  }

  public void setMes(HashMap<Date, Dias> mes) {
    this.mes = mes;
  }

  public HashMap<Date, DbModel.Dias>  convertToDbModel()
  {

   HashMap<Date, DbModel.Dias> result = new HashMap<Date, DbModel.Dias>();
    mes.forEach((k,v)-> v.convertToDbModel());
   return result;
  }

  public HorarioInterface(HashMap<Date, Dias> mes) {
    this.mes = mes;
  }
}
