package horario;

import java.time.LocalDate;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "horariopersonal")
public class HorarioPersonal{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ElementCollection
  @CollectionTable
  private Map<LocalDate, Dias> mes;

  public HorarioPersonal() {
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
