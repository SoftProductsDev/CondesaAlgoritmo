package condeso;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class HorasMes {
  @Id
  @GeneratedValue
  private long id;

  @Column
  private int hours;

  @Transient
  private LocalDate date;

  public int getHoras() {
    return hours;
  }

  public void setHoras(int horas) {
    this.hours = horas;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
