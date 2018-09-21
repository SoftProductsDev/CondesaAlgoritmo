package DbModel;

import java.time.LocalDate;
import java.util.Map;
import javax.persistence.*;

@Entity
@Table(name = "dias")
public class Dias {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ElementCollection
    @MapKey (name = "inicio")
    @CollectionTable
    @OneToMany(cascade = CascadeType.ALL)
    private Map<Integer, Turnos> turnos;

    @Column(name = "date")
    private java.util.Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<Integer, Turnos> getTurnos() {
        return turnos;
    }

    public void setTurnos(Map<Integer, Turnos> turnos) {
        this.turnos = turnos;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
