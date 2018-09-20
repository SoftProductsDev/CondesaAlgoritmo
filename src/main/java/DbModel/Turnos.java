package DbModel;

import java.time.LocalDate;
import javax.persistence.*;
import tiendas.Tiendas;

@Entity
@Table(name = "turnos")
public class Turnos {
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

    @Column(name = "duracion")
    private int duracion;

    @ManyToOne
    private Condeso condeso;

    @Column
    private LocalDate fecha;

    @JoinColumn
    @ManyToOne
    private DbModel.Tiendas tienda;

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
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Condeso getCondeso() {
        return condeso;
    }

    public void setCondeso(Condeso condeso) {
        this.condeso = condeso;
    }
}
