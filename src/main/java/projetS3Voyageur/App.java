package projetS3Voyageur;

import projetS3Voyageur.MathieuS.*;
import projetS3Voyageur.MatthiasD.*;

public class App {

    public static void main(String[] args) {
        long startTime;
        long endTime;

        long timeMS;
        long timeMD;

        long tempsMoyenMS = 0;
        long tempsMoyenMD = 0;

        int pointMS = 0;
        int pointMD = 0;

        int fin = 15;
        for (int i = 0; i < fin; i++) {
            startTime = System.currentTimeMillis();
            // #region MathieuS
            Pays p = new Pays(10);
            BrutForce b = new BrutForce(p);
            b.recherche();
            // #endregion MathieuS
            endTime = System.currentTimeMillis();
            timeMS = endTime - startTime;
            tempsMoyenMS += timeMS / fin;

            startTime = System.currentTimeMillis();
            // #region MatthiasD
            Graphe g = new Graphe(50, 10);
            Resolveur r = new Resolveur(g, g.getOneSommet(0).getVille());
            r.bruteForce();
            // #endregion MatthiasD
            endTime = System.currentTimeMillis();
            timeMD = endTime - startTime;
            tempsMoyenMD += timeMD / fin;

            pointMD += (timeMD < timeMS) ? 1 : 0;
            pointMS += (timeMS < timeMD) ? 1 : 0;

            // System.out.println((((double)i)/((double)fin))*100);
            int charge = (int) ((((double) i) / ((double) fin)) * 100);
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n         [");
            for (int j = 0; j < 100; j++) {
                System.out.print((j <= charge) ? "#" : ".");
            }
            System.out.print("] \n \n \n \n");
        }

        System.out.println("\n   Branche Matthias Delon :\n" + "Points obtenue : " + pointMD
                + "\n Temps moyen de recherche : " + tempsMoyenMD);
        System.out.println("\n   Branche Mathieu Soysal :\n" + "Points obtenue : " + pointMS
                + "\n Temps moyen de recherche : " + tempsMoyenMS);

        System.out.println("\n L'algo le plus rapide a étais celui de la branche :"
                + ((pointMS > pointMD) ? "Mathieu Soysal" : "Matthias Delon "));

    }

}
