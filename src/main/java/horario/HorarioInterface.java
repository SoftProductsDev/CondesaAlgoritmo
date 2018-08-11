package horario;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class HorarioInterface {
  private HashMap<Date, Dias> mes;

  public HorarioInterface(HashMap<Date, Dias> mes) {
    this.mes = mes;
  }
}
