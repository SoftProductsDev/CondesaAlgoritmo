package horario;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class HorarioInterface {
  private long Id;
  private int year;
  private Month month;
  private HashMap<LocalDate, Dias> mes;

  public Month getMonth(){return month;}

  public void setMonth(Month month){this.month = month;}

  public void setYear(int year){this.year = year;}

  public int getYear(){return year;}

  public HashMap<LocalDate, Dias> getMes() {
    return mes;
  }

  public void setMes(HashMap<LocalDate, Dias> mes) {
    this.mes = mes;
  }

  public HorarioInterface(HashMap<LocalDate, Dias> mes) {
    this.mes = mes;
  }
}
