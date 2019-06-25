package horario;

import java.util.Comparator;

public class CompareTurnos2 implements Comparator<Turnos> {


    @Override
    public int compare(Turnos o1, Turnos o2) {
        if(o1.getInicio() != o2.getInicio()) return  Integer.compare(o1.getInicio(), o2.getInicio());
        else{
          return o1.getShiftType().compareTo(o2.getShiftType());
        }
    }
}
