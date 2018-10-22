package tiendas;
import horario.HorarioMaster;
import horario.Plantillas;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Tiendas {
	private long Id;
	private Plantillas plantilla;
	private String nombre;
	private HorarioMaster master;
	private Boolean selected;
	private List<LocalDate> diasDeCierre = new ArrayList<>();

	public Tiendas(Plantillas plantilla, String nombre) {
		this.plantilla = plantilla;
		this.nombre = nombre;
	}

	public Tiendas() {
		selected = false;
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

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public BooleanProperty selected(){return new SimpleBooleanProperty(selected);
	}

	public List<LocalDate> getDiasDeCierre() {
		return diasDeCierre;
	}

	public void setDiasDeCierre(List<LocalDate> diasDeCierre) {
		this.diasDeCierre = diasDeCierre;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
