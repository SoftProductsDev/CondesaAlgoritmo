package lalo;

import condeso.Condeso;
import horario.TipoTurno;
import horario.Turnos;
import tiendas.Tiendas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.IsoEra;
import java.util.*;

public class Test {
    public static void main(String [] args) {

      /*String filename = "disponibilidad2.txt";
        Set<Disponibilidad> horario = Parser.parse2(filename);
        for(Disponibilidad e: horario){
            e.Print();
            System.out.println();
        }*/



        /*String filename = "GMs.txt";
        ArrayList<Turnos> deEncargado = new ArrayList<>();
        Set<Condeso> GMs = Parser.parseGMs(filename, deEncargado, LocalDate.of(2018, Month.NOVEMBER, 1));
        for(Condeso elGM : GMs){
            elGM.printCondeso();
        }
        System.out.println(deEncargado.size());*/
        List<Tiendas> lasTiendas = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Tiendas laTienda = new Tiendas();
            laTienda.setNombre("Tienda" + i);
            lasTiendas.add(laTienda);
        }
        for(Tiendas laTienda : lasTiendas){
            
            laTienda.print();
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
