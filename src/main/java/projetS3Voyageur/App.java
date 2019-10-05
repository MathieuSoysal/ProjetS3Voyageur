package projetS3Voyageur;
public class App {

//TODO: Attention ce programme est à faire en dernier lorsque tout les programmes ont validé les tests

    public static void main(String[] args) {
        long startTime; // variable pour récupéré l'heure (en miniseconde) à la quelle on commence le
                        // probramme
        long endTime;// variable pour récupéré l'heure (en miniseconde) à la quelle on arrête le
                     // probramme

        long timeMS; // variable permetant de stocké le temps mit pour l'exécution d'un algo
        long timeMD; // variable permetant de stocké le temps mit pour l'exécution d'un algo

        long tempsMoyenMS = 0;
        long tempsMoyenMD = 0;

        int pointMS = 0; // nombre de fois où l'algo a étais le plus rapide (au sein d'une boucle)
        int pointMD = 0; // nombre de fois où l'algo a étais le plus rapide (au sein d'une boucle)

        RepertoireAlgos exe = new RepertoireAlgos();

        int nombreDeTestes = 20;
        for (int i = 0; i < nombreDeTestes; i++) {
            startTime = System.currentTimeMillis();
            // #region MathieuS
            exe.mathieuS();
            // #endregion MathieuS
            endTime = System.currentTimeMillis();
            timeMS = endTime - startTime;
            tempsMoyenMS += timeMS / nombreDeTestes;

            startTime = System.currentTimeMillis();
            // #region MatthiasD
            exe.matthiasD();
            // #endregion MatthiasD
            endTime = System.currentTimeMillis();
            timeMD = endTime - startTime;
            tempsMoyenMD += timeMD / nombreDeTestes;

            pointMD += (timeMD < timeMS) ? 1 : 0;
            pointMS += (timeMS < timeMD) ? 1 : 0;

            // System.out.println((((double)i)/((double)nombreDeTestes))*100);
            int charge = (int) ((((double) i) / ((double) nombreDeTestes)) * 100);
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
