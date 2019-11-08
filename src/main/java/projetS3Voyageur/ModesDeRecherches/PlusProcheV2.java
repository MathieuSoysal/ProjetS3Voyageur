package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;
import projetS3Voyageur.CompositionPays.Pays;

public class PlusProcheV2 implements ModeRecherche {

    private int toutesVillesVisitees;

    private Pays pays;

    private byte villeInitiale;
    private byte nombreDeVilles;

    private double distanceOptimum;
    private String villesEmprunteesOptimum;

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
        this.villeInitiale = (byte) villeInitiale;
        nombreDeVilles = (byte) pays.getNombreDeVilles();
        toutesVillesVisitees = (1 << nombreDeVilles) - 1;
        distanceOptimum = Double.MAX_VALUE;
        villesEmprunteesOptimum = String.valueOf(villeInitiale);

        rechercheAux(1 << this.villeInitiale, this.villeInitiale, 0.0);

    }

    /**
     * Recherche la ville la plus proche parmis les villes non visitées pour y
     * aller.
     * 
     * @param villesVisitees    Villes qui ont été visitées jusqu'à présent.
     * @param villeActuelle     Ville où se situe l'algorithme.
     * @param distanceParcourue Distance parcourue depuis la première itération
     */
    private void rechercheAux(int villesVisitees, byte villeActuelle, double distanceParcourue) {

        // Je prend en compte que VilleActuelle est déjà une ville visitée

        if (villesVisitees == toutesVillesVisitees) {
            villesEmprunteesOptimum += ">" + villeInitiale;
            distanceOptimum = distanceParcourue + pays.getDistanceEntreVilles(villeActuelle, villeInitiale);
        } else {
            double distanceMin = Long.MAX_VALUE;
            byte villePlusProche = 0;
            for (int villeFormatBinaire = villeNonVisitee(1,
                    villesVisitees); villeFormatBinaire < toutesVillesVisitees; villeFormatBinaire = villeNonVisitee(
                            villeFormatBinaire << 1, villesVisitees)) {

                byte villeChoisie = (byte) (Math.getExponent(villeFormatBinaire));

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
     * @près-requis : Cette méthode doit être exécuté après la méthode recherche().
     * 
     * @return {@code Parcours} parcours le plus optimisé qu'il ai trouvé
     */
    @Override
    public Parcours getParcours() {
        // TODO: Ajouter l'exception avec un getParcours sans avoir fait de recherche

        return new Parcours(distanceOptimum, villesEmprunteesOptimum);
    }

    /**
     * Renvois le nom de l'algorithme de recherche
     * 
     * @return {@code String} Nom de l'algorithme
     */
    @Override
    public String getNom() {
        return "PlusProche v2";
    }

    // #endregion Getters

}