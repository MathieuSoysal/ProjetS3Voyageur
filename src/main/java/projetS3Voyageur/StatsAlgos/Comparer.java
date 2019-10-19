package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class Comparer {

    // TODO: Attention ce programme est à faire en dernier lorsque tout les
    // programmes ont validé les tests

    private ModeRecherche[] listAlgo;
    private int nombreDeTestes = 20;

    private double tempsMoyenAlgo[];
    private double tempsMoyenAlgoVariance[];

    private int nombreDeVilles = 10;
    private byte villeDepart = 0;

    private String etapeChargementAttein = "##";
    private String etapeChargementNonAttein = "#.";
    private String barreDeChargement = "[#...................................................................................................]";

    public Comparer(ModeRecherche[] listAlgo) {
        this.listAlgo = listAlgo;
        this.tempsMoyenAlgo = new double[listAlgo.length];
        this.tempsMoyenAlgoVariance = new double[listAlgo.length];

    }

    public Comparer(ModeRecherche[] listAlgo, int nombreDeVilles, int nombreDeTests) {
        this.listAlgo = listAlgo;
        this.tempsMoyenAlgo = new double[listAlgo.length];
        this.tempsMoyenAlgoVariance = new double[listAlgo.length];
        this.nombreDeTestes = nombreDeTests;
        this.nombreDeVilles = nombreDeVilles;

    }

    public void setNombreDeTest(int nombreDeTests) {
        this.nombreDeTestes = nombreDeTests;
    }

    public void setNombreDeVilles(int nombreDeVilles) {
        this.nombreDeVilles = nombreDeVilles;
    }

    public void calcule() {

        barreDeChargementInit();

        for (int i = 0; i < nombreDeTestes; i++) {

            effectueAlgos();

            barreDeChargement(i);
        }

    }

    public void afficher() {
        double tempsPlusLent = recupéreTempsPlusLent();
        for (int i = 0; i < tempsMoyenAlgo.length; i++) {
            double ecartType = Math.sqrt(tempsMoyenAlgoVariance[i] - Math.pow(tempsMoyenAlgo[i], 2));
            int poucentage = (int) ((((tempsPlusLent) / ((tempsMoyenAlgo[i]))) - 1) * 100);

            String tempsMoyenObtenue = " :\n Temps moyen de recherche : " + tempsMoyenAlgo[i];
            String differenceAvecPlusLent = "\n En moyenne " + poucentage + " % plus rapide que l'algo le plus lent.";
            String margeErreur = "\n Marge d'erreur : " + ecartType;

            System.out.println("\n Résultat avec " + listAlgo[i].getNom() + tempsMoyenObtenue + differenceAvecPlusLent
                    + margeErreur);

        }

    }

    public void calculeTempsExecutionBrut() {
        for (int i = 0; i < nombreDeTestes; i++) {

            effectueAlgos();

        }
    }

    private void effectueAlgos() {
        for (int j = 0; j < listAlgo.length; j++) {
            double tempsExecution = calculeTempsExecution(listAlgo[j]);
            tempsMoyenAlgo[j] += tempsExecution / nombreDeTestes;
            tempsMoyenAlgoVariance[j] += (Math.pow(tempsExecution, 2)) / nombreDeTestes;

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

    private long calculeTempsExecution(ModeRecherche algo) {
        long startTime = System.currentTimeMillis();
        algo.recherche(new Pays(nombreDeVilles), villeDepart);
        ;
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }

    // #region Pour les tests

    public double getTempsMoyenAlgo(int i) {
        return tempsMoyenAlgo[i];
    }

    public double[] getListTempsMoyenAlgo() {
        return tempsMoyenAlgo;
    }
    // #endregion pour les tests
}
