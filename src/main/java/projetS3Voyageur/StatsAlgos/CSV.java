package projetS3Voyageur.StatsAlgos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSV {

    /**
     * Génère ou régénère en écrasant l'ancienne version un fichier CSV avec comme
     * données, les données du tableau de String qui seront délimitées par le
     * séparateur.
     * 
     * @param donneeEnregistree {@code String[]} Données dont le fichier CSV doit
     *                          contenir
     * 
     * @param separateur        {@code String} L'élément séparant deux données du
     *                          fichier CSV
     * 
     * @param nomFichier        {@code File} Le fichier qui doit être généré ou
     *                          régénéré
     */
    public static void writeCSV(List<String[]> donneeEnregistree, String separateur, File nomFichier) {

        try (FileWriter writer = new FileWriter(nomFichier)) {

            for (String[] strings : donneeEnregistree) {

                for (int i = 0; i < strings.length; i++) {

                    writer.append(strings[i]);

                    if (i < (strings.length - 1)) {

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

    /**
     * Renvoie un tableau de String dont les cases sont les éléments contenus entre
     * les séparateurs dans le fichier CSV ciblé.
     * 
     * @param separateur {@code String} représente le séparateur des données à
     *                   récupérer du fichier CSV
     * 
     * @param nomFichier {@code File} Le fichier CSV ciblé par la lecture
     * 
     * @return {@code String[]} Retourne un tableau de String contenant les éléments
     *         du fichier ciblé.
     */
    public static List<String[]> readCSV(String separateur, File nomFichier) {

        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {

            List<String[]> list = new ArrayList<>();
            String line = "";

            while ((line = reader.readLine()) != null) {

                String[] array = line.split(separateur);
                list.add(array);
            }
            return list;
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }
}
