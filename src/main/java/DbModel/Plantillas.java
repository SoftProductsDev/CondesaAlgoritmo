package DbModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "plantillas")
public class Plantillas {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    /*An instance of the contained entity class cannot belong
    to more than one instance of the collection.
     An instance of the contained entity class cannot appear
     at more than one value of the collection index.*/
    @ElementCollection
    @CollectionTable
    private Set<Dias> dias;

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

    public Set<Dias> getDias() {
        return dias;
    }

    public void setDias(Set<Dias> dias) {
        this.dias = dias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
