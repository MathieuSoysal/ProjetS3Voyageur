package projetS3Voyageur;


public class BrutForce {
    final boolean dejaVisite = true;
    final boolean nonVisite = false;
    final int plusDeVillesAVisiter = 0;

    private int villeInital;

    private Pays pays;
    private Parcour parcourOptimum;

    public BrutForce(Pays pays) {
        this.pays = pays;
        this.villeInital = 0;
    }

    public void recherche() {

        boolean villesAVisiter[] = new boolean[pays.getNombreDeVilles()];

        for (int i = 0; i < pays.getNombreDeVilles(); i++) {
            villesAVisiter[i] = nonVisite;
        }

        this.parcourOptimum = new Parcour(Double.MAX_VALUE, "Non Changé");

        rechercheAux(villesAVisiter, villeInital, 0.0, pays.getNombreDeVilles()-1, villeInital + "");
    }

    private void rechercheAux(boolean villesAVisiter[], int villeDepart, double distanceParcourue, int nbVillesAVisiter,
            String villesEmprunté) {

        villesAVisiter[villeDepart] = dejaVisite;

        if (nbVillesAVisiter == 0) {
            if (distanceParcourue + pays.getDistanceEntreVilles(villeDepart, villeInital) < this.parcourOptimum
                    .getDistance())
                this.parcourOptimum = new Parcour(
                        distanceParcourue + pays.getDistanceEntreVilles(villeDepart, villeInital),
                        villesEmprunté + villeInital);
        } else {
            for (int villeChoisie = 0; villeChoisie < pays.getNombreDeVilles(); villeChoisie++) {
                if (villesAVisiter[villeChoisie] == nonVisite) {

                    rechercheAux(villesAVisiter.clone(), villeChoisie,
                            distanceParcourue + pays.getDistanceEntreVilles(villeDepart, villeChoisie),
                            nbVillesAVisiter-1, villesEmprunté + villeChoisie);
                }

            }
        }

    }




    public Parcour getParcour() {
        return parcourOptimum;
    }

}