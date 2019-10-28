package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class Comparer {

    private byte iterationActuel = 0;
    private double tempsMax = 240;

    // TODO: Attention ce programme est à faire en dernier lorsque tout les
    // programmes ont validé les tests

    private ModeRecherche[] listAlgo;
    private boolean[] algosDepassantTemps;

    private int nombreDeTestes = 20;

    private double tempsMoyenAlgo[];
    private double tempsMoyenAlgoVariance[];

    private int nombreDeVilles = 10;
    private byte villeDepart = 0;

    private String etapeChargementAttein;
    private String etapeChargementNonAttein;
    private final String BARRE_CHARGEMENT_APPARENCE = "[#...................................................................................................]";
    private String barreDeChargement;

    public Comparer(ModeRecherche[] listAlgo) {
        this.listAlgo = listAlgo;
        this.tempsMoyenAlgo = new double[listAlgo.length];
        this.tempsMoyenAlgoVariance = new double[listAlgo.length];
        this.algosDepassantTemps = new boolean[listAlgo.length];
    }

    // TODO: note pour moi-m^me Mathieu oublie pas que tu as mis en paramètre
    // tempsMax
    public Comparer(ModeRecherche[] listAlgo, int nombreDeVilles, int nombreDeTests, double tempsMax) {
        this.tempsMax = tempsMax;
        this.listAlgo = listAlgo;
        this.tempsMoyenAlgo = new double[listAlgo.length];
        this.algosDepassantTemps = new boolean[listAlgo.length];
        this.tempsMoyenAlgoVariance = new double[listAlgo.length];
        this.nombreDeTestes = nombreDeTests;
        this.nombreDeVilles = nombreDeVilles;

    }

    // #region méthodes de calcul

    public void calcule() {

        barreDeChargementInit();

        this.tempsMoyenAlgo = new double[listAlgo.length];
        this.tempsMoyenAlgoVariance = new double[listAlgo.length];

        for (iterationActuel = 0; iterationActuel < nombreDeTestes; iterationActuel++) {

            effectueAlgos();

            barreDeChargement(iterationActuel);
        }

    }

    public void calculeTempsExecutionBrut() {
        for (int i = 0; i < nombreDeTestes; i++) {

            effectueAlgos();

        }
    }

    // #endregion méthode de calcul

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

    /**
     * Execute tous les algorithme dans la liste, en vérifiant qu'il ne dépasse pas
     * le temps imparti @tempsMax
     */
    private void effectueAlgos() {
        Pays pays = new Pays(nombreDeVilles);
        for (int j = 0; j < listAlgo.length; j++) {
            if (!algosDepassantTemps[j] && (iterationActuel == 0
                    || ((tempsMoyenAlgo[j] * (nombreDeTestes / iterationActuel))) < (tempsMax * 1000))) {
                double tempsExecution = calculeTempsExecution(listAlgo[j], pays);
                tempsMoyenAlgo[j] += tempsExecution / nombreDeTestes;
                tempsMoyenAlgoVariance[j] += (Math.pow(tempsExecution, 2)) / nombreDeTestes;
            } else {
                algosDepassantTemps[j] = true;
                tempsMoyenAlgo[j] = 0;
            }
        }
    }

    // #region Outils Barre de Chargement
    private void barreDeChargementInit() {
        etapeChargementAttein = "##";
        etapeChargementNonAttein = "#.";
        for (int i = 1; i < (int) ((((double) 1) / ((double) nombreDeTestes)) * 100); i++) {
            etapeChargementAttein += '#';
            etapeChargementNonAttein += '.';
        }
        barreDeChargement = BARRE_CHARGEMENT_APPARENCE;
        System.out.print(barreDeChargement);
    }

    private void barreDeChargement(int i) {
        int charge = (int) ((((double) i) / ((double) nombreDeTestes)) * 100);
        int chargePrecedant = ((int) ((((double) (i - 1)) / ((double) nombreDeTestes)) * 100));
        if ((charge - chargePrecedant) != 0) {
            barreDeChargement = barreDeChargement.replace(etapeChargementNonAttein, etapeChargementAttein);
        }
        System.out.print("\r" + barreDeChargement);
    }
    // #endregion Outils barre de chargement

    // #region outils calcul sur le temps
    private long calculeTempsExecution(ModeRecherche algo, Pays pays) {
        long startTime = System.currentTimeMillis();
        algo.recherche(pays, villeDepart);
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }

    private double recupéreTempsPlusLent() {
        double tempsPlusLent = 0.;
        for (double tempsMoyen : tempsMoyenAlgo) {
            tempsPlusLent = (tempsMoyen > tempsPlusLent) ? tempsMoyen : tempsPlusLent;
        }
        return tempsPlusLent;
    }
    // #endregion outils calcul sur le temps

    // #region setters & getters
    public void setNombreDeTest(int nombreDeTests) {
        this.nombreDeTestes = nombreDeTests;
    }

    public void setNombreDeVilles(int nombreDeVilles) {
        this.nombreDeVilles = nombreDeVilles;
    }

    public void setTempsMax(double tempsMax) {
        this.tempsMax = tempsMax;
    }

    public double getTempsMoyenAlgo(int i) {
        return tempsMoyenAlgo[i];
    }

    public double[] getListTempsMoyenAlgo() {
        return tempsMoyenAlgo;
    }
    // #endregion setters & getters
}
