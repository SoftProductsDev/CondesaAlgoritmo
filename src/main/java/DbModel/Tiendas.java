package DbModel;


import javax.persistence.*;
import java.time.LocalDate;
import org.hibernate.annotations.Cascade;

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

    @Column(name = "manager")
    private String manager;

    @Column(name = "fechaApertura")
    private LocalDate fechaApertura;

    @JoinColumn
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
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


    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    @Override
    public String toString() {
        return  nombre;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
}
