package projetS3Voyageur;

import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class App {


    public static void main(String[] args) {

        Pays pays = new Pays(12);

        Voyageur v = new Voyageur(pays, pays.getOneVille(0));

        System.out.println("Nombre de possibilitées : " + Util.factorielle(pays.getNbVille()-1));
        System.out.println("Execution en cours (Cela peut prendre plusieurs minutes [voir heures])...");

        long startTime = System.nanoTime();

        Parcours p = v.recherche();

        long endTime = System.nanoTime();

        System.out.println(p);
        System.out.println("Temps passé : " + (endTime - startTime)/1000000 + "ms");

    }
}
