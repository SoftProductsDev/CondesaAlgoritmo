package lalo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    public static void main(String [] args) {
        String filename = "disponibilidad.txt";
        int[][] horario = Parser.parse(filename);
        toString(horario);

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
