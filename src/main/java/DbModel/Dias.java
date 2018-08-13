package DbModel;

import horario.Turnos;

import java.sql.Date;
import java.util.HashMap;

public class Dias {
    private long id;
    private HashMap<Integer,horario.Turnos> turnos = new HashMap<Integer, Turnos>();
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
