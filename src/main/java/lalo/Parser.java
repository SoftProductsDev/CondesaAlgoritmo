package lalo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import DbController.HibernateCrud;
import DbModel.Condeso;
import DbModel.HorarioEntrega;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import horario.Turnos;

public class Parser {



    private static List<Condeso> foundCondesos;

    public static List<Condeso> getFoundCondesos() {
        return foundCondesos;
    }

    public static void setFoundCondesos(List<Condeso> foundCondesos) {
        Parser.foundCondesos = foundCondesos;
    }

    public static int[][] parse(String fileName) {
        String line;
        String month = "1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13\t14\t15\t16\t17  \t18\t19\t20\t21\t22\t23\t24\t25\t26\t27\t28";
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

private static void parseMaxMin(String line, Disponibilidad laDisponibilidad, int posicion){
        String number;
        int num;
     number = line.substring(posicion , (posicion = subString(line, posicion, '\t')));
     try{
         num = Integer.parseInt(number);
     }catch (Exception e){
         num = 0;
     }
     laDisponibilidad.setMin(num);
     posicion++;
     number = line.substring(posicion, (posicion = subString(line, posicion, '\t')));
     try{
         num =Integer.parseInt(number);
     }catch(Exception e){
         num = 0;
     }
     laDisponibilidad.setMax(num);

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

private static int parseTime(int [][] disponibilidad, String line, int a, int start){
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
return i;
}

private static int ignore(String input, char toIgnore){
  int i = 0;
        while(input.charAt(i) == toIgnore){
            i++;
        }
        return i;
}

private static int ignore(String input, char toIgnore, int from){
    int i = from;
    while(input.charAt(i) == toIgnore){
        i++;
    }
    return i;
    }

private static int subString(String input, int i, char last){
        int j = i /*+1*/;//por si se caga algo
        while(j < input.length() && input.charAt(j) != last){
            j++;
        }
        if(j < input.length())
        return j;
        else return input.length();
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
int Id;
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
              j = parseTime(disponibilidad, line, 0, ++i) + 1;
              parseMaxMin(line, condeso, j);

              line = buffer.readLine();

              //parser del Id de condeso, para que funcione descomentar y borrar la función parseTime justo debajo de la parte comentada
                j = ignore(line, '\t');
              i = getPosition(line, '\t', j);
              Id = Integer.parseInt(line.substring(j, i));
              condeso.setId(Id);
              parseTime(disponibilidad, line, 1, ++i);

              //parseTime(disponibilidad, line, 1);
              condeso.setDisponibilidad(disponibilidad);
              Disp.add(condeso);
              buffer.readLine();
              /*Condeso DbCondeso = HibernateCrud.findCondesoId(666);
              if(DbCondeso != null){
                  HorarioEntrega entrega = new HorarioEntrega();
                  entrega.setMax(666);
                  entrega.setMin(666);
                  entrega.setMes(LocalDate.now());
                  entrega.setDisponibilidad(disponibilidad);
                  DbCondeso.setEntrega(entrega);
                  foundCondesos.add(DbCondeso);
              }else{
                  System.out.print("WARNING: Condeso not found!");
              }*/
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


public static Set<GM> parseGMs(String filename, ArrayList<Turnos> losTurnos , LocalDate date){

String line;
String tienda;
String mes;
Month month;
Set<GM> paraRegresar = new HashSet<GM>();



HashMap<Integer, GM> GMs = new HashMap<Integer, GM>();
ArrayList<Integer> IDs = new ArrayList<>();

try{
    FileReader reader = new FileReader(filename);
    BufferedReader buffer = new BufferedReader(reader);
    while((line=buffer.readLine()) != null ){
      int i = ignore(line, '\t');
      int j = subString(line, i, '\t');
      int idTienda;
      tienda = line.substring(i, j);
      i = ignore(line, '\t', j);
      j = subString(line, i, '\t');
      try{
      idTienda = Integer.parseInt(line.substring(i, j));}
      catch (Exception e){
          System.out.println("error al leer el Id de tienda");
          return null;
      }

      line = buffer.readLine();
      i = ignore(line, '\t');
      j = subString(line, i, '\t');
      mes = line.substring(i, j);
      mes = mes.toUpperCase();
      month = Month.valueOf(mes);
      buffer.readLine();
      buffer.readLine();
      parseTurnosGMs(buffer.readLine(), buffer.readLine(), buffer.readLine(), buffer.readLine(), date,  GMs,
              IDs, idTienda, losTurnos);
      parseTurnosGMs(buffer.readLine(), buffer.readLine(), buffer.readLine(), buffer.readLine(), date,  GMs,
                IDs, idTienda, losTurnos);
      buffer.readLine();
      buffer.readLine();
      buffer.readLine();
        /*while(useless(line)){
          line = buffer.readLine();
        }*/

    }
buffer.close();
for(int id : IDs){
    paraRegresar.add(GMs.get(id));
}
return paraRegresar;

}catch (FileNotFoundException ex){
    System.out.println( "Unable to open file '" +
            filename + "'");
} catch (IOException e){
    e.printStackTrace();
}


    return null;


}

private static boolean useless(String line){
       System.out.println("useless");
     for(int i = 0; i < 5; i++){

         if(line.charAt(i) != '\t') return false;
     }
     return true;
}


private static void parseTurnosGMs(String inicio, String fin, String GM, String ID, LocalDate mes, HashMap<Integer, GM> GMs ,
                            ArrayList<Integer> IDs, int idTienda, ArrayList<Turnos> losTurnos){

        int length = mes.lengthOfMonth();
        int paraInicio = 0;
        int paraFin = 0;
        int paraGM = 0;
        int paraId = 0;
        int begin = 0;
        int end;
        int Id;
        String Abrev;
        boolean next = false;
        GM elGM;

        // String anfang;
        // String ende;

        paraInicio = ignore(inicio, '\t');
        paraFin = ignore(fin, '\t');
        paraGM = ignore(GM, '\t');
        paraId = ignore(ID, '\t');
    paraGM = subString(GM, paraGM, '\t')+1;
    paraId = subString(ID, paraId, '\t')+1;

        for(int i = 0; i < length; i++){
            try{
                begin = Integer.parseInt(inicio.substring(paraInicio, (paraInicio = subString(inicio, paraInicio, '\t' ))));
                next = true;
            } catch(Exception e){
               paraInicio++;
               paraFin = subString(fin, paraFin, '\t') + 1;
               paraId = subString(ID, paraId, '\t') + 1;
               paraGM = subString(GM , paraGM, '\t') + 1;
                next = false;
            }
            if(next){
               try{
                   end = Integer.parseInt(fin.substring(paraFin, (paraFin = subString(fin, paraFin, '\t'))));
               }catch(Exception e){
                   throw new RuntimeException("error parsing second number");
               }
               try{
                   Id = Integer.parseInt(ID.substring(paraId, (paraId = subString(ID, paraId, '\t'))));
               }catch (Exception e){
                   Id = 0;
               }

               Abrev = GM.substring(paraGM, (paraGM = subString(GM, paraGM, '\t')));
               elGM = GMs.get(Id);
               if(Abrev.charAt(0) == '#'){
                 losTurnos.add(new Turnos(idTienda, begin, end, LocalDate.of(mes.getYear(), mes.getMonth(), i+1)));
               }else{
               if(elGM == null){
                   elGM = new GM(Id, Abrev);
                   IDs.add(Id);
                   GMs.put(Id, elGM);
               }
               elGM.addTurno(new Turnos(idTienda, begin, end, LocalDate.of(mes.getYear(), mes.getMonth(), i+1)));
            }
            paraInicio++;
            paraFin++;
            paraId++;
            paraGM++;
            }

        }
}


}
