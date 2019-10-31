package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class TempsExecution {
    private static final byte villeDepart = 0;

    public static long calcule(ModeRecherche algo, Pays pays) {
        long startTime = System.currentTimeMillis();
        algo.recherche(pays, villeDepart);
        return (System.currentTimeMillis() - startTime);
    }
}