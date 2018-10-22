package lalo;

import horario.TipoTurno;
import horario.Turnos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

public class Test {
    public static void main(String [] args) {

     /* String filename = "disponibilidad2.txt";
        Set<Disponibilidad> horario = Parser.parse2(filename);
        for(Disponibilidad e: horario){
            e.Print();
            System.out.println();
        }*/

        String filename = "GMs.txt";
        ArrayList<Turnos> deEncargado = new ArrayList<>();
        Set<GM> GMs = Parser.parseGMs(filename, deEncargado, LocalDate.of(2018, Month.NOVEMBER, 1));
        for(GM elGM : GMs){
            elGM.print();
        }
        System.out.println(deEncargado.size());







    }


    private static void toString(int[][] horario){
        for(int i = 0; i < horario.length; i++){
            for(int j = 0; j < horario[i].length; j++){
                System.out.print(horario[i][j] + ", ");
            }
          System.out.println();
        }

    }
}
