package horario;

import java.util.List;

public class Hora {
    private float promedio;
    private Dias elDia;
    private List<Turnos> colisiones;

    private Dias getElDia(){return elDia;}

    private void setElDia(Dias elDia){this.elDia = elDia;}



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
        //int
        int countNoOcupados = 0;
        boolean nivelUno = false;
        for(Turnos turno : colisiones){
            if(turno.isOcupado()){
                ocupados += turno.getCondeso().getLevel();
                if(turno.getCondeso().getLevel() == 1) nivelUno = true;}
            else countNoOcupados++;
        }

    }


}
