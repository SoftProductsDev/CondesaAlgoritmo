package horario;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private Set<Turnos> turnos;

    @Column(name = "date")
    private LocalDate date;

    private Tiendas tienda;
    private HashMap<Integer, Hora> horas;
    private float promedioMinimo;

    public Dias(){}

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDia(){return date.getDayOfMonth();}

    public DayOfWeek getDay(){return date.getDayOfWeek();}

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