package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class TempsExecution {
    private static final byte villeDepart = 0;

    /**
     * Retourne le temps passé en millisecondes entre le début et la fin de
     * l'algorithme pour récupérer le plus court trajet du pays donné en paramètre.
     * 
     * @param algo {@code ModeRecherche} Algorithme dont le temps d'exécution doit
     *             être récupéré
     * 
     * @param pays {@code Pays} Pays dont l'algorithme doit obtenir le parcours le
     *             plus court
     * 
     * @return {@code long} Retourne le temps passé en millisecondes entre le début
     *         et la fin de l'algorithme
     */
    public static long calcule(ModeRecherche algo, Pays pays) {
        long startTime = System.currentTimeMillis();
        algo.recherche(pays, villeDepart);
        return (System.currentTimeMillis() - startTime);
    }
}