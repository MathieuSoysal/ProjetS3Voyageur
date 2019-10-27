package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;

public class PlusProcheV2 implements ModeRecherche {

    private int overFlow;

    private Pays pays;

    private byte villeInitial;
    private byte nombreDeVilles;

    private double distanceOptimum;
    private String villesEmprunteesOptimum;

    /**
     * Recherche depuis une ville de départ le parcours pour visiter toutes les
     * villes d'un pays, en allant à la ville la plus proche non visitée.
     */
    @Override
    public void recherche(Pays pays, int villeInitialP) {
        this.pays = pays;
        this.villeInitial = (byte) villeInitialP;
        nombreDeVilles = (byte) pays.getNombreDeVilles();
        overFlow = (1 << nombreDeVilles) - 1;
        distanceOptimum = Double.MAX_VALUE;
        villesEmprunteesOptimum = String.valueOf(villeInitial);

        rechercheAux(1 << villeInitial, villeInitial, 0.0);

    }

    /**
     * Recherche la ville la plus proche parmis les villes non visitées pour y
     * aller.
     * 
     * @param villesVisite      Villes qui ont été visitées jusqu'à présent.
     * @param villeActuel       Ville où se situe l'algorithme.
     * @param distanceParcourue Distance parcourue depuis la première itération
     */
    private void rechercheAux(int villesVisite, byte villeActuel, double distanceParcourue) {

        // Je prend en compte que la VilleActuell est déjà une ville visité

        if (villesVisite == overFlow) {
            villesEmprunteesOptimum += ">" + villeInitial;
            distanceOptimum = distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeInitial);
        } else {
            double distanceMin = Long.MAX_VALUE;
            byte villePlusProche = 0;
            for (int villeFomatBinaire = villeNonVisite(1,
                    villesVisite); villeFomatBinaire < overFlow; villeFomatBinaire = villeNonVisite(
                            villeFomatBinaire << 1, villesVisite)) {

                byte villeChoisie = (byte) (Math.getExponent(villeFomatBinaire));

                double distanceVilleChoisie = pays.getDistanceEntreVilles(villeActuel, villeChoisie);
                if (distanceVilleChoisie < distanceMin) {
                    distanceMin = distanceVilleChoisie;
                    villePlusProche = villeChoisie;
                }

            }
            villesEmprunteesOptimum += ">" + villePlusProche;
            rechercheAux((villesVisite + (1 << villePlusProche)), (villePlusProche), distanceParcourue + distanceMin);

        }

    }

    // #region Outils

    /**
     * Renvois un type int où chaque bit représente une ville, si un bit 0 elle
     * n'est pas visitée, si un bit vaut 1 elle a été visitée. La méthode récupère
     * les villes visitées et la ville actuelle si la ville actuelle fait partie des
     * villes déjà visitée elle le fait passer à une ville non visitée.
     * 
     * @param villeActuel  Chaque bit du int représente une ville seul l'un des bit
     *                     du int représente la ville actuel
     * @param villesVisite Chaque bit du int représente les villes visité
     * @return {@code int} Renvois un int avec un seul bit à 1, son emplacement
     *         représente une ville non visitée.
     */
    private int villeNonVisite(int villeActuel, int villesVisite) {
        villeActuel += villesVisite;
        return villeActuel ^ (villeActuel & villesVisite);
    }

    // #endregion Outils

    // #region Getters

    /**
     * Renvoi le nom de l'algorithme de recherche
     * 
     * @return {@code String}
     */
    @Override
    public String getNom() {
        return "PlusProche v2";
    }

    /**
     * Dois être éxecuté après la recherche() Retourne le parcous le plus optimisé
     * 
     * @return {@code Parcours}
     */
    public Parcours getParcour() {
        // TODO: Ajouter l'exception avec un getParcours sans avoir fait de recherche

        return new Parcours(distanceOptimum, villesEmprunteesOptimum);
    }

    // #endregion Getters

}