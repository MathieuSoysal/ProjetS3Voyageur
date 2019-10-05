package projetS3Voyageur.MathieuS;

public class BrutForce {
    final boolean dejaVisite = true;
    final boolean nonVisite = false;
    final int plusDeVillesAVisiter = 0;

    private int villeInital;
    private int nombreDeVilles;

    private Pays pays;
    private Parcour parcourOptimum;

    public BrutForce(Pays pays) {
        this.pays = pays;
        this.villeInital = 0;
        nombreDeVilles = pays.getNombreDeVilles();
    }

    public void recherche() {

        boolean villesAVisiter[] = new boolean[nombreDeVilles];
        this.parcourOptimum = new Parcour(Double.MAX_VALUE, "Parcourt par défaut");

        for (int i = 0; i < nombreDeVilles; i++)
            villesAVisiter[i] = nonVisite;

        rechercheAux(villesAVisiter, villeInital, 0.0, nombreDeVilles - 1, String.valueOf(villeInital));
    }

    private void rechercheAux(boolean villesAVisiter[], int villeActuel, double distanceParcourue, int nbVillesAVisiter,
            String villesEmprunté) {

        villesAVisiter[villeActuel] = dejaVisite;

        if (nbVillesAVisiter == 0) {
            double distanceParcourueFinal = distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeInital);

            if (distanceParcourueFinal < parcourOptimum.getDistance())
                parcourOptimum = new Parcour(distanceParcourueFinal, villesEmprunté + villeInital);

        } else {
            for (int villeChoisie = 0; villeChoisie < nombreDeVilles; villeChoisie++) {

                if (villesAVisiter[villeChoisie] == nonVisite) {
                    double distanceParcourueActuel = distanceParcourue
                            + pays.getDistanceEntreVilles(villeActuel, villeChoisie);

                    rechercheAux(villesAVisiter.clone(), villeChoisie, distanceParcourueActuel, nbVillesAVisiter - 1,
                            villesEmprunté + villeChoisie);
                }

            }
        }

    }

    public Parcour getParcour() {
        return parcourOptimum;
    }

}