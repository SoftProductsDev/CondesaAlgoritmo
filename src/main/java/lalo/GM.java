package lalo;

import horario.Turnos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GM {
    private String elGM;
    private List<Turnos> susTurnos;
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getElGM() {
        return elGM;
    }

    public void setElGM(String elGM) {
        this.elGM = elGM;
    }

    public GM(int id, String elGM){
        this.elGM = elGM;
        Id = id;
        susTurnos = new ArrayList<Turnos>();
   }

   public void addTurno(Turnos elTurno){
        susTurnos.add(elTurno);
   }

   public List<Turnos> getSusTurnos(){
        return susTurnos;
   }

   public void print(){
        System.out.println(elGM + ", " + Id);
        System.out.println(susTurnos.size());
        System.out.println();
   }
}
