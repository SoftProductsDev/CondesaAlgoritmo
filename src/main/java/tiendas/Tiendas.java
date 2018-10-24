package tiendas;
import horario.HorarioMaster;
import horario.Plantillas;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "tiendas")
public class Tiendas {
	@javax.persistence.Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@JoinColumn(name = "plantilla")
	@OneToOne
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
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

	@JoinColumn
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Plantillas> plantillasAnteriores;

	@Transient
	private Boolean selected;
	@Transient
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
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public LocalDate getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(LocalDate fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public List<Plantillas> getPlantillasAnteriores() {
		return plantillasAnteriores;
	}

	public void setPlantillasAnteriores(List<Plantillas> plantillasAnteriores) {
		this.plantillasAnteriores = plantillasAnteriores;
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
