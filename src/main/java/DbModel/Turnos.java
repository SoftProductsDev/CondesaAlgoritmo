package DbModel;

import horario.TipoTurno;
import javax.persistence.*;

@Entity
@Table(name = "turnos")
public class Turnos implements Comparable<Turnos> {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "elemental")
    private boolean elemental;

    @Column(name = "matutino")
    private boolean matutino;

    @Column(name = "ocupado")
    private boolean ocupado = false;

    @Column(name = "inicio")
    private int inicio;

    @Column(name = "fin")
    private int fin;

    @JoinColumn
    @OneToOne
    private Condeso condeso;

    @Column
    private TipoTurno tipoTurno;

    public Turnos(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isElemental() {
        return elemental;
    }

    public void setElemental(boolean elemental) {
        this.elemental = elemental;
    }

    public boolean isMatutino() {
        return matutino;
    }

    public void setMatutino(boolean matutino) {
        this.matutino = matutino;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public int getDuracion() {
        return fin-inicio;
    }

    public Condeso getCondeso() {
        return condeso;
    }

    public void setCondeso(Condeso condeso) {
        this.condeso = condeso;
    }

    public TipoTurno getTipoTurno() {
        return tipoTurno;
    }

    public void setTipoTurno(TipoTurno tipoTurno) {
        this.tipoTurno = tipoTurno;
    }

    @Override
    public int compareTo(Turnos o) {
        if(this.inicio > o.getInicio()){return 1;}
        else if (this.inicio == o.getInicio() && this.condeso == o.getCondeso()){return 0;}
        else{return -1;}
    }
}
