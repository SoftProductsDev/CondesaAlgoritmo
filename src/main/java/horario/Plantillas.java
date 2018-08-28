package horario;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Id;

public class Plantillas {
	private long Id;

	private Set<Dias> dias;

	public Plantillas() {
		dias = new HashSet<Dias>();
	}

	public Set<Dias> getDias(){return dias; }

	public void setDias(Set<Dias> dias) {
		this.dias = dias;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public DbModel.Plantillas convertToDbModel()
	{
		DbModel.Plantillas result = new DbModel.Plantillas();
		result.setId(Id);
		dias.forEach((k) -> result.getDias().add(k.convertToDbModel()));
		return result;
	}
}
