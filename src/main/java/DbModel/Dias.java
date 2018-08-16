package DbModel;

import horario.Turnos;

import java.util.Map;
import javax.persistence.*;
import java.sql.Date;
import java.util.HashMap;

@Entity
@Table(name = "dias")
public class Dias {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ElementCollection
    @CollectionTable(name = "turnos")
    private Map<Integer, Turnos> turnos;

    @Column(name = "date")
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
