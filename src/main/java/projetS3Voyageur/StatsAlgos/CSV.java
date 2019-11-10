package projetS3Voyageur.StatsAlgos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CSV {

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
    static void writeCSV(List<String[]> donneeEnregistree, String separateur, File nomFichier) {
        // TODO: est devenue static à cause des tests
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
            System.err.println("Un problème est survenu lors de l'écriture du fichier CSV :");
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
    static List<String[]> readCSV(String separateur, File nomFichier) {

        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {

            List<String[]> list = new ArrayList<>();
            String line = "";

            while ((line = reader.readLine()) != null) {

                String[] array = line.split(separateur);
                list.add(array);
            }
            return list;
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier CSV donné en paramètre (pour la lecture) n'a pas pu être trouvé :");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.err.println("Un problème est survenu lors de la lecture du fichier CSV :");
            e.printStackTrace();
            return null;
        }
    }
}
