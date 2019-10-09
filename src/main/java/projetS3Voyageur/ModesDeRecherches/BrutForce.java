package projetS3Voyageur.ModesDeRecherches;
import projetS3Voyageur.*;

public class BrutForce implements ModeRecherche {
    final boolean dejaVisite = true;
    final boolean nonVisite = false;
    final int plusDeVillesAVisiter = 0;
    private Pays pays;

    private int villeInital;
    private int nombreDeVilles;

    private Parcours parcoursOptimum;
    
    public void recherche(Pays pays, int villeDepart) {
        this.pays = pays;
        villeInital = villeDepart;
        nombreDeVilles = pays.getNombreDeVilles();

        boolean villesAVisiter[] = new boolean[nombreDeVilles];
        this.parcoursOptimum = new Parcours(Double.MAX_VALUE, "Parcours par défaut");

        for (int i = 0; i < nombreDeVilles; i++)
            villesAVisiter[i] = nonVisite;

        rechercheAux(villesAVisiter, villeInital, 0.0, nombreDeVilles - 1, String.valueOf(villeInital));
    }

    private void rechercheAux(boolean villesAVisiter[], int villeActuel, double distanceParcourue, int nbVillesAVisiter,
            String villesEmprunté) {

        villesAVisiter[villeActuel] = dejaVisite;

        if (nbVillesAVisiter == 0) {
            double distanceParcourueFinal = distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeInital);

            if (distanceParcourueFinal < parcoursOptimum.getDistance())
                parcoursOptimum = new Parcours(distanceParcourueFinal, villesEmprunté +"->"+ villeInital);

        } else {
            for (int villeChoisie = 0; villeChoisie < nombreDeVilles; villeChoisie++) {

                if (villesAVisiter[villeChoisie] == nonVisite) {
                    double distanceParcourueActuel = distanceParcourue
                            + pays.getDistanceEntreVilles(villeActuel, villeChoisie);

                    rechercheAux(villesAVisiter.clone(), villeChoisie, distanceParcourueActuel, nbVillesAVisiter - 1,
                            villesEmprunté +"->"+ villeChoisie);
                }

            }
        }

    }

    public Parcours getParcour() {
        return parcoursOptimum;
    }

}