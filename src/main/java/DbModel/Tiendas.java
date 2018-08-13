package DbModel;

import horario.HorarioMaster;
import horario.Plantillas;

public class Tiendas {
    private long id;
    private Plantillas plantilla;
    private String nombre;
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
