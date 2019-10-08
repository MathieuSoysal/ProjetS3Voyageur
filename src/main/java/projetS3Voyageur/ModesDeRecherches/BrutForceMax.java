package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;

public class BrutForceMax implements ModeRecherche {
    final boolean dejaVisite = true;
    final boolean nonVisite = false;
    final int plusDeVillesAVisiter = 0;
    private Pays pays;

    private int villeInital;
    private int nombreDeVilles;

    private Parcour parcourOptimum;

    public void recherche(Pays pays, int villeDepart) {
        this.pays = pays;
        villeInital = villeDepart;
        nombreDeVilles = pays.getNombreDeVilles();

        boolean villesAVisiter[] = new boolean[nombreDeVilles];
        this.parcourOptimum = new Parcour(Double.MAX_VALUE, "Parcourt par défaut");

        for (int i = 0; i < nombreDeVilles; i++)
            villesAVisiter[i] = nonVisite;

        rechercheAux(villesAVisiter, villeInital, 0.0, nombreDeVilles - 1, String.valueOf(villeInital));
    }

    private void rechercheAux(boolean villesAVisiter[], int villeActuel, double distanceParcourue, int nbVillesAVisiter,
            String villesEmprunté) {
        villesAVisiter[villeActuel] = dejaVisite;

        if (distanceParcourue < parcourOptimum.getDistance()) {

            if (nbVillesAVisiter == 0) {
                double distanceParcourueFinal = distanceParcourue
                        + pays.getDistanceEntreVilles(villeActuel, villeInital);

                if (distanceParcourueFinal < parcourOptimum.getDistance())
                    parcourOptimum = new Parcour(distanceParcourueFinal, villesEmprunté + "->" + villeInital);

            } else {
                for (int villeChoisie = 0; villeChoisie < nombreDeVilles; villeChoisie++) {

                    if (villesAVisiter[villeChoisie] == nonVisite) {
                        double distanceParcourueActuel = distanceParcourue
                                + pays.getDistanceEntreVilles(villeActuel, villeChoisie);

                        rechercheAux(villesAVisiter.clone(), villeChoisie, distanceParcourueActuel,
                                nbVillesAVisiter - 1, villesEmprunté + "->" + villeChoisie);
                    }

                }
            }
        }

    }

    public Parcour getParcour() {
        return parcourOptimum;
    }

}