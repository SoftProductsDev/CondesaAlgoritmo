package lalo;

import condeso.AvailableDay;

import java.util.Collections;
import java.util.List;

public class Disponibilidad {
    private String name;
    private int Id;
    private Integer[][] disponibilidad;
    private List<AvailableDay> availableDays ;
    private int max;
    private int min;

    public List<AvailableDay> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<AvailableDay> availableDays) {
        this.availableDays = availableDays;
    }

    public void setMax(int max){
        this.max = max;
    }

    public void setId(int Id){this.Id = Id;}

    public int getId(){return Id;}

    public int getMax(){
        return max;
    }

    public void setMin(int min){
        this.min = min;
    }

    public int getMin(){
        return min;
    }

    public Disponibilidad(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDisponibilidad(Integer[][] disponibilidad){
        this.disponibilidad = disponibilidad;
    }

    public Integer[][] getDisponibilidad(){
        return disponibilidad;
    }

    public void Print(){
        System.out.println(name + " Max: " + max + ", Min: " + min);
        for(int i = 0; i < disponibilidad.length; i++){
            for(int j = 0; j < disponibilidad[i].length; j++){
                System.out.print(disponibilidad[i][j] + ", ");
            }
            System.out.println();
        }
    }


    public Integer[][] getAvailableDaysAsArray() {
        if(availableDays == null)
        {
            return null;
        }
        Integer[][] result = new Integer[2][31];
        for (AvailableDay day:availableDays
             ) {
                result[day.CalendarDay][0] = day.getStart();
                result[day.CalendarDay][1] = day.getEnd();
        }
        return result;
    }
}
