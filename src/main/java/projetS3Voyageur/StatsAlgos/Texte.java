package projetS3Voyageur.StatsAlgos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Texte{
    public static void ecrire(File fichier, String texte) {
        try (FileWriter writer = new FileWriter(fichier)) {
            writer.write(texte);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}