package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.Pays;
import projetS3Voyageur.ModesDeRecherches.BrutForceV2;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class Comparer {

    private int iterationActuel = 0;
    private double tempsMax = 240;

    // Calcul de la varience du CurrentTime :
    private byte dernierElement;
    private final ModeRecherche ALGOREFERENCE = new BrutForceV2();
    private final Pays PAYSREFERENCE = new Pays(9);

    private boolean[] algosDepassantTemps;

    private ModeRecherche[] listAlgo;

    private double tempsMoyenAlgos[];
    private double margeErreurAlgos[];

    private int nombreDeTestes = 20;
    private int nombreDeVilles = 10;
    private byte villeDepart = 0;

    private String etapeChargementAttein;
    private String etapeChargementNonAttein;
    private final String BARRE_CHARGEMENT_APPARENCE = "[#...................................................................................................]";
    private String barreDeChargement;

    public Comparer(ModeRecherche[] listAlgo) {
        this.listAlgo = listAlgo;
        tempsMoyenAlgos = new double[listAlgo.length];
        margeErreurAlgos = new double[listAlgo.length];
        algosDepassantTemps = new boolean[listAlgo.length];
        dernierElement = (byte) listAlgo.length;
    }

    // TODO: note pour moi-m^me Mathieu oublie pas que tu as mis en paramètre
    // tempsMax
    public Comparer(ModeRecherche[] listAlgo, int nombreDeVilles, int nombreDeTests, double tempsMax) {
        this.tempsMax = tempsMax;
        this.listAlgo = listAlgo;
        this.nombreDeTestes = nombreDeTests;
        this.nombreDeVilles = nombreDeVilles;
        algosDepassantTemps = new boolean[listAlgo.length];
        tempsMoyenAlgos = new double[listAlgo.length];
        margeErreurAlgos = new double[listAlgo.length];

    }

    // #region méthodes de calcul

    public void calcule() {

        barreDeChargementInit();

        tempsMoyenAlgos = new double[listAlgo.length + 1];
        margeErreurAlgos = new double[listAlgo.length + 1];

        for (iterationActuel = 0; iterationActuel < nombreDeTestes; iterationActuel++) {

            effectueAlgos();

            barreDeChargement(iterationActuel);
        }

        CalculEcartType();

    }

    private void CalculEcartType() {
        for (int i = 0; i < margeErreurAlgos.length; i++) {
            margeErreurAlgos[i] = Math.sqrt(margeErreurAlgos[i] - Math.pow(tempsMoyenAlgos[i], 2));
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
        for (int i = 0; i < listAlgo.length; i++) {
            int poucentage = (int) ((((tempsPlusLent) / ((tempsMoyenAlgos[i]))) - 1) * 100);

            String tempsMoyenObtenue = " :\n Temps moyen de recherche : " + tempsMoyenAlgos[i];
            String differenceAvecPlusLent = "\n En moyenne " + poucentage + " % plus rapide que l'algo le plus lent.";
            String margeErreur = "\n Marge d'erreur : " + margeErreurAlgos[i];

            System.out.println("\n Résultat avec " + listAlgo[i].getNom() + tempsMoyenObtenue + differenceAvecPlusLent
                    + margeErreur + "\n");

        }
        System.out.println("Marge d'erreur de la machine : "
                + (int) ((tempsMoyenAlgos[dernierElement] / margeErreurAlgos[dernierElement])) + "%");

    }

    /**
     * Execute tous les algorithme dans la liste, en vérifiant qu'il ne dépasse pas
     * le temps imparti @tempsMax
     */
    private void effectueAlgos() {
        Pays pays = new Pays(nombreDeVilles);
        calculVarianceCurrentTime();

        for (int j = 0; j < listAlgo.length; j++) {
            if (!algosDepassantTemps[j] && (iterationActuel == 0
                    || ((tempsMoyenAlgos[j] * (nombreDeTestes / iterationActuel))) < tempsMax * 1000)) {
                double tempsExecution = calculeTempsExecution(listAlgo[j], pays);
                tempsMoyenAlgos[j] += tempsExecution / nombreDeTestes;
                margeErreurAlgos[j] += (Math.pow(tempsExecution, 2)) / nombreDeTestes;
            } else {
                algosDepassantTemps[j] = true;
                tempsMoyenAlgos[j] = 0;
                margeErreurAlgos[j] = 0;
            }
        }
        calculVarianceCurrentTime();
    }

    private void calculVarianceCurrentTime() {
        double tempsExecution = calculeTempsExecution(ALGOREFERENCE, PAYSREFERENCE);
        tempsMoyenAlgos[dernierElement] += tempsExecution / (nombreDeTestes * 2);
        margeErreurAlgos[dernierElement] += (Math.pow(tempsExecution, 2)) / (nombreDeTestes * 2);
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
        for (double tempsMoyen : tempsMoyenAlgos) {
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
        return tempsMoyenAlgos[i];
    }

    public double[] getListTempsMoyenAlgo() {
        return tempsMoyenAlgos;
    }

    public double[] getListMargeErreurAlgos() {
        return margeErreurAlgos;
    }
    // #endregion setters & getters
}
