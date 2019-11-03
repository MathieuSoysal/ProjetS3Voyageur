package projetS3Voyageur.StatsAlgos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigOrdinateur {

    /**
     * Enregistre la configuration CPU de l'ordinateur dans un fichier donné en
     * paramètre
     * 
     * @param fichier {@code File} Fichier où les données doivent être enregistrées
     */
    public static void enregistreConfig(File fichier) {
        try {
            String commande = "";
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fichier, "config.txt")));

            switch (System.getProperty("os.name")) {
            case "Windows 10":
                commande = "wmic cpu";
                break;
            default:
                commande = "cat/proc/cpuinfo";
            }
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(commande);
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                System.err.println("Erreur lors de l'exécution de la commande :");
                e.printStackTrace();
            }
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";

            while ((line = b.readLine()) != null) {
                writer.write(line + "\n");
            }

            writer.close();
            b.close();
        } catch (IOException ioe) {
            System.err.print("Erreur lors de l'enregistrement des configurations de l'ordinateur : ");
            ioe.printStackTrace();
        }

    }
}