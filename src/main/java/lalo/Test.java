package lalo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;
import java.util.Date;


public class Test {
    public static void main(String[] args) {
        /*String filename = "disponibilidad2.txt";
        Set<Disponibilidad> horario = Parser.parse2(filename);
        for(Disponibilidad e: horario){
            e.Print();
            System.out.println();*/
        Calendar cal = Calendar.getInstance();
        cal.set(2018, 8, 23);
        System.out.println(cal.get(Calendar.DAY_OF_WEEK) + ", " +Calendar.SEPTEMBER);


    }




    private static void toString(int[][] horario) {
        for (int i = 0; i < horario.length; i++) {
            for (int j = 0; j < horario[i].length; j++) {
                System.out.print(horario[i][j] + ", ");
            }
            System.out.println();
        }

    }

    private static int fecha(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        return cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

}