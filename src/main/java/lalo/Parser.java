package lalo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    public static int[][] parse(String fileName) {
        String line;
        String month = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28";
        String number;
        int hour;
        int[][] disponibilidad;
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader buffer = new BufferedReader(reader);

            while ((line = buffer.readLine()) != null) {
                if(line.contains(month)){
                    int i = ignore(line, ' ');
                    i += 75;
                    int j = 0;
                    while(line.charAt(i) != ' '){
                        i+=3;
                        j++;
                    }
                 disponibilidad = new int[3][28+j];

                    for(int k = 0; k < disponibilidad[0].length){
                        disponibilidad[0][k] = k+1;
                    }

                 line = buffer.readLine();
                 i = 0;
                 i = ignore(line, ' ');
                 for(int k = 0; k < disponibilidad[1].length; k++){
                 number = line.substring(i, (i = subString(line, i, ' ' )));
                 try{
                    hour = Integer.parseInt(number);
                 } catch(Exception e){
                     hour = 0;
                 }
                 disponibilidad[1][k] = hour;
                 }
                 line = buffer.readLine();
                    i = 0;
                    i = ignore(line, ' ');
                    for(int k = 0; k < disponibilidad[2].length; k++){
                        number = line.substring(i, (i = subString(line, i, ' ' )));
                        try{
                            hour = Integer.parseInt(number);
                        } catch(Exception e){
                            hour = 0;
                        }
                        disponibilidad[2][k] = hour;
                    }


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
        while(input.charAt(j) != last){
            int j = j++;
        }
        return j;
}

}
