package condeso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HorasMes {
  @Id
  @GeneratedValue
  private long id;

  @Column
  private int hours;

  public int getHoras() {
    return hours;
  }

  public void setHoras(int horas) {
    this.hours = horas;
  }
}
