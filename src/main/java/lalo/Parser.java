package lalo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    private static int[][] horario;
    /*public static void main(String[] args){
       horario = parse("disponibilidad.txt");
      // System.out.println(toString(horario));
    }*/
    public static int[][] parse(String fileName) {
        String line;
        String month = "1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13\t14\t15\t16\t17\t18\t19\t20\t21\t22\t23\t24\t25\t26\t27\t28";
        String number;
        int hour;
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
                 i = ignore(line, '\t');
                 for(int k = 0; k < disponibilidad[1].length; k++){
                 number = line.substring(i, (i = subString(line, i, '\t' )));
                 try{
                    hour = Integer.parseInt(number);
                 } catch(Exception e){
                     hour = 0;
                 }
                 disponibilidad[1][k] = hour;
                 i++;
                 }
                 line = buffer.readLine();
                    i = ignore(line,  '\t');
                    for(int k = 0; k < disponibilidad[2].length; k++){

                        number = line.substring(i, (i = subString(line, i, '\t' )));

                        try{
                            hour = Integer.parseInt(number);
                        } catch(Exception e){
                            hour = 0;
                        }
                        disponibilidad[2][k] = hour;
                        i++;
                    }
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

public static String toString(int[][] horario){
        String a = "";
        for(int i = 0; i < 2;i++){
            for(int j = 0; j < horario.length ;j++){
                a += horario[i][j];
            }
        }
        return a;
}

}
