package horario;


import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class HorarioEntrega {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "disponibilidad")
  private int[][] disponibilidad;

  @Column(name = "Minimo")
  private int min;

  @Column(name ="Maximo")
  private int max;

  @Column(name = "mes")
  private LocalDate mes;

  public HorarioEntrega() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int[][] getDisponibilidad() {
    return disponibilidad;
  }

  public void setDisponibilidad(int[][] disponibilidad) {
    this.disponibilidad = disponibilidad;
  }

  public int getMin() {
    return min;
  }

  public void setMin(int min) {
    this.min = min;
  }

  public int getMax() {
    return max;
  }

  public void setMax(int max) {
    this.max = max;
  }

  public LocalDate getMes() {
    return mes;
  }

  public void setMes(LocalDate mes) {
    this.mes = mes;
  }
}
