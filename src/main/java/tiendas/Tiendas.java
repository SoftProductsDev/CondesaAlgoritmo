package tiendas;
import DbController.HibernateCrud;
import condeso.Condeso;
import horario.HorarioMaster;
import horario.Plantillas;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tiendas")
public class Tiendas {
	@Id
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

	@Column(nullable = true)
	private String color;

	@JoinColumn
	@OneToOne
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private HorarioMaster master;

	@JoinColumn
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@OrderColumn(name = "columnName")
	private List<Plantillas> plantillasAnteriores;

	@Transient
	private Boolean selected;
	@Transient
	private List<LocalDate> diasDeCierre = new ArrayList<>();

	public Tiendas(Plantillas plantilla, String nombre) {
		this.plantilla = plantilla;
		this.nombre = nombre;
		this.master = new HorarioMaster();
	}

	public Tiendas() {
		selected = false;
		this.master = new HorarioMaster();
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return nombre;
	}

	public void print(){
		System.out.println(nombre);
		System.out.println(master);
		System.out.println(master.getMes());
	}

	@PreRemove
	public void removeTiendasFromCondesos() {
		List<Condeso> condesos = HibernateCrud.GetAllCondesos();
		for (Condeso c : condesos) {
			c.getDondePuedeTrabajar().remove(this);
			HibernateCrud.UpdateCondeso(c);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Tiendas tiendas = (Tiendas) o;
		return getId() == tiendas.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
