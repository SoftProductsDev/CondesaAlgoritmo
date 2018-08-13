package DbModel;

import horario.Turnos;

import java.sql.Date;
import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Dias {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy="dias")
    @MapKey(name="inicio")
    private HashMap<Integer,horario.Turnos> turnos;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HashMap<Integer, Turnos> getTurnos() {
        return turnos;
    }

    public void setTurnos(HashMap<Integer, Turnos> turnos) {
        this.turnos = turnos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
