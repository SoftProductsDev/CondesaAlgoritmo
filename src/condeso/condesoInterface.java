package condeso;
import java.util.Set;

import horario.HorarioEntrega;
import horario.HorarioMaster;
import horario.HorarioPersonal;

public interface CondesoInterface {
	  public boolean fijos = false;
	  public int level = 0;
	  public int priorityValue = 0;
	  public boolean manana = false;
	  public boolean caja = false;
	  public int antiguedad = 0;
	  public int diasSeguidos = 0;
	  public int finesLibres = 0;
	  public HorarioEntrega entrega = null;
	  public HorarioMaster master = null;
	  public HorarioPersonal personal = null;
	  public Set<?> dondePuedeTrabajar = null;
	  public Contrato contrato = null;
	  public enum Contrato {
	  }	  	  
}
