package tiendas;
import horario.HorarioMaster;
import horario.Plantillas;
public class Tiendas { // hola amigos
	private long Id;
	private Plantillas plantilla;
	private String nombre;
	private HorarioMaster master;

	public Tiendas(Plantillas plantilla, String nombre) {
		this.plantilla = plantilla;
		this.nombre = nombre;
	}

	public Tiendas() {
	}

	public Plantillas getPlantilla() {
		return plantilla;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
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

	@Override
	public String toString() {
		return "Tiendas{" +
				"Id=" + Id +
				", plantilla=" + plantilla +
				", nombre='" + nombre + '\'' +
				", master=" + master +
				'}';
	}
}
