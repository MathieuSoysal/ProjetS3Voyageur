package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class Analyser {

    // TODO: Cette classe doit aussi pouvoir analyser un algo dans plusieurs nbVille
    // différents la class GenererCSV doit faire faire cette tâche

    private int iterationActuel = 0;
    private double tempsMax = 240;

    // Calcul de la varience du CurrentTime :

    private boolean[] algosDepassantTemps;
    VarianceCurrentTime varianceCurrentTime = null;

    private ModeRecherche[] listAlgo;

    private double tempsMoyenAlgos[];
    private double margeErreurAlgos[];

    private int nombreDeTestes = 20;
    private int nombreDeVilles = 10;

    private BarreChargement chargement;

    public Analyser(ModeRecherche[] listAlgo) {
        this.listAlgo = listAlgo;
        tempsMoyenAlgos = new double[listAlgo.length];
        margeErreurAlgos = new double[listAlgo.length];
        algosDepassantTemps = new boolean[listAlgo.length];
    }

    // TODO: note pour moi-m^me Mathieu oublie pas que tu as mis en paramètre
    // tempsMax
    public Analyser(ModeRecherche[] listAlgo, int nombreDeVilles, int nombreDeTests, double tempsMax) {
        this.tempsMax = tempsMax;
        this.listAlgo = listAlgo;
        this.nombreDeTestes = nombreDeTests;
        this.nombreDeVilles = nombreDeVilles;
        algosDepassantTemps = new boolean[listAlgo.length];
        tempsMoyenAlgos = new double[listAlgo.length];
        margeErreurAlgos = new double[listAlgo.length];

    }

    // #region méthodes de calcul

    public void analyse() {

        varianceCurrentTime = new VarianceCurrentTime(nombreDeTestes);
        chargement = new BarreChargement(nombreDeTestes);

        tempsMoyenAlgos = new double[listAlgo.length];
        margeErreurAlgos = new double[listAlgo.length];

        for (iterationActuel = 0; iterationActuel < nombreDeTestes; iterationActuel++) {
            effectueAlgos();

            chargement.avancer(iterationActuel);
        }

        CalculEcartType();

    }

    public void calculeTempsExecutionBrut() {
        varianceCurrentTime = new VarianceCurrentTime(nombreDeTestes);

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
            String margeErreur = "\n Marge d'erreur : " + margeErreurAlgos[i];
            String differenceAvecPlusLent = (listAlgo.length == 1) ? ""
                    : "\n En moyenne " + poucentage + " % plus rapide que l'algo le plus lent.";

            System.out.println("\n Résultat avec " + listAlgo[i].getNom() + tempsMoyenObtenue + differenceAvecPlusLent
                    + margeErreur + "\n");

        }
        if (varianceCurrentTime != null)
            System.out.println("Marge d'erreur du CurrentTime : "
                    + String.valueOf(getMargeErreurCurrentTime()).substring(0, 4) + "%");

    }

    /**
     * Execute tous les algorithme dans la liste, en vérifiant qu'il ne dépasse pas
     * le temps imparti @tempsMax
     */
    private void effectueAlgos() {
        Pays pays = new Pays(nombreDeVilles);
        varianceCurrentTime.calcul();
        for (int j = 0; j < listAlgo.length; j++) {
            if (!algosDepassantTemps[j] && (iterationActuel == 0
                    || ((tempsMoyenAlgos[j] * (nombreDeTestes / iterationActuel))) < tempsMax * 1000)) {
                double tempsExecution = TempsExecution.calcule(listAlgo[j], pays);
                tempsMoyenAlgos[j] += tempsExecution / nombreDeTestes;
                margeErreurAlgos[j] += (Math.pow(tempsExecution, 2)) / nombreDeTestes;
            } else {
                algosDepassantTemps[j] = true;
                tempsMoyenAlgos[j] = 0;
                margeErreurAlgos[j] = 0;
            }
        }
        varianceCurrentTime.calcul();
    }

    // #region outils calcul sur le temps

    private double recupéreTempsPlusLent() {
        double tempsPlusLent = 0.;
        for (double tempsMoyen : tempsMoyenAlgos) {
            tempsPlusLent = Double.max(tempsPlusLent, tempsMoyen);
        }
        return tempsPlusLent;
    }

    private void CalculEcartType() {
        for (int i = 0; i < margeErreurAlgos.length; i++) {
            margeErreurAlgos[i] = Math.sqrt(margeErreurAlgos[i] - Math.pow(tempsMoyenAlgos[i], 2));
        }
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

    public double getMargeErreurCurrentTime() {
        return varianceCurrentTime.getMargeErreur();
    }
    // #endregion setters & getters
}
