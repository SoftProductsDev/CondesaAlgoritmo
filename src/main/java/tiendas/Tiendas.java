package tiendas;
import horario.HorarioMaster;
import horario.Plantillas;
public class Tiendas {
	private Plantillas plantilla;
	private String nombre;
	private HorarioMaster master;

	public Tiendas(Plantillas plantilla, String nombre) {
		this.plantilla = plantilla;
		this.nombre = nombre;
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
