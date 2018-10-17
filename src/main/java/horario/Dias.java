package horario;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import tiendas.Tiendas;

public class Dias {
    private long Id;
    //Usar TreeSet
    private Set<Turnos> turnos = new TreeSet<>();
    private LocalDate date;
    private int dia;
    private DayOfWeek day;
    private Tiendas tienda;

    public Tiendas getTienda(){return tienda;}

    public void setTienda(Tiendas tienda){this.tienda = tienda;}

    public  DbModel.Dias convertToDbModel()
    {
        DbModel.Dias result = new DbModel.Dias();
        result.setDate(date);
        //result.setTurnos(convertTurnosToDbModel());
        return result;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setDia(int dia){this.dia  = dia;}

    public int getDia(){return dia;}

    public DayOfWeek getDay(){return day;}

    public void setDay(DayOfWeek day){this.day = day;}


    private Map<Integer, DbModel.Turnos> convertTurnosToDbModel() {
        HashMap<Integer, DbModel.Turnos> result = new HashMap<>();
        //turnos.forEach((k,v) -> result.put(k, v.convertToDbModel()));
        return  result;
    }

    public Dias(LocalDate date) {
        this.date = date;
    }
    public Set<Turnos> getTurnos() {
        return turnos;
    }
    public void setTurnos(Set<Turnos> turnos) {
        this.turnos = turnos;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }



}