package projetS3Voyageur.StatsAlgos;

import static projetS3Voyageur.StatsAlgos.CSV.writeCSV;

import java.util.ArrayList;

import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class GenererCSV {

    private byte nbVillesMax = 12;
    private int nbIteration = 100;
    private long tempsMax = Long.MAX_VALUE;

    private ModeRecherche[] listAlgo;

    private ArrayList<String[]> tuples = new ArrayList<>();

    private String nonFichier;

    /**
     * @param listAlgo
     */
    public GenererCSV(ModeRecherche[] listAlgo, String nonFichier) {
        this.listAlgo = listAlgo;
        this.nonFichier = nonFichier;
        initTuple();
    }

    private void initTuple() {
        String[] attributs = new String[nbVillesMax - 2];
        attributs[0] = "Algorithmes";
        for (byte i = 0; i < (nbVillesMax - 3); i++) {
            attributs[i + 1] = String.valueOf(i + 3);
        }
        tuples.add(attributs);
    }

    /**
     * @param nbVillesMax
     * @param nbIteration
     * @param listAlgo
     */
    public GenererCSV(int nbVillesMax, int nbIteration, ModeRecherche[] listAlgo, String nonFichier) {
        // TODO: Vérifier les valeurs saisie
        this.nbVillesMax = (byte) nbVillesMax;
        this.nbIteration = nbIteration;
        this.listAlgo = listAlgo;
        initTuple();
    }

    public void Genere() {
        Analyser analyse;
        int i = 0;
        String[] statsAlgo;
        for (ModeRecherche currentAlgo : listAlgo) {
            System.out.println("\n" + currentAlgo.getNom() + " :");
            analyse = new Analyser(nbVillesMax, nbIteration, currentAlgo);
            analyse.calculSafe(tempsMax);
            statsAlgo = convertToString(i++, analyse.getResultat());
            statsAlgo[0] = currentAlgo.getNom();
            tuples.add(statsAlgo);
        }
        writeCSV(tuples, ";", nonFichier);
    }

    // #region Outils

    private String[] convertToString(int indexAglo, double[] tempsMoyenParVilles) {
        String statsAlgo[] = new String[tempsMoyenParVilles.length + 1];
        for (int i = 0; i < tempsMoyenParVilles.length; i++) {
            if (tempsMoyenParVilles[i] != 0)
                statsAlgo[i + 1] = String.valueOf(tempsMoyenParVilles[i]).replace('.', ',');
            else
                statsAlgo[i + 1] = "";
        }
        return statsAlgo;
    }

    // #endregion Outils

    // #region Setter

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

    // #endregion Settet

}