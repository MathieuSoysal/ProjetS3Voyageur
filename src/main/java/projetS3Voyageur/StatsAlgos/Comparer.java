package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class Comparer {

    // TODO: Attention ce programme est à faire en dernier lorsque tout les
    // programmes ont validé les tests

    private ModeRecherche algo1;
    private ModeRecherche algo2;
    private int nombreDeTestes = 20;

    private long tempsMoyenAlgo1 = 0;
    private long tempsMoyenAlgo2 = 0;

    private int nombreDeVilles = 10;
    private byte villeDepart = 0;

    private String etapeChargementAttein = "##";
    private String etapeChargementNonAttein = "#.";
    private String barreDeChargement = "[#...................................................................................................]";

    public Comparer(ModeRecherche algo1, ModeRecherche algo2) {
        this.algo1 = algo1;
        this.algo2 = algo2;
    }

    public void setNombreDeTest(int nombreDeTests) {
        this.nombreDeTestes = nombreDeTests;
    }

    public void setNombreDeVilles(int nombreDeVilles) {
        this.nombreDeVilles = nombreDeVilles;
    }

    public void afficher() {

        for (int i = 1; i < (int) ((((double) 1) / ((double) nombreDeTestes)) * 100); i++) {
            etapeChargementAttein += '#';
            etapeChargementNonAttein += '.';
        }

        for (int i = 0; i < nombreDeTestes; i++) {

            tempsMoyenAlgo1 += calculeTempsExecution(algo1) / nombreDeTestes;

            tempsMoyenAlgo2 += calculeTempsExecution(algo2) / nombreDeTestes;

            barreDeChargement(i);
        }

        System.out.println("\n  Premier algo en paramètre :\n Temps moyen de recherche : " + tempsMoyenAlgo1);

        System.out.println("\n  Deuxième algo en paramètre :\n Temps moyen de recherche : " + tempsMoyenAlgo2);

        System.out
                .println(
                        " \n         Le " + ((tempsMoyenAlgo1 < tempsMoyenAlgo2) ? "premier" : "deuxième")
                                + " algo est "
                                + ((((Double.max(tempsMoyenAlgo1, tempsMoyenAlgo2))
                                        / (Double.min(tempsMoyenAlgo1, tempsMoyenAlgo2)) - 1) * 100))
                                + "% plus rapide !");

    }

    private void barreDeChargement(int i) {
        int charge = (int) ((((double) i) / ((double) nombreDeTestes)) * 100);
        int chargePrecedant = ((int) ((((double) (i - 1)) / ((double) nombreDeTestes)) * 100));
        if ((charge - chargePrecedant) != 0) {
            barreDeChargement = barreDeChargement.replace(etapeChargementNonAttein, etapeChargementAttein);
        }
        System.out.print("\r" + barreDeChargement);
    }

    private long calculeTempsExecution(ModeRecherche algo) {
        long startTime = System.currentTimeMillis();
        algo.recherche(new Pays(nombreDeVilles), villeDepart);
        ;
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
