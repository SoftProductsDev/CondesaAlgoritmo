package DbModel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tiendas")
public class Tiendas {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @JoinColumn
    @ManyToOne
    private Plantillas plantilla;

    @Column(name = "nombre")
    private String nombre;

    @JoinColumn
    @ManyToOne
    private HorarioMaster master;

    public Tiendas(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Plantillas getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(Plantillas plantilla) {
        this.plantilla = plantilla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HorarioMaster getMaster() {
        return master;
    }

    public void setMaster(HorarioMaster master) {
        this.master = master;
    }
}
