package projetS3Voyageur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UltimeCSV {

    public static void writeCSV(List<String[]> thingsToWrite, String separateur, String nomFichier){
        try(FileWriter writer = new FileWriter(nomFichier)){
            for(String[] strings : thingsToWrite) {
                for(int i = 0; i < strings.length; i++){
                    writer.append(strings[i]);
                    if(i < strings.length-1){
                        writer.append(separateur);
                    }
                }
                writer.append(System.lineSeparator());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static List<String[]> readCSV(String separateur, String nomFichier){
        try(BufferedReader reader = new BufferedReader(new FileReader(nomFichier))){
            List<String[]> list = new ArrayList<>();
            String line = "";

            while((line = reader.readLine()) != null){
                String[] array = line.split(separateur);
                list.add(array);
            }

            return list;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
