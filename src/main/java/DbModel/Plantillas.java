package DbModel;

import java.util.List;
import javax.persistence.*;
import java.util.Set;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "plantillas")
public class Plantillas {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @JoinColumn
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Dias> dias;

    @Column(name = "nombre")
    private  String nombre;

    public Plantillas(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Dias> getDias() {
        return dias;
    }

    public void setDias(List<Dias> dias) {
        this.dias = dias;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
