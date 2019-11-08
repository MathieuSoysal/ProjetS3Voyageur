package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;
import projetS3Voyageur.CompositionPays.Pays;

public class PlusProche implements ModeRecherche {

    private static final boolean nonVisitee = false;
    private static final boolean visitee = true;
    private Pays pays;
    private int villeInitale;
    private int nombreDeVilles;

    double distanceParcourue = 0;
    String villesEmpruntees;

    /**
     * Recherche depuis une ville de départ le parcours pour visiter toutes les
     * villes d'un pays, en allant à la ville la plus proche non visitée.
     * 
     * @param pays          Le pays concerné par la recherche
     * @param villeInitiale Le numéro de la ville de départ
     */
    @Override
    public void recherche(Pays pays, int villeInitiale) {
        this.pays = pays;
        villeInitale = villeInitiale;
        nombreDeVilles = pays.getNombreDeVilles();
        boolean villesAVisiter[] = new boolean[nombreDeVilles];

        for (int villeI = 0; villeI < nombreDeVilles; villeI++)
            villesAVisiter[villeI] = nonVisitee;

        villesAVisiter[villeInitale] = visitee;
        villesEmpruntees = String.valueOf(villeInitale);

        rechercheAux(villesAVisiter, villeInitale, pays.getNombreDeVilles() - 1);

    }

    /**
     * Recherche la villes la plus proche non visitée pour y aller
     * 
     * @param villesAVisiter  Ville qui reste à visiter
     * @param villeActuel     Ville où est situé l'algo
     * @param nbVillesAVisiter Nombre de villes qui reste à visiter (variable
     *                        d'arrêt de la méthode récursive)
     */
    public void rechercheAux(boolean[] villesAVisiter, int villeActuel, int nbVillesAVisiter) {

        if (nbVillesAVisiter != 0) {
            int villePlusProche = 1;
            double distanceMin = Double.MAX_VALUE;
            double distanceEntreVilles = 0;

            for (int villeI = 0; villeI < nombreDeVilles; villeI++) {

                distanceEntreVilles = pays.getDistanceEntreVilles(villeActuel, villeI);

                if ((villesAVisiter[villeI] == nonVisitee) && (distanceEntreVilles < distanceMin)) {
                    villePlusProche = villeI;
                    distanceMin = distanceEntreVilles;
                }
            }

            villesAVisiter[villePlusProche] = visitee;
            distanceParcourue += distanceEntreVilles;
            villesEmpruntees += "->" + villePlusProche;

            rechercheAux(villesAVisiter.clone(), villePlusProche, nbVillesAVisiter - 1);
        } else {
            distanceParcourue += pays.getDistanceEntreVilles(villeActuel, villeInitale);
            villesEmpruntees += "->" + villeInitale;
        }
    }

    // #region Getters

    /**
     * @près-requis : Cette méthode doit être exécuté après la méthode recherche().
     * 
     * @return {@code Parcours} parcours le plus optimisé qu'il ai trouvé
     */
    @Override
    public Parcours getParcours() {

        return new Parcours(distanceParcourue, villesEmpruntees);
    }

    /**
     * Renvois le nom de l'algorithme de recherche
     * 
     * @return {@code String} Nom de l'algorithme
     */
    @Override
    public String getNom() {
        return "PlusProche v1";
    }

    // #endregion Getters

}