package projetS3Voyageur.StatsAlgos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigOrdinateur {

    public static void enregistreConfig(File f) {
        try {
            String commande = "";
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(f, "config.txt")));

            switch (System.getProperty("os.name")) {
            case "Windows 10":
                commande = "wmic cpu";
                break;
            default:
                commande = "cat/proc/cpuinfo";
            }
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(commande);
            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";

            while ((line = b.readLine()) != null) {
                writer.write(line + "\n");
            }

            writer.close();
            b.close();
        } catch (IOException | InterruptedException ioe) {
            System.out.print("Erreur : ");
            ioe.printStackTrace();
        }

    }
}