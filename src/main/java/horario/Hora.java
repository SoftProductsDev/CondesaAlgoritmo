package horario;

import java.util.ArrayList;
import java.util.List;

public class Hora {
    private float promedio;
    private Dias elDia;
    private List<Turnos> colisiones;
    private boolean nivelUno = false;

    private Dias getElDia(){return elDia;}

    private void setElDia(Dias elDia){this.elDia = elDia;}

    public Hora(Dias elDia, float promedio){
        this.elDia = elDia;
        this.promedio = promedio;
        colisiones = new ArrayList<>();
    }


    public void addTurno(Turnos elTurno){
        colisiones.add(elTurno);
    }

    public void setColisiones(List<Turnos> colisiones){
        this.colisiones = colisiones;
    }

    public float getPromedio(){
        return promedio;
    }


    public void eraseTurno(Turnos elTurno){
        colisiones.remove(elTurno);
    }

    public void change(Turnos elTurno){
        int ocupados = 0;
        int minimo = (int)(promedio*colisiones.size());
        int countNoOcupados = 0;
        for(Turnos turno : colisiones){
            if(turno.isOcupado()){
                ocupados += turno.getCondeso().getLevel();
                if(turno.getCondeso().getLevel() == 1) nivelUno = true;}
            else countNoOcupados++;
        }

        if(countNoOcupados != 0) {
            minimo = (minimo - ocupados + countNoOcupados - 1) / countNoOcupados;
            if (nivelUno && minimo <= 1) minimo++;

            for (Turnos turno : colisiones) {
                if (!turno.isOcupado()) turno.setMinimo(minimo);
            }
        }

    }


    public void change2(Turnos elTurno) {
        if(nivelUno && elTurno.getCondeso().getLevel() != 1){
            elTurno.setMinimo(2);
            return;
        }

    }


    public boolean checkUnos() {
        int max = colisiones.size()/2;
        int count = 0;
        for(Turnos elTurno : colisiones){
            if(elTurno.isOcupado() && elTurno.getCondeso().getLevel() == 1) count++;
        }
        if(count >= max)return false;
        return true;
    }
}
