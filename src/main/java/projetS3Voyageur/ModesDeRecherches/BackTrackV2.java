package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.CompositionPays.Pays;

class BackTrackV2 implements ModeRecherche {

    private int toutesVillesVisitees;

    private Pays pays;

    private byte villeInitiale;
    private byte nombreDeVilles;

    private double distanceOptimum;
    private byte[] villesEmprunteesOptimum;

    /**
     * Recherche depuis une ville de départ, le parcours le plus optimisé pour
     * visiter toutes les villes d'un pays.
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
        distanceOptimum = Double.MAX_VALUE;

        rechercheAuxDistance(1 << this.villeInitiale, this.villeInitiale, 0.0);
        rechercheAuxVillesEmpruntees(1 << this.villeInitiale, this.villeInitiale, 0.0,
                emprunteVille(new byte[nombreDeVilles + 1], 0, this.villeInitiale));
    }

    /**
     * Recherche récursivement le parcours le plus court possible. En vérifiant que
     * la distance parcourue n'est pas plus longue que la distance la plus courte
     * enregistré.
     * 
     * @param villesVisitees    Villes qui reste à visitée.
     * @param villeActuelle     Ville où se situe l'algorithme
     * @param distanceParcourue Distance parcourure depuis la première itération
     */
    private void rechercheAuxDistance(final int villesVisitees, final byte villeActuelle,
            final double distanceParcourue) {

        // Je prend en compte que la VilleActuel est déjà une ville visitée
        if (distanceParcourue < distanceOptimum) {
            if (villesVisitees == toutesVillesVisitees) {
                final double distanceParcourueFinal = distanceParcourue
                        + pays.getDistanceEntreVilles(villeActuelle, villeInitiale);

                if (distanceParcourueFinal < distanceOptimum) {
                    distanceOptimum = distanceParcourueFinal;
                }
            } else {

                byte villeChoisie;

                for (int villeFormatBinaire = villeNonVisitee(1,
                        villesVisitees); villeFormatBinaire < toutesVillesVisitees; villeFormatBinaire = villeNonVisitee(
                                villeFormatBinaire << 1, villesVisitees)) {

                    villeChoisie = (byte) (Math.getExponent(villeFormatBinaire));

                    rechercheAuxDistance(villesVisitees + villeFormatBinaire, (villeChoisie),
                            distanceParcourue + pays.getDistanceEntreVilles(villeActuelle, villeChoisie));

                }

            }
        }

    }

    /**
     * Recherche récursivement le parcours le plus court possible. En vérifiant que
     * la distance parcourue n'est pas plus longue que la distance la distance
     * optimum enregistrée via rechercheDistanceAux().
     * 
     * @param villesVisitees    Villes qui ont été visitées depuis la première
     *                          itération
     * 
     * @param villeActuelle     Ville dans laquelle se situe l'algo
     * 
     * @param distanceParcourue Distance parcourue depuis la première itération
     * 
     * @param villesEmpruntees  Stock par ordre chronologique les numéros des villes
     *                          empruntées
     */
    private void rechercheAuxVillesEmpruntees(final int villesVisitees, final byte villeActuelle,
            final double distanceParcourue, final byte[] villesEmpruntees) {

        // Je prend en compte que la VilleActuel est déjà une ville visitée
        if (distanceParcourue < distanceOptimum) {

            if (villesVisitees == toutesVillesVisitees) {
                
                final double distanceParcourueFinal = distanceParcourue
                        + pays.getDistanceEntreVilles(villeActuelle, villeInitiale);

                if (distanceParcourueFinal == distanceOptimum) {
                    distanceOptimum = distanceParcourueFinal;
                    villesEmprunteesOptimum = emprunteVille(villesEmpruntees, nombreDeVilles, villeInitiale);
                }
            } else {

                byte villeChoisie;

                for (int villeFormatBinaire = villeNonVisitee(1,
                        villesVisitees); villeFormatBinaire < toutesVillesVisitees; villeFormatBinaire = villeNonVisitee(
                                villeFormatBinaire << 1, villesVisitees)) {

                    villeChoisie = (byte) Math.getExponent(villeFormatBinaire);

                    rechercheAuxVillesEmpruntees(villesVisitees + villeFormatBinaire, (villeChoisie),
                            distanceParcourue + pays.getDistanceEntreVilles(villeActuelle, villeChoisie),
                            emprunteVille(villesEmpruntees, Integer.bitCount(villesVisitees), villeChoisie));

                }

            }
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
    private int villeNonVisitee(int villeActuelle, final int villesVisitees) {
        villeActuelle += villesVisitees;
        return villeActuelle ^ (villeActuelle & villesVisitees);
    }

    /**
     * Stock par ordre chronologique les villes visitées.
     * 
     * @param villesEmpruntees Tableau de int où chaque case représente le numéro
     *                         d'une ville visité
     * @param index            index à la quelle le numéro de la ville visité doit
     *                         être ajouté
     * @param villeVisitee     Numéro de la ville visitée
     * @return
     */
    private byte[] emprunteVille(byte[] villesEmpruntees, final int index, final byte villeActuelle) {
        villesEmpruntees[index] = villeActuelle;
        return villesEmpruntees.clone();
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
        String villesEmpruntees = String.valueOf(villesEmprunteesOptimum[0]);
        for (int i = 1; i < villesEmprunteesOptimum.length; i++) {
            villesEmpruntees += '>' + String.valueOf(villesEmprunteesOptimum[i]);
        }

        return new Parcours(distanceOptimum, villesEmpruntees);
    }

    /**
     * Renvois le nom de l'algorithme de recherche
     * 
     * @return {@code String} Nom de l'algorithme
     */
    @Override
    public String getNom() {
        return "BackTrack v2";
    }

    // #endregion Getters

}