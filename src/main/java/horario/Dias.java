package horario;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import tiendas.Tiendas;

@Entity
@Table(name = "dias")
public class Dias {
    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    //Usar TreeSet
    @ElementCollection
    @MapKey(name = "inicio")
    @CollectionTable
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Turnos> shifts;

    @Column(name = "date")
    private LocalDate date;

    @Transient
    private Tiendas tienda;
    @Transient
    private HashMap<Integer, Hora> horas = new HashMap<>();
    @Transient
    private float promedioMinimo;

    public Dias(){
        shifts = new HashSet<>();
    }

    public Dias(LocalDate date, Tiendas laTienda){
        this.date = date;
        this.tienda = laTienda;
        shifts = new HashSet<>();
        switch (date.getDayOfWeek()){
            case SATURDAY:
            case SUNDAY:
            case FRIDAY:
            case THURSDAY:
                promedioMinimo = 2.1F;
             break;
            case WEDNESDAY:
            case TUESDAY:
            case MONDAY:
                promedioMinimo = 1.8F;
            break;
        }
        laTienda.getMaster().getMes().put(date, this);
    }

    public void  resetMinimoTurnos(){
        for(Turnos elTurno : shifts){
            elTurno.resetMinimo();
        }
    }

    public float getPromedioMinimo(){return promedioMinimo;}

    public void setPromedioMinimo(float promedioMinimo){this.promedioMinimo = promedioMinimo;}


    public HashMap<Integer, Hora> getHoras(){return horas;}

    public void setHoras(){
        for(Turnos elTurno : shifts ){
            elTurno.setHoras();
        }
    }

    public Tiendas getTienda(){return tienda;}

    public void setTienda(Tiendas tienda){this.tienda = tienda;}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDia(){return date.getDayOfMonth();}

    public DayOfWeek getDay(){return date.getDayOfWeek();}

    /*public Dias(LocalDate date) {
        this.date = date;
    }*/
    public Set<Turnos> getTurnos() {
        return shifts;
    }
    public void setTurnos(Set<Turnos> turnos) {
        for(Turnos elTurno : turnos){
            elTurno.setDay(this);
        }
        this.shifts = turnos;
    }
    public void addTurno(Turnos elTurno){
        shifts.add(elTurno);
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void eliminarTurno(Turnos elTurno){
        shifts.remove(elTurno);
    }

    public void setDias(){
        for(Turnos elTurno : shifts){
            elTurno.setDay(this);
        }
    }


}