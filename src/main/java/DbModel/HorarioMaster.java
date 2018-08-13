package DbModel;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by javier on 13/08/2018.
 */
public class HorarioMaster {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToMany(mappedBy="HorarioMaster")
  @MapKey(name="date")
  private HashMap<Date, horario.Dias> mes;

  public HorarioMaster() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public HashMap<Date, horario.Dias> getMes() {
    return mes;
  }

  public void setMes(HashMap<Date, horario.Dias> mes) {
    this.mes = mes;
  }
}

