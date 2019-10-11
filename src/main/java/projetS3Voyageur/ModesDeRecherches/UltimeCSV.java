package projetS3Voyageur.ModesDeRecherches;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UltimeCSV {

    //ceci est le meilleur lecteur CSV


    public static void writeCSV(List<String[]> thingsToWrite, String separateur, String nomFichier){

        try (FileWriter writer = new FileWriter(nomFichier)){

            for (String[] strings : thingsToWrite) {

                for (int i = 0; i < strings.length; i++) {

                    writer.append(strings[i]);

                    if(i < (strings.length-1)) {

                        writer.append(separateur);
                    }
                }
                writer.append(System.lineSeparator());
            }
            writer.flush();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // choix du séparateur
    public static List<String[]> readCSV(String separateur, String nomFichier){

        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))){

            List<String[]> list = new ArrayList<>();
            String line = "";

            while((line = reader.readLine()) != null){

                String[] array = line.split(separateur);
                list.add(array);
            }
            return list;
        }
        catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }



    public static void main(String[] args) {

        List<String> liste = new ArrayList<String>();

        liste.add("Paris");
        liste.add("Marseille");
        liste.add("Bordeaux");

      /*  writeCSV(liste, ";", "ville.csv");*/
        readCSV(";", "ville.csv");

    }
}
