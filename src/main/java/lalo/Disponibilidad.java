package lalo;

public class Disponibilidad {
    private String name;
    private int[][] disponibilidad;

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
