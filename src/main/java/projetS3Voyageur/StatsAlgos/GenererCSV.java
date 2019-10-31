package projetS3Voyageur.StatsAlgos;

import static projetS3Voyageur.StatsAlgos.CSV.writeCSV;
import static projetS3Voyageur.StatsAlgos.Repertorisation.getRepertoire;
import static projetS3Voyageur.StatsAlgos.Texte.ecrire;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class GenererCSV {

    // TODO: Néttoyer la class

    private byte nbVillesMax = 12;
    private int nbIteration = 100;
    private long tempsMax = 180;

    private String nomFichier = String.valueOf(LocalDate.now()) + ".csv";
    private File repertoire = getRepertoire();

    private ModeRecherche[] listAlgo;

    private ArrayList<String[]> tableauStats = new ArrayList<>();
    private ArrayList<String[]> tableauMargeErreur = new ArrayList<>();


    /**
     * Cette méthode permet de générer un fichier CSV possèdant les statistique de
     * temps d'exécution des différents ModeRecherche donnés en paramètre. Sa
     * spécificité réside dans le fait que pour chaque itération elle donne le même
     * pays/graphique en paramètre aux différents algorithmes, ainsi la comparaison
     * entre les différents algos est moins affectée par le facteur aléatoire d'un
     * pays/graphique.
     * 
     * 
     * @param nbVillesMax Nombre de villes où les algos doivent s'arrêter
     * @param nbIteration Nombre de fois qu'on execute les algos pour chaque nbVille
     * @param listAlgo    Liste de ModeRecherche à comparer
     * @param nonFichier  Le nom du fichier au quelle les statistique seront stocké
     */
    public void GenereSyncro(ModeRecherche[] listAlgo) {
        Analyser compare;
        this.listAlgo = listAlgo;
        initTupleSyncro();
        compare = new Analyser(listAlgo, 3, nbIteration, tempsMax);
        for (byte nbVille = 3; nbVille != nbVillesMax + 1; nbVille++) {
            System.out.println("\n Nombre de villes actuelles :" + nbVille);
            compare.setNombreDeVilles(nbVille);
            compare.analyse();
            actualiseTableaux(compare, nbVille);
        }

        writeCSV(tableauMargeErreur, ";", new File(repertoire, "marge_d'erreur-" + nomFichier));
        writeCSV(tableauStats, ";", new File(repertoire, "stats-" + nomFichier));
        ConfigOrdinateur.enregistreConfig(repertoire);
        ecrire(new File(repertoire, "infoCourbe.txt"), toString());

    }

    // #region Outils

    private void actualiseTableaux(Analyser compare, byte nbVille) {

        tableauStats.add(construitTuple(compare.getListTempsMoyenAlgo(), nbVille, compare.getMargeErreurCurrentTime()));

        tableauMargeErreur
                .add(construitTuple(compare.getListMargeErreurAlgos(), nbVille, compare.getMargeErreurCurrentTime()));
    }

    // #region manipulation sur les tuples

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

    private String[] construitTuple(double[] listDouble, byte nbVille, double margeErreurCurrentTime) {
        String[] tuple = convertToString(listDouble);
        tuple[0] = String.valueOf(nbVille);
        tuple[listDouble.length + 1] = String.valueOf(margeErreurCurrentTime);
        return tuple;
    }
    // #endregion manipulation sur les tuples

    private String[] convertToString(double[] listDouble) {

        String statsAlgos[] = new String[listDouble.length
                + 2 /*
                     * +2 car le premier et dernier element sont réservés à l'indication du nbVille
                     * et de la marge d'erreur du currentTime
                     */];

        for (int i = 0; i < listDouble.length; i++) {
            if (listDouble[i] != 0)
                statsAlgos[i + 1] = String.valueOf(listDouble[i]).replace('.', ',');
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