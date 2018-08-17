package DbModel;

import condeso.Contrato;
import condeso.TipoEmpleado;
import horario.HorarioEntrega;
import horario.HorarioMaster;
import horario.HorarioPersonal;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.MapKeyType;
import org.hibernate.annotations.Type;
import tiendas.Tiendas;

import java.util.Date;
import java.util.List;

/**
 * Created by javier on 10/08/2018.
 */

@Entity
@Table(name = "condeso")
public class Condeso {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private long id;
  @Column
  private TipoEmpleado tipo;
  @Column
  private String nombre;
  @Column
  private boolean fijos;
  @Column
  private int level;
  @Column
  private boolean manana;
  @Column
  private boolean caja;
  @Column
  private Date antiguedad;

  @ElementCollection
  @MapKey(name = "date")
  @CollectionTable
  private Map<Date, Dias> entrega;
  /*
  @JoinColumn(name="horarioentrega")
  @ManyToOne
  private HorarioMaster master;
  @JoinColumn(name="horarioentrega")
  @OneToOne
  private HorarioPersonal personal;

  @ElementCollection(fetch= FetchType.LAZY)
  @CollectionTable(name = "tiendas")
  @IndexColumn(name="tiendas_index")
  private List<Tiendas> dondePuedeTrabajar;*/
  @Column
  private Contrato contrato;

  public Condeso() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public TipoEmpleado getTipo() {
    return tipo;
  }

  public void setTipo(TipoEmpleado tipo) {
    this.tipo = tipo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public boolean isFijos() {
    return fijos;
  }

  public void setFijos(boolean fijos) {
    this.fijos = fijos;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public boolean isManana() {
    return manana;
  }

  public void setManana(boolean manana) {
    this.manana = manana;
  }

  public boolean isCaja() {
    return caja;
  }

  public void setCaja(boolean caja) {
    this.caja = caja;
  }

  public Date getAntiguedad() {
    return antiguedad;
  }

  public void setAntiguedad(Date antiguedad) {
    this.antiguedad = antiguedad;
  }

  public Map<Date, Dias> getEntrega() {
    return entrega;
  }

  public void setEntrega(Map entrega) {
    this.entrega = entrega;
  }
/*
  public HorarioMaster getMaster() {
    return master;
  }

  public void setMaster(HorarioMaster master) {
    this.master = master;
  }

  public HorarioPersonal getPersonal() {
    return personal;
  }

  public void setPersonal(HorarioPersonal personal) {
    this.personal = personal;
  }

  public List<Tiendas> getDondePuedeTrabajar() {
    return dondePuedeTrabajar;
  }

  public void setDondePuedeTrabajar(List<Tiendas> dondePuedeTrabajar) {
    this.dondePuedeTrabajar = dondePuedeTrabajar;
  }
*/
  public Contrato getContrato() {
    return contrato;
  }

  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
}
