package lalo;

import DbController.HibernateCrud;
import condeso.Condeso;
import horario.Dias;
import horario.TipoTurno;
import horario.Turnos;
import tiendas.Tiendas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.IsoEra;
import java.util.*;
import java.awt.EventQueue;
import javax.swing.JFileChooser;

public class Test {
    private static String result = null;
    public static void main(String [] args) throws InvocationTargetException, InterruptedException {
       // EventQueue.invokeAndWait(new Runnable() {
         //   @Override
           // public void run() {
                //String folder = System.getProperty("user.dir");
                 JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(fc);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    result = fc.getSelectedFile().getAbsolutePath();
            }
        //}});
        System.out.println(result);

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
        /*List<Tiendas> lasTiendas = new ArrayList<>();
        List<Tiendas> lasTiendas2 = new ArrayList<>();
        lasTiendas = HibernateCrud.GetAllTiendas();
        lasTiendas2 = HibernateCrud.GetAllTiendas();

        for(int i = 0; i < lasTiendas.size(); i++){
            System.out.println("Tienda " + i + " " +lasTiendas.get(i));
            System.out.println("Tienda " + i + " " +lasTiendas2.get(i));
        }*/
        /*for(int i = 0; i < 3; i++){
            Tiendas laTienda = new Tiendas();
            laTienda.setNombre("Tienda" + i);
            lasTiendas.add(laTienda);
        }*/
       // Dias elDia = new Dias(LocalDate.of(2018, Month.SEPTEMBER, 27), l);
       /* List<Integer> hola = new ArrayList<>();
        long prueba = 33L;
        hola.add((int)34L);
        hola.add((int) prueba);
        for(long pro : hola){
            System.out.println(pro);
        }*/







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
