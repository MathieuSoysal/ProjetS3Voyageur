package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;

public class BackTrackV2 implements ModeRecherche {

    private int overFlow;

    private Pays pays;

    private byte villeInitial;
    private byte nombreDeVilles;

    private double distanceOptimum;
    private byte[] villesEmprunteesOptimum;

    /**
     * Recherche depuis une ville de départ, le parcours le plus optimisé pour
     * visiter toutes les villes d'un pays.
     */
    @Override
    public void recherche(Pays pays, int villeInitialP) {
        this.pays = pays;
        this.villeInitial = (byte) villeInitialP;
        nombreDeVilles = (byte) pays.getNombreDeVilles();
        overFlow = (1 << nombreDeVilles) - 1;
        distanceOptimum = Double.MAX_VALUE;

        rechercheAuxDistance(1 << villeInitial, villeInitial, 0.0);
        rechercheAuxVillesEmpruntees(1 << villeInitial, villeInitial, 0.0,
                emprunteVille(new byte[nombreDeVilles + 1], 0, villeInitial));
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
    private void rechercheAuxDistance(int villesVisitees, byte villeActuelle, double distanceParcourue) {

        // Je prend en compte que la VilleActuel est déjà une ville visitée
        if (distanceParcourue < distanceOptimum) {
            if (villesVisitees == overFlow) {
                double distanceParcourueFinal = distanceParcourue
                        + pays.getDistanceEntreVilles(villeActuelle, villeInitial);

                if (distanceParcourueFinal < distanceOptimum) {
                    distanceOptimum = distanceParcourueFinal;
                }
            } else {

                for (int villeFormatBinaire = villeNonVisitee(1,
                        villesVisitees); villeFormatBinaire < overFlow; villeFormatBinaire = villeNonVisitee(
                                villeFormatBinaire << 1, villesVisitees)) {

                    byte villeChoisie = (byte) (Math.getExponent(villeFormatBinaire));

                    rechercheAuxDistance(villesVisitees + villeFormatBinaire, (villeChoisie),
                            distanceParcourue + pays.getDistanceEntreVilles(villeActuelle, villeChoisie));

                }

            }
        }

    }

    // TODO: à voir si on peut appliqué DRY
    private void rechercheAuxVillesEmpruntees(int villesVisitees, byte villeActuelle, double distanceParcourue,
            byte[] villesEmpruntees) {

        // Je prend en compte que la VilleActuel est déjà une ville visitée
        if (distanceParcourue < distanceOptimum) {

            if (villesVisitees == overFlow) {
                double distanceParcourueFinal = distanceParcourue
                        + pays.getDistanceEntreVilles(villeActuelle, villeInitial);

                if (distanceParcourueFinal == distanceOptimum) {
                    distanceOptimum = distanceParcourueFinal;
                    villesEmprunteesOptimum = emprunteVille(villesEmpruntees, nombreDeVilles, villeInitial);
                }
            } else {

                for (int villeFormatBinaire = villeNonVisitee(1,
                        villesVisitees); villeFormatBinaire < overFlow; villeFormatBinaire = villeNonVisitee(
                                villeFormatBinaire << 1, villesVisitees)) {

                    byte villeChoisie = (byte) Math.getExponent(villeFormatBinaire);

                    rechercheAuxVillesEmpruntees(villesVisitees + villeFormatBinaire, (villeChoisie),
                            distanceParcourue + pays.getDistanceEntreVilles(villeActuelle, villeChoisie),
                            emprunteVille(villesEmpruntees, Integer.bitCount(villesVisitees), villeChoisie));

                }

            }
        }

    }

    /**
     * @près-requis : Cette méthode doit être exécuté après la méthode recherche().
     * 
     *              Retourne le parcours le plus optimisé qu'il ai trouvé.
     * 
     * @return {@code Parcours} parcours le plus optimisé qu'il ai trouvé
     */
    public Parcours getParcours() {
        // TODO: Ajouter l'exception avec un getParcours sans avoir fait de recherche
        String villesEmpruntees = String.valueOf(villesEmprunteesOptimum[0]);
        for (int i = 1; i < villesEmprunteesOptimum.length; i++) {
            villesEmpruntees += '>' + String.valueOf(villesEmprunteesOptimum[i]);
        }

        return new Parcours(distanceOptimum, villesEmpruntees);
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
    private byte[] emprunteVille(byte[] villesEmpruntees, int index, byte villeActuelle) {
        villesEmpruntees[index] = villeActuelle;
        return villesEmpruntees.clone();
    }

    // #endregion Outils

    @Override
    public String getNom() {
        return "BackTrack v2";
    }

}