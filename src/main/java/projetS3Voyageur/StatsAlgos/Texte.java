package projetS3Voyageur.StatsAlgos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Texte {

    /**
     * Ecrie dans un fichier texte (préalablement choisi) le texte mit en paramètre
     * 
     * @param fichier {@code File} Fichier qu'il faut crée/choisir
     * @param texte   {@code String} Texte qu'il faut écrire
     */
    public static void ecrire(File fichier, String texte) {
        try (FileWriter writer = new FileWriter(fichier)) {
            writer.write(texte);
            writer.close();
        } catch (IOException ioe) {
            System.err.println("Erreur lors de la création/choix du fichier");
            ioe.printStackTrace();
        }
    }
}