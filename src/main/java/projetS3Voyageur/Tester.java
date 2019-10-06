package projetS3Voyageur;

public class Tester {

    // TODO: Attention ce programme est à faire en dernier lorsque tout les
    // programmes ont validé les tests

    private AlgoLuncher algo1;
    private AlgoLuncher algo2;
    private int nombreDeTestes = 20;

    public Tester(AlgoLuncher algo1, AlgoLuncher algo2) {
        this.algo1 = algo1;
        this.algo2 = algo2;
    }

    public void setNombreDeTest(int nombreDeTests) {
        this.nombreDeTestes = nombreDeTests;
    }

    public void setNombreDeVilles(int nombreDeVilles) {
        this.algo1.setNombreDeVilles(nombreDeVilles);
        this.algo2.setNombreDeVilles(nombreDeVilles);
    }

    public void afficher() {
        long startTime; // variable pour récupéré l'heure (en miniseconde) à la quelle on commence le
                        // probramme
        long endTime;// variable pour récupéré l'heure (en miniseconde) à la quelle on arrête le
                     // probramme

        long timeALgo1; // variable permetant de stocké le temps mit pour l'exécution d'un algo
        long timeAlgo2; // variable permetant de stocké le temps mit pour l'exécution d'un algo

        long tempsMoyenAlgo1 = 0;
        long tempsMoyenAlgo2 = 0;

        int pointAlgo1 = 0; // nombre de fois où l'algo a étais le plus rapide (au sein d'une boucle)
        int pointAlgo2 = 0; // nombre de fois où l'algo a étais le plus rapide (au sein d'une boucle)

        for (int i = 0; i < nombreDeTestes; i++) {
            startTime = System.currentTimeMillis();
            // #region MathieuS
            algo1.execute();
            // #endregion MathieuS
            endTime = System.currentTimeMillis();
            timeALgo1 = endTime - startTime;
            tempsMoyenAlgo1 += timeALgo1 / nombreDeTestes;

            startTime = System.currentTimeMillis();
            // #region MatthiasD
            algo2.execute();
            // #endregion MatthiasD
            endTime = System.currentTimeMillis();
            timeAlgo2 = endTime - startTime;
            tempsMoyenAlgo2 += timeAlgo2 / nombreDeTestes;

            pointAlgo2 += (timeAlgo2 < timeALgo1) ? 1 : 0;
            pointAlgo1 += (timeALgo1 < timeAlgo2) ? 1 : 0;

            // System.out.println((((double)i)/((double)nombreDeTestes))*100);
            int charge = (int) ((((double) i) / ((double) nombreDeTestes)) * 100);
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n         [");
            for (int j = 0; j < 100; j++) {
                System.out.print((j <= charge) ? "#" : ".");
            }
            System.out.print("] \n \n \n \n");
        }

        System.out.println("\n  " + algo2.getNom() + " :\n" + "Points obtenue : " + pointAlgo2
                + "\n Temps moyen de recherche : " + tempsMoyenAlgo2);

        System.out.println("\n  " + algo1.getNom() + " :\n" + "Points obtenue : " + pointAlgo1
                + "\n Temps moyen de recherche : " + tempsMoyenAlgo1);

        System.out.println("\n L'algo le plus rapide a étais celui de la branche :"
                + ((pointAlgo1 > pointAlgo2) ? algo1.getNom() : algo2.getNom()));

    }

}
