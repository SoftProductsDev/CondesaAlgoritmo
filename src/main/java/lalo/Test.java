package lalo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class Test {
    public static void main(String [] args) {
        String filename = "disponibilidad2.txt";
        Set<Disponibilidad> horario = Parser.parse2(filename);
        for(Disponibilidad e: horario){
            e.Print();
            System.out.println();
        }


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
