package horario;

import java.util.Set;

import condeso.Contrato;

public class Turnos {
	private boolean elemental;
	private int intervalo;
	private Contrato contrato;

	public Turnos(boolean elemental, int intervalo, Contrato contrato) {
		this.elemental = elemental;
		this.intervalo = intervalo;
		this.contrato = contrato;
	}

	public boolean isElemental() {
		return elemental;
	}

	public void setElemental(boolean elemental) {
		this.elemental = elemental;
	}

	public int getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
}
