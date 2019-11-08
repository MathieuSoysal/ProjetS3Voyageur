package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

class Analyser {

    // TODO: Cette classe doit aussi pouvoir analyser un algo dans plusieurs nbVille
    // différents la class GenererCSV doit faire faire cette tâche

    private int iterationActuel = 0;
    private double tempsMax = 240;

    // Calcul de la varience du CurrentTime :

    private boolean[] algosDepassantTempsMax;
    VarianceCurrentTime varianceCurrentTime = null;

    private ModeRecherche[] listeAlgo;

    private double tempsMoyenAlgos[];
    private double margeErreurAlgos[];

    private int nombreDeTests = 20; // par défaut à 29
    private int nombreDeVilles = 10; // par défaut à 10

    Analyser(ModeRecherche[] listeAlgo) {
        this.listeAlgo = listeAlgo;
        tempsMoyenAlgos = new double[listeAlgo.length];
        margeErreurAlgos = new double[listeAlgo.length];
        algosDepassantTempsMax = new boolean[listeAlgo.length];
    }

    // TODO: note pour moi-m^me Mathieu oublie pas que tu as mis en paramètre
    // tempsMax
    Analyser(ModeRecherche[] listeAlgo, int nombreDeVilles, int nombreDeTests, double tempsMax) {
        this.tempsMax = tempsMax;
        this.listeAlgo = listeAlgo;
        this.nombreDeTests = nombreDeTests;
        this.nombreDeVilles = nombreDeVilles;
        algosDepassantTempsMax = new boolean[listeAlgo.length];
        tempsMoyenAlgos = new double[listeAlgo.length];
        margeErreurAlgos = new double[listeAlgo.length];

    }

    // #region méthodes de calcul

    /**
     * Analyse la liste des algorithmes donnés dans le constructeur pour un nombre
     * de ville donné dans le constructeur. La méthode calcule le temps de
     * résolution de chacun des algorithmes, la marge d'erreur des résultats, et la
     * marge d'erreur du CurrentTIme
     */
    void analyse() {

        BarreChargement chargement = new BarreChargement(nombreDeTests);

        varianceCurrentTime = new VarianceCurrentTime(nombreDeTests);

        tempsMoyenAlgos = new double[listeAlgo.length];
        margeErreurAlgos = new double[listeAlgo.length];

        for (iterationActuel = 0; iterationActuel < nombreDeTests; iterationActuel++) {

            effectueAlgos();
            chargement.avancer(iterationActuel);
        }

        CalculEcartType();

    }

    /**
     * Méthode identique à analyse() mise à part que analyseBrut() n'affiche pas la
     * barre de chargement
     */
     void analyseBrut() {

        varianceCurrentTime = new VarianceCurrentTime(nombreDeTests);

        tempsMoyenAlgos = new double[listeAlgo.length];
        margeErreurAlgos = new double[listeAlgo.length];

        for (int i = 0; i < nombreDeTests; i++) {

            effectueAlgos();

        }
    }

    // #endregion méthode de calcul

    /**
     * Affiche dans la console le résultat de l'analyse des algorithmes, résulat
     * affiché pour chaque algorithme :
     *  - temps moyen de recherche
     *  - marge d'erreur du temps moyen de recherche
     *  - comparaison en % avec le temps moyen le plus lent
     * 
     * En final :
     *  - la marge d'erreur de la fonction CurrentTime
     */
     void afficher() {
        double tempsPlusLent = recupéreTempsPlusLent();
        for (int i = 0; i < listeAlgo.length; i++) {
            int poucentage = (int) ((((tempsPlusLent) / ((tempsMoyenAlgos[i]))) - 1) * 100);

            String tempsMoyenObtenu = " :\n Temps moyen de recherche : " + tempsMoyenAlgos[i];
            String margeErreur = "\n Marge d'erreur : " + margeErreurAlgos[i];
            String differenceAvecPlusLent = (listeAlgo.length == 1) ? ""
                    : "\n En moyenne " + poucentage + " % plus rapide que l'algo le plus lent.";

            System.out.println("\n Résultat avec " + listeAlgo[i].getNom() + tempsMoyenObtenu + differenceAvecPlusLent
                    + margeErreur + "\n");

        }
        if (varianceCurrentTime != null)
            System.out.println("Marge d'erreur du CurrentTime : "
                    + String.valueOf(getMargeErreurCurrentTime()).substring(0, 4) + "%");

    }

    /**
     * Execute tous les algorithme dans la liste, en vérifiant que l'algorithme ne
     * dépasse pas le temps imparti @tempsMax
     */
    private void effectueAlgos() {
        Pays pays = new Pays(nombreDeVilles);
        varianceCurrentTime.calcul();
        for (int j = 0; j < listeAlgo.length; j++) {
            if (!algosDepassantTempsMax[j] && (iterationActuel == 0
                    || ((tempsMoyenAlgos[j] * (nombreDeTests / iterationActuel))) < tempsMax * 1000)) {
                double tempsExecution = TempsExecution.calcule(listeAlgo[j], pays);
                tempsMoyenAlgos[j] += tempsExecution / nombreDeTests;
                margeErreurAlgos[j] += (Math.pow(tempsExecution, 2)) / nombreDeTests;
            } else {
                algosDepassantTempsMax[j] = true;
                tempsMoyenAlgos[j] = 0;
                margeErreurAlgos[j] = 0;
            }
        }
        varianceCurrentTime.calcul();
    }

    // #region outils calcul sur le temps

    /**
     * Renvoie le temps d'execution moyen le plus lent de la liste tempsMoyenAlgos
     * 
     * @return {@code double} temps d'execution moyen le plus lent de la liste
     */
    private double recupéreTempsPlusLent() {
        double tempsPlusLent = 0.;
        for (double tempsMoyen : tempsMoyenAlgos) {
            tempsPlusLent = Double.max(tempsPlusLent, tempsMoyen);
        }
        return tempsPlusLent;
    }

    /**
     * Applique pour chaque case de MargeErreurAlgos la formule de l'ecart-type
     * selon le théorème de König-Huygens
     */
    private void CalculEcartType() {
        for (int i = 0; i < margeErreurAlgos.length; i++) {
            margeErreurAlgos[i] = Math.sqrt(margeErreurAlgos[i] - Math.pow(tempsMoyenAlgos[i], 2));
        }
    }
    // #endregion outils calcul sur le temps

    // #region setters & getters
    public void setNombreDeTest(int nombreDeTests) {
        this.nombreDeTests = nombreDeTests;
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
