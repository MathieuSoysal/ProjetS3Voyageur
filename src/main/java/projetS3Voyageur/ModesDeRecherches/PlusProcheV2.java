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
     * @param villesVisitees      Villes qui ont été visitées jusqu'à présent.
     * @param villeActuelle       Ville où se situe l'algorithme.
     * @param distanceParcourue Distance parcourue depuis la première itération
     */
    private void rechercheAux(int villesVisitees, byte villeActuelle, double distanceParcourue) {

        // Je prend en compte que la VilleActuell est déjà une ville visité

        if (villesVisitees == overFlow) {
            villesEmprunteesOptimum += ">" + villeInitial;
            distanceOptimum = distanceParcourue + pays.getDistanceEntreVilles(villeActuelle, villeInitial);
        } else {
            double distanceMin = Long.MAX_VALUE;
            byte villePlusProche = 0;
            for (int villeFomatBinaire = villeNonVisitee(1,
                    villesVisitees); villeFomatBinaire < overFlow; villeFomatBinaire = villeNonVisitee(
                            villeFomatBinaire << 1, villesVisitees)) {

                byte villeChoisie = (byte) (Math.getExponent(villeFomatBinaire));

                double distanceVilleChoisie = pays.getDistanceEntreVilles(villeActuelle, villeChoisie);
                if (distanceVilleChoisie < distanceMin) {
                    distanceMin = distanceVilleChoisie;
                    villePlusProche = villeChoisie;
                }

            }
            villesEmprunteesOptimum += ">" + villePlusProche;
            rechercheAux((villesVisitees + (1 << villePlusProche)), (villePlusProche), distanceParcourue + distanceMin);

        }

    }

    // #region Outils

    /**
     * Renvois un type int où chaque bit représente une ville, si un bit 0 elle
     * n'est pas visitée, si un bit vaut 1 elle a été visitée. La méthode récupère
     * les villes visitées et la ville actuelle si la ville actuelle fait partie des
     * villes déjà visitée elle fait passer la ville actuelle à une ville non
     * visitée.
     * 
     * @param villeActuelle  Chaque bit du int représente une ville seul l'un des
     *                       bits est à 1, elle représente la ville actuelle
     * 
     * @param villesVisitees Chaque bit à 1 du int représente les villes visitées.
     * 
     * @return {@code int} Renvois un int avec un seul bit à 1, son emplacement
     *         (dans la séquence de bits du int) représente une ville non visitée
     *         qui est la nouvelle ville actuelle.
     */
    private int villeNonVisitee(int villeActuelle, int villesVisitees) {
        villeActuelle += villesVisitees;
        return villeActuelle ^ (villeActuelle & villesVisitees);
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
    public Parcours getParcours() {
        // TODO: Ajouter l'exception avec un getParcours sans avoir fait de recherche

        return new Parcours(distanceOptimum, villesEmprunteesOptimum);
    }

    // #endregion Getters

}