package DbModel;

import horario.*;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

/**
 * Created by javier on 13/08/2018.
 */
public class HorarioPersonal{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy="HorarioPersonal")
    @MapKey(name="date")
    private HashMap<Date, horario.Dias> mes;

    public HorarioPersonal() {
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
