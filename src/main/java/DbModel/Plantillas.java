package DbModel;

import horario.Dias;
import java.util.Set;

public class Plantillas {
    private long id;

    /*An instance of the contained entity class cannot belong
    to more than one instance of the collection.
     An instance of the contained entity class cannot appear
     at more than one value of the collection index.*/
    private Set<Dias> dias;

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
}
