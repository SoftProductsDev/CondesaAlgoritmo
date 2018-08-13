package DbModel;

import horario.Dias;
import java.util.Set;

public class Plantillas {
    private long id;
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
