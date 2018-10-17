package horario;

import DbModel.Condeso;

import java.util.Comparator;

public class CompareTurnos implements Comparator<Turnos> {
    @Override
    public int compare(Turnos o1, Turnos o2) {
        return Integer.compare(o1.getInicio(), o2.getInicio());
    }
}
