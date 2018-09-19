package horario;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class HorarioInterface {
  private long Id;
  private HashMap<LocalDate, Dias> mes;

  public HashMap<LocalDate, Dias> getMes() {
    return mes;
  }

  public void setMes(HashMap<LocalDate, Dias> mes) {
    this.mes = mes;
  }

  public HashMap<LocalDate, DbModel.Dias>  convertToDbModel()
  {

   HashMap<LocalDate, DbModel.Dias> result = new HashMap<LocalDate, DbModel.Dias>();
    mes.forEach((k,v)->
    result.put(v.getDate(), v.convertToDbModel()));
   return result;
  }

  public HorarioInterface(HashMap<LocalDate, Dias> mes) {
    this.mes = mes;
  }
}
