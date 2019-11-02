package projetS3Voyageur.StatsAlgos;

public class BarreChargement {

    private String etapeChargementAttein = "##";
    private String etapeChargementNonAttein = "#.";
    private final String BARRE_CHARGEMENT_INIT = "[#...................................................................................................]";
    private String barreDeChargement;

    private int nombreDeTests;

    /**
     * Récupère le nombre de tests à afin de calculer la proportion d'avancement de
     * la barre de chargement
     * 
     * @param nombreDeTests {@code int} Nombre de tests dont la barre de chargement
     *                       doit représenter l'avancement
     */
    public BarreChargement(int nombreDeTests) {
        this.nombreDeTests = nombreDeTests;
        for (int i = 1; i < (int) ((((double) 1) / ((double) nombreDeTests)) * 100); i++) {
            etapeChargementAttein += '#';
            etapeChargementNonAttein += '.';
        }
        barreDeChargement = BARRE_CHARGEMENT_INIT;
        System.out.print(barreDeChargement);
    }

    /**
     * Selon l'index d'itération actuel fait avancer la barre de chargement d'une
     * étape (étape préalablement calculée)
     * 
     * @param i {@code int} index d'iteration des tests
     */
    public void avancer(int i) {
        int charge = (int) ((((double) i) / ((double) nombreDeTests)) * 100);
        int chargePrecedant = ((int) ((((double) (i - 1)) / ((double) nombreDeTests)) * 100));
        if ((charge - chargePrecedant) != 0) {
            barreDeChargement = barreDeChargement.replace(etapeChargementNonAttein, etapeChargementAttein);
        }
        System.out.print("\r" + barreDeChargement);
    }

}