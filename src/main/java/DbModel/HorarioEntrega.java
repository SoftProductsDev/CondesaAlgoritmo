package DbModel;

import java.time.LocalDate;
import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by javier on 13/08/2018.
 */

@Entity
@Table
public class HorarioEntrega {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ElementCollection
  @CollectionTable(name = "mesentrega")
  private Map<LocalDate, Dias> mes;

  public HorarioEntrega() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Map<LocalDate, Dias> getMes() {
    return mes;
  }

  public void setMes(Map<LocalDate, Dias> mes) {
    this.mes = mes;
  }
}
