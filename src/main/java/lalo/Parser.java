package lalo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    public static int[][] parse(String fileName) {
        String line;

        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader buffer = new BufferedReader(reader);

            while ((line = buffer.readLine()) != null) {
                int i = ignore(line, ' ');
                if(i < line.length()){
                    line.
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

}
