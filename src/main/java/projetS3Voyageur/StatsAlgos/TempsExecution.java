package projetS3Voyageur.StatsAlgos;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;
import projetS3Voyageur.CompositionPays.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;
import projetS3Voyageur.ModesDeRecherches.TrackProchesV2;

import java.util.concurrent.TimeUnit;

class TempsExecution {
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
    static long calcule(ModeRecherche algo, Pays pays) {
        long startTime = System.currentTimeMillis();
        algo.recherche(pays, villeDepart);
        return (System.currentTimeMillis() - startTime);
    }

    @Benchmark
    @Warmup(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS) // 10 warm up iterations without measures
    @Measurement(iterations = 20, time = 2000, timeUnit = TimeUnit.MILLISECONDS) // 20 iterations of 2000ms each
    public void benchTrackProcheV2() {
        Pays p = new Pays(10);
        ModeRecherche algo = new TrackProchesV2();
        algo.recherche(p, 0);
    }

    @Benchmark
    @Warmup(iterations = 10, time = 2000, timeUnit = TimeUnit.MILLISECONDS) // 10 warm up iterations without measures
    @Measurement(iterations = 20, time = 2000, timeUnit = TimeUnit.MILLISECONDS) // 20 iterations of 2000ms each
    public void benchTrackProcheV2Sleep() {
        Pays p = new Pays(10);
        ModeRecherche algo = new TrackProchesV2();
        algo.recherche(p, 0);
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}