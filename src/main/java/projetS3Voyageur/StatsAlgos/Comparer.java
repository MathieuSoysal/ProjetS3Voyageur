package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class Comparer {

    // TODO: Attention ce programme est à faire en dernier lorsque tout les
    // programmes ont validé les tests

    private ModeRecherche[] listAlgo;
    private int nombreDeTestes = 20;

    private double tempsMoyenAlgo[];

    private int nombreDeVilles = 10;
    private byte villeDepart = 0;

    private String etapeChargementAttein = "##";
    private String etapeChargementNonAttein = "#.";
    private String barreDeChargement = "[#...................................................................................................]";

    public Comparer(ModeRecherche[] listAlgo) {
        this.listAlgo = listAlgo;
        this.tempsMoyenAlgo = new double[listAlgo.length];

    }

    public void setNombreDeTest(int nombreDeTests) {
        this.nombreDeTestes = nombreDeTests;
    }

    public void setNombreDeVilles(int nombreDeVilles) {
        this.nombreDeVilles = nombreDeVilles;
    }

    public void afficher() {

        barreDeChargementInit();

        for (int i = 0; i < nombreDeTestes; i++) {

            effectueAlgos();

            barreDeChargement(i);
        }
        double tempsPlusLent = recupéreTempsPlusLent();
        for (int i = 0; i < tempsMoyenAlgo.length; i++) {
            int poucentage = (int) ((((tempsPlusLent) / ((tempsMoyenAlgo[i]))) - 1) * 100);
            System.out.println("\n algo n°" + i + " dans la liste :\n Temps moyen de recherche : " + tempsMoyenAlgo[i]
                    + "\n En moyenne " + poucentage + " % plus rapide que l'algo le plus lent."+"\n");

        }

    }

    public void calculeTempsExecutionBrut(){
        for (int i = 0; i < nombreDeTestes; i++) {

            effectueAlgos();

        }
    }


    private void effectueAlgos() {
        for (int j = 0; j < listAlgo.length; j++) {
            tempsMoyenAlgo[j] += calculeTempsExecution(listAlgo[j]);
        }
    }

    private double recupéreTempsPlusLent() {
        double tempsPlusLent = 0.;
        for (double tempsMoyen : tempsMoyenAlgo) {
            tempsPlusLent = (tempsMoyen > tempsPlusLent) ? tempsMoyen : tempsPlusLent;
        }
        return tempsPlusLent;
    }

    private void barreDeChargementInit() {
        for (int i = 1; i < (int) ((((double) 1) / ((double) nombreDeTestes)) * 100); i++) {
            etapeChargementAttein += '#';
            etapeChargementNonAttein += '.';
        }
    }

    private void barreDeChargement(int i) {
        int charge = (int) ((((double) i) / ((double) nombreDeTestes)) * 100);
        int chargePrecedant = ((int) ((((double) (i - 1)) / ((double) nombreDeTestes)) * 100));
        if ((charge - chargePrecedant) != 0) {
            barreDeChargement = barreDeChargement.replace(etapeChargementNonAttein, etapeChargementAttein);
        }
        System.out.print("\r" + barreDeChargement);
    }

    private double calculeTempsExecution(ModeRecherche algo) {
        long startTime = System.currentTimeMillis();
        algo.recherche(new Pays(nombreDeVilles), villeDepart);
        ;
        long endTime = System.currentTimeMillis();
        return (endTime - startTime) / nombreDeTestes;
    }

    // #region Pour les tests

    public double getTempsMoyenAlgo(int i) {
        return tempsMoyenAlgo[i];
    }

    // #endregion pour les tests
}
