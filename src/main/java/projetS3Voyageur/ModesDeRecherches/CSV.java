package projetS3Voyageur.ModesDeRecherches;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;

public class CSV {

// à écrire dans le main

    public void afficheCSV() {

        Path orderPath = Paths.get("order.csv");    // Création d'un PATH

        String resultat = "resulat_obtenu";                              /// stocker le string du résultat des villes dans cette variable

        try {
            Files.write(orderPath, String.format(resultat).getBytes(), APPEND);        /////// Ecrie dans un fihier : APPEND empêche le xrite de supprimer le fichier à chaque fois il lui permet d'écrire à la suite
        } catch (IOException e) {
            System.out.println("ERREUR problème avec l'écriture dans le fichier");
            return;
        }


        // Pour lire le fichier et toutes ces lignes.
        List<String> lines;

        {
            try {

                lines = Files.readAllLines(orderPath);

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
    }
}
