package projetS3Voyageur;

public class Comparer {

    // TODO: Attention ce programme est à faire en dernier lorsque tout les
    // programmes ont validé les tests

    private AlgoLuncher algo1;
    private AlgoLuncher algo2;
    private int nombreDeTestes = 20;

    private long tempsMoyenAlgo1 = 0;
    private long tempsMoyenAlgo2 = 0;

    public Comparer(AlgoLuncher algo1, AlgoLuncher algo2) {
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

        for (int i = 0; i < nombreDeTestes; i++) {

            tempsMoyenAlgo1 += calculeTempsExecution(algo1) / nombreDeTestes;

            tempsMoyenAlgo2 += calculeTempsExecution(algo2) / nombreDeTestes;

            barreDeChargement(i);
        }

        System.out.println("\n  " + algo2.getNom() + "\n Temps moyen de recherche : " + tempsMoyenAlgo2);

        System.out.println("\n  " + algo1.getNom() + "\n Temps moyen de recherche : " + tempsMoyenAlgo1);

        System.out.println("\n L'algo le plus rapide a étais celui de la branche :"
                + ((tempsMoyenAlgo1 < tempsMoyenAlgo2) ? algo1.getNom() : algo2.getNom()));

    }

    private void barreDeChargement(int i) {
        int charge = (int) ((((double) i) / ((double) nombreDeTestes)) * 100);
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n         [");
        for (int j = 0; j < 100; j++) {
            System.out.print((j <= charge) ? "#" : ".");
        }
        System.out.print("] \n \n \n \n");
    }

    private long calculeTempsExecution(AlgoLuncher algo) {
        long startTime = System.currentTimeMillis();
        algo.execute();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    // #region Pour les tests

    public void calculeTempsExecutionBrut() {
        for (int i = 0; i < nombreDeTestes; i++) {

            tempsMoyenAlgo1 += calculeTempsExecution(algo1) / nombreDeTestes;

            tempsMoyenAlgo2 += calculeTempsExecution(algo2) / nombreDeTestes;
        }
    }

    public long getTempsMoyenAlgo1() {
        return tempsMoyenAlgo1;
    }

    public long getTempsMoyenAlgo2() {
        return tempsMoyenAlgo2;
    }
    // #endregion pour les tests
}
