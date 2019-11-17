package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.CompositionPays.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class Comparer {

    private double[][] distancesAlgo;

    /**
     * Renvois pour chaque algo dans la liste {@code ModeRecherche[]} une liste
     * {@code double[]} de moyenne pondéré des distances obtenues pour les graphes de
     * 4 à 15 points.
     * 
     * La comparaison se fait avec la moyenne des résultats obtenue sur 100 graphes
     * générés aléatoirement pour un nombre de ville donné.
     * 
     * @param algo {@code ModeRecherche} Algorithme de recherche dont les résultats
     *             seront comparés.
     */
    public void comparer(ModeRecherche[] listAlgo) {
        distancesAlgo = new double[12][listAlgo.length];
        for (int i = 4; i < 16; i++) {

            Pays pays;

            System.out.println("\n Avec " + i + " Villes :");
            BarreChargement barre = new BarreChargement(100);
            for (int j = 0; j < 100; j++) {
                pays = new Pays(i);

                for (int j2 = 0; j2 < listAlgo.length; j2++) {
                    listAlgo[j2].recherche(pays, 0);
                    distancesAlgo[i - 4][j2] += listAlgo[j2].getParcours().getDistance() / 100;
                }

                barre.avancer(j);
            }

        }
    }

    /**
     * @return the distancesAlgo
     */
    public double[][] getDistancesAlgo() {
        return distancesAlgo;
    }

    /**
     * Renvois pour chaque algo dans la liste {@code ModeRecherche[]} une liste
     * {@code double[]} de moyenne pondéré des distances obtenues pour les villes de
     * 4 à 15.
     */
    public Comparer() {
    }

}