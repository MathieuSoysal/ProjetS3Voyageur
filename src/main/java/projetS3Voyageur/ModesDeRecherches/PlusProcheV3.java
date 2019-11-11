package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.CompositionPays.Pays;

public class PlusProcheV3 implements ModeRecherche {

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
    public void recherche(final Pays pays, final int villeInitiale) {
        this.pays = pays;
        this.villeInitiale = (byte) villeInitiale;
        nombreDeVilles = (byte) pays.getNombreDeVilles();
        toutesVillesVisitees = (1 << nombreDeVilles) - 1;
        distanceOptimum = 0;
        villesEmprunteesOptimum = String.valueOf(villeInitiale);

        rechercheAux(1 << this.villeInitiale, this.villeInitiale);

    }

    /**
     * Recherche la ville la plus proche parmis les villes non visitées pour y
     * aller.
     * 
     * @param villesVisitees    Villes qui ont été visitées jusqu'à présent.
     * @param villeActuelle     Ville où se situe l'algorithme.
     */
    private void rechercheAux(int villesVisitees, byte villeActuelle) {

        byte villePlusProche = villeActuelle;

        for (byte numVille = 0; numVille < nombreDeVilles - 1; numVille++) {

            double distanceVillePlusProche = Double.MAX_VALUE;

            for (int villeFormatBinaire2 = villeNonVisitee(1,
                    villesVisitees); villeFormatBinaire2 < toutesVillesVisitees; villeFormatBinaire2 = villeNonVisitee(
                            villeFormatBinaire2 << 1, villesVisitees)) {

                                //TODO : à voir si instancier à chaque fois la variable est 
                final byte villeIteration = (byte) Math.getExponent(villeFormatBinaire2);
                final double distanceIteration = pays.getDistanceEntreVilles(villeActuelle,
                        villeIteration);

                if ((distanceIteration < distanceVillePlusProche)) {
                    villePlusProche = villeIteration;
                    distanceVillePlusProche = distanceIteration;
                }
            }

            villeActuelle = villePlusProche;
            distanceOptimum += distanceVillePlusProche;
            villesVisitees |= 1 << villePlusProche;
            villesEmprunteesOptimum += ">" + villePlusProche;
            //TODO: possible d'utiliser des méthodes privé pour aléger le code

        }

        distanceOptimum += pays.getDistanceEntreVilles(villePlusProche, villeInitiale);
        villesEmprunteesOptimum += ">" + villeInitiale;

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
    private int villeNonVisitee(int villeActuelle, final int villesVisitees) {
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
        return "PlusProche v3";
    }

    // #endregion Getters

}