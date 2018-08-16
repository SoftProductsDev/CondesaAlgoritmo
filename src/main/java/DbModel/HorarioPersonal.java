package DbModel;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by javier on 13/08/2018.
 */

@Entity
@Table(name = "horariopersonal")
public class HorarioPersonal{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

  @ElementCollection
  @CollectionTable(name = "mespersonal")
  private Map<Date, horario.Dias> mes;

    public HorarioPersonal() {
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
