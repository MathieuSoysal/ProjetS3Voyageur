package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;

public class PlusProche implements ModeRecherche {

    private static final boolean nonVisite = false;
    private static final boolean visite = true;
    private Pays pays;
    private int villeInital;
    private int nombreDeVilles;

    double distanceParcourue = 0;
    String villesEmpruntee;

    @Override
    public void recherche(Pays pays, int villeDepart) {
        this.pays = pays;
        villeInital = villeDepart;
        nombreDeVilles = pays.getNombreDeVilles();
        boolean villesAVisiter[] = new boolean[nombreDeVilles];

        for (int villeI = 0; villeI < nombreDeVilles; villeI++)
            villesAVisiter[villeI] = nonVisite;

        villesAVisiter[villeInital] = visite;
        villesEmpruntee = String.valueOf(villeInital);

        rechercheAux(villesAVisiter, villeInital, pays.getNombreDeVilles() - 1);

    }

    public void rechercheAux(boolean[] villesAVisiter, int villeActuel, int nbVillesAVisite) {

        if (nbVillesAVisite != 0) {
            int villePlusProche = 1;
            double distanceMin = Double.MAX_VALUE;
            double distanceEntreVilles = 0;
            for (int villeI = 0; villeI < nombreDeVilles; villeI++) {
                distanceEntreVilles = pays.getDistanceEntreVilles(villeActuel, villeI);
                if ((villesAVisiter[villeI] == nonVisite) && (distanceEntreVilles < distanceMin)) {
                    villePlusProche = villeI;
                    distanceMin = distanceEntreVilles;
                }
            }
            villesAVisiter[villePlusProche] = visite;
            distanceParcourue += distanceEntreVilles;
            villesEmpruntee += "->" + villePlusProche;
            rechercheAux(villesAVisiter.clone(), villePlusProche, nbVillesAVisite - 1);
        } else {
            distanceParcourue += pays.getDistanceEntreVilles(villeActuel, villeInital);
            villesEmpruntee += "->" + villeInital;
        }
    }

    @Override
    public Parcour getParcour() {

        return new Parcour(distanceParcourue, villesEmpruntee);
    }

}