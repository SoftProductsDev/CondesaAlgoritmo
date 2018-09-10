package lalo;

public class Disponibilidad {
    private String name;
    private int[][] disponibilidad;
    private int max;
    private int min;

    public void setMax(int max){
        this.max = max;
    }

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

    public void setDisponibilidad(int[][] disponibilidad){
        this.disponibilidad = disponibilidad;
    }

    public int[][] getDisponibilidad(){
        return disponibilidad;
    }

}
