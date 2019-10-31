package projetS3Voyageur.StatsAlgos;

import projetS3Voyageur.Pays;
import projetS3Voyageur.ModesDeRecherches.BrutForceV2;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class VarianceCurrentTime {

    private final ModeRecherche ALGOREFERENCE = new BrutForceV2();
    private final Pays PAYSREFERENCE = new Pays(9);
    private double esperanceCurrentTime = 0.;
    private double esperanceCarreCurrentTime = 0.;

    private int nombreDeTestes;

    /**
     * @param nombreDeTestes
     */
    public VarianceCurrentTime(int nombreDeTestes) {
        this.nombreDeTestes = nombreDeTestes;
    }

    public void calcul() {
        double tempsExecution = TempsExecution.calcule(ALGOREFERENCE, PAYSREFERENCE);
        esperanceCurrentTime += tempsExecution / (nombreDeTestes * 2);
        esperanceCarreCurrentTime += (Math.pow(tempsExecution, 2)) / (nombreDeTestes * 2);
    }

    public double getMargeErreur() {
        double variance = esperanceCarreCurrentTime - Math.pow(esperanceCurrentTime, 2);
        return Math.sqrt(variance) / (esperanceCurrentTime / 100);
    }


}