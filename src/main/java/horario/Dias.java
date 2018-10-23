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
    private Set<Turnos> turnos = new TreeSet<>(new CompareTurnos2());
    private LocalDate date;
    private int dia;
    private DayOfWeek day;
    private Tiendas tienda;
    private HashMap<Integer, Hora> horas;
    private float promedioMinimo;

    public float getPromedioMinimo(){return promedioMinimo;}

    public void setPromedioMinimo(float promedioMinimo){this.promedioMinimo = promedioMinimo;}


    public HashMap<Integer, Hora> getHoras(){return horas;}

    public void setHoras(){
        for(Turnos elTurno : turnos ){
            elTurno.setHoras();
        }
    }

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
    public void addTurno(Turnos elTurno){
        turnos.add(elTurno);
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }



}