package lalo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;

public class Parser {

    public static int[][] parse(String fileName) {
        String line;
        String month = "1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13\t14\t15\t16\t17\t18\t19\t20\t21\t22\t23\t24\t25\t26\t27\t28";
        int[][] disponibilidad;
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader buffer = new BufferedReader(reader);

            while ((line = buffer.readLine()) != null) {
                if(line.contains(month)){
                    int i = ignore(line, '\t');
                    i += 75;
                    int j = 0;
                    while(line.charAt(i) != '\t'){
                        i+=3;
                        j++;
                    }
                 disponibilidad = new int[3][28+j];

                    for(int k = 0; k < disponibilidad[0].length; k++){
                        disponibilidad[0][k] = k+1;
                    }

                 line = buffer.readLine();

                 parseTime(disponibilidad, line, 1);

                 line = buffer.readLine();

                 parseTime(disponibilidad, line, 2);

                    buffer.close();
                    return disponibilidad;


                }

            }

            buffer.close();

        } catch (FileNotFoundException ex) {

            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return null;
    }

private static void parseTime(int[][] disponibilidad, String line, int a){
        int i;
        String number;
        int hour;

    i = ignore(line, '\t');
    for(int k = 0; k < disponibilidad[a].length; k++){
        number = line.substring(i, (i = subString(line, i, '\t' )));
        try{
            hour = Integer.parseInt(number);
        } catch(Exception e){
            hour = 0;
        }
        disponibilidad[a][k] = hour;
        i++;
    }
}

private static void parseTime(int [][] disponibilidad, String line, int a, int start){
    int i = start;
    String number;
    int hour;
    for(int k = 0; k < disponibilidad[a].length; k++){
        number = line.substring(i, (i = subString(line, i, '\t' )));
        try{
            hour = Integer.parseInt(number);
        } catch(Exception e){
            hour = 0;
        }
        disponibilidad[a][k] = hour;
        i++;
    }

}

private static int ignore(String input, char toIgnore){
  int i = 0;
        while(input.charAt(i) == toIgnore){
            i++;
        }
        return i;
}

private static int subString(String input, int i, char last){
        int j = i + 1;
        while(j < input.length() && input.charAt(j) != last){
            j++;
        }
        if(j < input.length())
        return j;
        else return input.length()-1;
}

private static int getPosition(String line, char last, int i){

        while(line.charAt(i) != last){
            i++;
        }

        return i;
}

public static String toString(int[][] horario){
        String a = "";
        for(int i = 0; i < 2;i++){
            for(int j = 0; j < horario.length ;j++){
                a += horario[i][j];
            }
        }
        return a;
}

public static Set<Disponibilidad> parse2(String filename){
Set<Disponibilidad> Disp = new HashSet<Disponibilidad>();

String month = "1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13\t14\t15\t16\t17\t18\t19\t20\t21\t22\t23\t24\t25\t26\t27\t28";
String line;
String name;
Disponibilidad condeso;
int[][] disponibilidad;
try{
    FileReader reader = new FileReader(filename);
    BufferedReader buffer = new BufferedReader(reader);

    while((line=buffer.readLine()) != null ){
        if(line.contains(month)){
           int i = ignore(line, '\t');
           i += 75;
           int dias = 28;
            while(line.charAt(i) != '\t'){
                i+=3;
                dias++;
            }
            buffer.readLine();
            while((line = buffer.readLine()) != null){
              int j = ignore(line, '\t');
              i = getPosition(line, '\t', j);
              name = line.substring(j, i);
              condeso = new Disponibilidad(name);
              disponibilidad = new int[2][dias];
              parseTime(disponibilidad, line, 0, ++i);
              line = buffer.readLine();
              parseTime(disponibilidad, line, 1);
              condeso.setDisponibilidad(disponibilidad);
              Disp.add(condeso);
              buffer.readLine();
            }
            buffer.close();
            return Disp;
        }
    }
    buffer.close();
    return null;
} catch (FileNotFoundException ex){
    System.out.println( "Unable to open file '" +
            filename + "'");
} catch (IOException e){
    e.printStackTrace();
}


    return null;
}

}
