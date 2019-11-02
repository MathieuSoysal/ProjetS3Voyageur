package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class TempsExecution {
    private static final byte villeDepart = 0;

    /**
     * Récupère le temps avant l'exécution de l'algorithme de recherche et soustrais
     * le temps pris au début par temps pris à la fin) ainsi récupéré le temps de
     * résolution mit par l'algorithme pour résoudre le graphe du pays donné en
     * paramètre.
     * 
     * @param algo {@code ModeRecherche} Algorithme dont le temps d'exécution doit
     *             être récupéré
     * @param pays {@code Pays} Pays dont l'algorithme doit obtenir le parcours le
     *             plus court
     * @return {@code long} Retourne le temps pris à la fin de l'exécution par le temps prit
     *         au début de l'exécution
     */
    public static long calcule(ModeRecherche algo, Pays pays) {
        long startTime = System.currentTimeMillis();
        algo.recherche(pays, villeDepart);
        return (System.currentTimeMillis() - startTime);
    }
}