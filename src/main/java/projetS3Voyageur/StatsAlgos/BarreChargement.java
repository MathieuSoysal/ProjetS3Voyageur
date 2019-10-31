package projetS3Voyageur.StatsAlgos;

public class BarreChargement {

    private String etapeChargementAttein = "##";
    private String etapeChargementNonAttein = "#.";
    private final String BARRE_CHARGEMENT_APPARENCE = "[#...................................................................................................]";
    private String barreDeChargement;

    private int nombreDeTestes;

    public BarreChargement(int nombreDeTestes) {
        this.nombreDeTestes = nombreDeTestes;
        for (int i = 1; i < (int) ((((double) 1) / ((double) nombreDeTestes)) * 100); i++) {
            etapeChargementAttein += '#';
            etapeChargementNonAttein += '.';
        }
        barreDeChargement = BARRE_CHARGEMENT_APPARENCE;
        System.out.print(barreDeChargement);
    }

    public void avancer(int i) {
        int charge = (int) ((((double) i) / ((double) nombreDeTestes)) * 100);
        int chargePrecedant = ((int) ((((double) (i - 1)) / ((double) nombreDeTestes)) * 100));
        if ((charge - chargePrecedant) != 0) {
            barreDeChargement = barreDeChargement.replace(etapeChargementNonAttein, etapeChargementAttein);
        }
        System.out.print("\r" + barreDeChargement);
    }

}