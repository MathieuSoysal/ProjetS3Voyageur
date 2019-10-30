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

    /**
     * Recherche depuis une ville de départ le parcours pour visiter toutes les
     * villes d'un pays, en allant à la ville la plus proche non visitée.
     */
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

    /**
     * Recherche la villes la plus proche non visitée pour y aller
     * 
     * @param villesAVisiter  Ville qui reste à visiter
     * @param villeActuel     Ville où est situé l'algo
     * @param nbVillesAVisite Nombre de villes qui reste à visiter (variable
     *                        d'arrêt)
     */
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

    // #region Getters

    /**
     * Dois être éxecuté après la recherche() Retourne le parcous le plus optimisé
     * 
     * @return {@code Parcours}
     */
    @Override
    public Parcours getParcours() {

        return new Parcours(distanceParcourue, villesEmpruntee);
    }

    /**
     * Renvoi le nom de l'algorithme de recherche
     * 
     * @return {@code String}
     */
    @Override
    public String getNom() {
        return "PlusProche v1";
    }

    // #endregion Getters

}