package horario;

import java.util.Comparator;

public class CompareTurnos implements Comparator<Turnos> {
    @Override
    public int compare(Turnos o1, Turnos o2) {
        if(o1.deEncargado()){
          if(o2.deEncargado()) return compare2(o1, o2);
          else return -1;
        }else{
         if(o2.deEncargado()) return 1;
         else return compare2(o1, o2);
        }


    }

    private int compare2(Turnos o1, Turnos o2){
        int i = o1.getTipoTurno().compareTo(o2.getTipoTurno());

        if(i == 0){
            return Integer.compare(o1.getInicio(), o2.getInicio());
        }else if(i > 0) return 1;
        else return -1;
    }

}
