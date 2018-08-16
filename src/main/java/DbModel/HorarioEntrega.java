package DbModel;

import horario.Dias;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by javier on 13/08/2018.
 */

@Entity
public class HorarioEntrega {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToMany(mappedBy="HorarioEntrega")
  @MapKey(name="date")
  private HashMap<Date, Dias> mes;

  public HorarioEntrega() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public HashMap<Date, Dias> getMes() {
    return mes;
  }

  public void setMes(HashMap<Date, Dias> mes) {
    this.mes = mes;
  }
}