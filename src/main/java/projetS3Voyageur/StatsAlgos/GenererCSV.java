package projetS3Voyageur.StatsAlgos;

import static projetS3Voyageur.StatsAlgos.CSV.writeCSV;
import static projetS3Voyageur.StatsAlgos.Repertorisation.getRepertoire;
import static projetS3Voyageur.StatsAlgos.Texte.ecrire;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import projetS3Voyageur.ModesDeRecherches.ModeRecherche;
import projetS3Voyageur.ModesDeRecherches.TrackProchesV2_1;

public class GenererCSV {

    public static void main(String[] args) throws Exception {

        // #region Comparer avec les résultats de plusProche
        GenererCSV fichier = new GenererCSV();
        fichier.genererComparaisonPlusProche(new TrackProchesV2_1());

        // #region Generer un fichier CSV
        // ModeRecherche[] listeAlgo = { new BrutForceV2(), new BrutForceV3(), new
        // BrutForceV3_1(), new BrutForceV4(),
        // new BackTrackV1(), new BackTrackV2(), new TrackProchesV1(), new
        // TrackProchesV1_1(),
        // new TrackProchesV2() };
        // GenererCSV fichierCSV = new GenererCSV();
        // fichierCSV.setTempsMax(30);
        // fichierCSV.setNbIteration(150);
        // fichierCSV.setNbVillesMax(20);
        // fichierCSV.GenereSyncro(listeAlgo);

    }

    // TODO: Néttoyer la class, Il ne respecte pas le principe Ouvert fermer
    // TODO: Cette classe doit-elle juste avec
    private byte nbVillesMax = 12;
    private int nbIteration = 100;
    private long tempsMax = 180;

    private String nomFichier = String.valueOf(LocalDate.now()) + ".csv";
    private File repertoire = getRepertoire();

    private ModeRecherche[] listAlgo;

    private ArrayList<String[]> tableauStats = new ArrayList<>();
    private ArrayList<String[]> tableauMargeErreur = new ArrayList<>();

    /**
     * Cette méthode permet de générer un fichier CSV possèdant les statistiques de
     * temps d'exécution des différents ModeRecherche donnés en paramètre.
     * 
     * Sa spécificité réside dans le fait que pour chaque itération elle donne le
     * même pays/graphique en paramètre aux différents algorithmes, ainsi la
     * comparaison entre les différents algos est moins affectée par le facteur
     * aléatoire d'un pays/graphique.
     * 
     * 
     * @param nbVillesMax Nombre de villes où les algos doivent s'arrêter
     * @param nbIteration Nombre de fois qu'on execute les algos pour chaque nbVille
     * @param listAlgo    Liste de ModeRecherche à comparer
     * @param nonFichier  Le nom du fichier au quelle les statistique seront stocké
     */
    public void GenereSyncro(ModeRecherche[] listAlgo) {
        Analyser analyse;
        this.listAlgo = listAlgo;
        initTupleSyncro();
        analyse = new Analyser(listAlgo, 3, nbIteration, tempsMax);
        for (byte nbVille = 3; nbVille != nbVillesMax + 1; nbVille++) {
            System.out.println("\n Nombre de villes actuelles :" + nbVille);
            analyse.setNombreDeVilles(nbVille);
            analyse.analyse();
            actualiseTableaux(analyse, nbVille);
        }

        writeCSV(tableauMargeErreur, ";", new File(repertoire, "marge_d'erreur-" + nomFichier));
        writeCSV(tableauStats, ";", new File(repertoire, "stats-" + nomFichier));
        ConfigOrdinateur.enregistreConfig(repertoire);
        ecrire(new File(repertoire, "infoCourbe.txt"), toString());

    }

    /**
     * Cette méthode permet de générer un fichier CSV possédant les statistiques
     * comparatifs entre la distance la plus courte obtenue avec l'algo donné en
     * paramètre et l'algorithme plusProche v.3.
     * 
     * Sa spécificité réside dans le fait que pour chaque itération elle donne le
     * même pays/graphique en paramètre aux différents algorithmes, ainsi la
     * comparaison entre les différents algos est moins affectée par le facteur
     * aléatoire d'un pays/graphique.
     * 
     * @param algo @{@code ModeRecherche} Algorithme de recherche qui doit être
     *             comparé
     */
    public void genererComparaisonPlusProche(ModeRecherche algo) {
        ComparePlusProche cpp = new ComparePlusProche();
        cpp.comparer(algo);

        ArrayList<String[]> tableau = new ArrayList<>();

        String[] tuple = { "Plus Proche Voisin", algo.getNom() };
        tableau.add(tuple);
        for (int i = 4; i < 16; i++) {
            String[] tupleStats = { String.valueOf(cpp.getDistancesPlusProche()[i - 4]).replace('.', ','),
                    String.valueOf(cpp.getDistancesAlgo()[i - 4]).replace('.', ',') };
            tableau.add(tupleStats);
        }

        writeCSV(tableau, ";", new File(repertoire, "comparaisonPlusProche" + nomFichier));
    }

    // #region Outils

    /**
     * Insére les données de l'analyse pour un nombre de villes dans le tableau
     * 
     * @param analyse {@code Analyser} Variable contenant les données de l'analyse
     * 
     * @param nbVille {@code byte} le nombre de villes avec lequel l'analyse à été
     *                effectué
     * 
     */// TODO: l'insertions dans la base de donnée SQL peut s'effectuer ici
    private void actualiseTableaux(Analyser analyse, byte nbVille) {

        tableauStats.add(construitTuple(analyse.getListTempsMoyenAlgo(), nbVille, analyse.getMargeErreurCurrentTime()));

        tableauMargeErreur
                .add(construitTuple(analyse.getListMargeErreurAlgos(), nbVille, analyse.getMargeErreurCurrentTime()));
    }

    // #region manipulation sur les tuples

    /**
     * Initialise les attributs des tableaux (TableauStats et tableauMargeErreur)
     * sous la forme : {Nombre de ville, nom algo n°1,..., nom algo n°x, Marge
     * d'erreur du Current Time}
     */
    private void initTupleSyncro() {
        String[] attributs = new String[listAlgo.length + 2];
        attributs[0] = "Nombre de villes";
        for (byte i = 0; i != (listAlgo.length); i++) {
            attributs[i + 1] = listAlgo[i].getNom();
        }
        attributs[listAlgo.length + 1] = "Marge d'erreur CurrentTime";
        tableauStats.add(attributs);
        tableauMargeErreur.add(attributs);
    }

    /**
     * Construit un tuple en respectant les attributs du tableau avec les
     * informations données en paramètre
     * 
     * @param listeDouble            {@code double[]} Représente le temps mis par
     *                               les algos selon un nombre de villes
     * 
     * @param nbVille                {@code byte} Le nombre de villes représentatif
     *                               des données
     * 
     * @param margeErreurCurrentTime {@code double} Représente la marge d'erreur
     *                               lorsque l'on récupère le temps d'exécution via
     *                               la currentTime
     * 
     * @return {@code String[]} une liste de String, conforme aux attributs des
     *         tableaux c'est-à-dire : {nombre de villes, Donnée algo n°1,...,
     *         Donnée algo n°x, Marge d'erreur}
     */
    private String[] construitTuple(double[] listeDouble, byte nbVille, double margeErreurCurrentTime) {
        String[] tuple = convertToString(listeDouble);
        tuple[0] = String.valueOf(nbVille);
        tuple[listeDouble.length + 1] = String.valueOf(margeErreurCurrentTime).replace('.', ',');
        return tuple;
    }
    // #endregion manipulation sur les tuples

    /**
     * Convertie une liste de type double en une chaine de caractères conformément
     * au tuple dans les tableaux, remplace les 0 par un string vide pour éviter de
     * fausser les données du graphique.
     * 
     * @param listeDouble {@code double[]} la liste qui doit être convertie
     * 
     * @return {@code String[]} Retourne une liste de String conforme à la norme des
     *         tuples des tableaux
     */
    private String[] convertToString(double[] listeDouble) {

        String statsAlgos[] = new String[listeDouble.length
                + 2 /*
                     * +2 car le premier et dernier element sont réservés à l'indication du nbVille
                     * et de la marge d'erreur du currentTime
                     */];

        for (int i = 0; i < listeDouble.length; i++) {
            if (listeDouble[i] != 0)
                statsAlgos[i + 1] = String.valueOf(listeDouble[i] / 1000/* convertion en seconde */).replace('.', ',');
            else
                statsAlgos[i + 1] = "";
        }
        return statsAlgos;
    }

    // #endregion Outils

    // #region Setters

    /**
     * @param nbVillesMax the nbVillesMax to set
     */
    public void setNbVillesMax(int nbVillesMax) {
        this.nbVillesMax = (byte) nbVillesMax;
    }

    /**
     * @param nbIteration the nbIteration to set
     */
    public void setNbIteration(int nbIteration) {
        this.nbIteration = nbIteration;
    }

    /**
     * @param tempsMax the tempsMax to set
     */
    public void setTempsMax(long tempsMax) {
        this.tempsMax = tempsMax;
    }

    /**
     * @param nomFichier the nomFichier to set
     */
    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    // #endregion Setters

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "    GenererCSV : \n List des algorithmes de recherches =" + Arrays.toString(listAlgo)
                + "\n nombre d'itération=" + nbIteration + "\n Nombre de villes maximum =" + nbVillesMax
                + " \n Temps maximum=" + tempsMax + " seconde" + ((tempsMax > 1) ? "s" : "");
    }

}