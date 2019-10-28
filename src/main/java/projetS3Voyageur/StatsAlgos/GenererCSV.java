package projetS3Voyageur.StatsAlgos;

import static projetS3Voyageur.StatsAlgos.CSV.writeCSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class GenererCSV {

    // TODO: Néttoyer la class
    // TODO: ajouter un calcul safe pour la méthode avec le graphe syncronisé

    private byte nbVillesMax = 12;
    private int nbIteration = 100;
    private long tempsMax = 180;

    private ModeRecherche[] listAlgo;

    private ArrayList<String[]> tuples = new ArrayList<>();

    private String nonFichier;

    /**
     * @param listAlgo
     */
    // public GenererCSV(ModeRecherche[] listAlgo, String nonFichier) {
    // this.listAlgo = listAlgo;
    // this.nonFichier = nonFichier;
    // initTuple();
    // }

    private void initTupleSyncro() {
        String[] attributs = new String[listAlgo.length + 2];
        attributs[0] = "Nombre de villes";
        for (byte i = 0; i < (listAlgo.length); i++) {
            attributs[i + 1] = listAlgo[i].getNom();
        }
        tuples.add(attributs);
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
    public void GenereSyncro(int nbVillesMax, int nbIteration, ModeRecherche[] listAlgo, String nonFichier) {
        Comparer compare;
        this.listAlgo = listAlgo;
        this.nbVillesMax = (byte) (nbVillesMax + 1);
        String[] statsAlgo = new String[listAlgo.length + 1];
        initTupleSyncro();
        compare = new Comparer(listAlgo, 3, nbIteration, tempsMax);
        for (byte nbVille = 3; nbVille != nbVillesMax + 1; nbVille++) {
            System.out.println("\n Nombre de villes actuel :" + nbVille);
            compare.setNombreDeVilles(nbVille);
            compare.calcule();
            statsAlgo = convertToString(compare.getListTempsMoyenAlgo());
            statsAlgo[0] = String.valueOf(nbVille);
            tuples.add(statsAlgo.clone());
        }

        creerFichier(nonFichier);
    }

    public void Genere(int nbVillesMax, int nbIteration, ModeRecherche[] listAlgo, String nonFichier) {
        Analyser analyse;
        this.listAlgo = listAlgo;
        this.nbVillesMax = (byte) nbVillesMax;
        int i = 0;
        String[] statsAlgo;
        initTuple();
        for (ModeRecherche currentAlgo : listAlgo) {
            System.out.println("\n" + currentAlgo.getNom() + " :");
            analyse = new Analyser((byte) nbVillesMax, nbIteration, currentAlgo);
            analyse.calculSafe(tempsMax);
            statsAlgo = convertToString(i++, analyse.getResultat());
            statsAlgo[0] = currentAlgo.getNom();
            tuples.add(statsAlgo);
        }
        creerFichier(nonFichier);
    }

    // #region Outils

    private String[] convertToString(double[] tempsMoyenPourUneVille) {
        String statsAlgo[] = new String[tempsMoyenPourUneVille.length + 1];

        for (int i = 0; i < tempsMoyenPourUneVille.length; i++) {
            if (tempsMoyenPourUneVille[i] != 0)
                statsAlgo[i + 1] = String.valueOf(tempsMoyenPourUneVille[i]).replace('.', ',');
            else
                statsAlgo[i + 1] = "";
        }
        return statsAlgo;
    }

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

    private void creerFichier(String nonFichier) {
        File f = new File("Statistiques/" + getNameOrdinateur());
        f.mkdirs();
        try {
            recupereComposant(f);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        writeCSV(tuples, ";", new File(f, nonFichier));
    }

    private String getNameOrdinateur() {
        Map<String, String> env = System.getenv();
        if (env.containsKey("COMPUTERNAME"))
            return env.get("COMPUTERNAME");
        else if (env.containsKey("HOSTNAME"))
            return env.get("HOSTNAME");
        else
            return System.getProperty("user.name");
    }

    private void recupereComposant(File f) throws IOException, InterruptedException {
        String commande = "";
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(f, "config.txt")));

        switch (System.getProperty("os.name")) {
        case "Windows 10":
            commande = "wmic cpu";
            break;
        default:
            commande = "cat/proc/cpuinfo";
        }
        Runtime r = Runtime.getRuntime();
        Process p = r.exec(commande);
        p.waitFor();
        BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";

        while ((line = b.readLine()) != null) {
            writer.write(line+"\n");
        }

        writer.close();
        b.close();

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