package horario;

import horario.Dias;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;


@Entity
@Table
public class HorarioMaster {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @JoinColumn
  @OneToMany( fetch = FetchType.EAGER)
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Map<LocalDate, Dias> mes;

  public HorarioMaster() {
  }

  public HorarioMaster(HashMap<LocalDate,horario.Dias> master) {
    mes = master;
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

