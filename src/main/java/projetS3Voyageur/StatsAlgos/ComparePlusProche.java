package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.CompositionPays.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;
import projetS3Voyageur.ModesDeRecherches.PlusProcheV3;

public class ComparePlusProche {

    private static final ModeRecherche plusProche = new PlusProcheV3();

    private double[] distancesAlgo = new double[12];
    private double[] distancesPlusProche = new double[12];

    /**
     * Compare les résultats de l'algorithme PlusProche v.3 avec l'algorithme de
     * recherche donné en paramètre.
     * 
     * La comparaison se fait avec la moyenne des résultats obtenue sur 100 graphes
     * générés aléatoirement pour un nombre de ville donné.
     * 
     * Compare les résultats des graphes avec 4 points jusqu'au graphe avec 16
     * points.
     * 
     * @param algo {@code ModeRecherche} Algorithme de recherche dont les résultats
     *             seront comparés avec l'algortihme de recherche plusProche v.3
     */
    public void comparer(ModeRecherche algo) {
        for (int i = 4; i < 16; i++) {

            Pays pays;

            double distanceAlgo = 0;
            double distancePlusProche = 0;

            System.out.println("\n Avec " + i + " Villes :");
            BarreChargement barre = new BarreChargement(100);
            for (int j = 0; j < 100; j++) {
                pays = new Pays(i);
                algo.recherche(pays, 0);
                plusProche.recherche(pays, 0);
                distanceAlgo += algo.getParcours().getDistance() / 100;
                distancePlusProche += plusProche.getParcours().getDistance() / 100;
                barre.avancer(j);
            }

            distancesAlgo[i - 4] = distanceAlgo;
            distancesPlusProche[i - 4] = distancePlusProche;

        }
    }

    /**
     * @return the distancesAlgo
     */
    public double[] getDistancesAlgo() {
        return distancesAlgo;
    }

    /**
     * @return the distancesPlusProche
     */
    public double[] getDistancesPlusProche() {
        return distancesPlusProche;
    }

    /**
     * 
     */
    public ComparePlusProche() {
    }

}