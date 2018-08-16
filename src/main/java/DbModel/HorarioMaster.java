package DbModel;

import horario.Dias;
import java.util.Map;
import javax.persistence.*;
import java.util.Date;


/**
 * Created by javier on 13/08/2018.
 */
@Entity
@Table(name = "horariomaster")
public class HorarioMaster {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ElementCollection
  @CollectionTable(name = "mesmaster")
  private Map<Date, Dias> mes;

  public HorarioMaster() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Map<Date, horario.Dias> getMes() {
    return mes;
  }

  public void setMes(Map<Date, horario.Dias> mes) {
    this.mes = mes;
  }
}

