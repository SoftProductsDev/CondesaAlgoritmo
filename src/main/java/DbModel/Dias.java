package DbModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Turnos> turnos;

    @Column(name = "date")
    private LocalDate date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
