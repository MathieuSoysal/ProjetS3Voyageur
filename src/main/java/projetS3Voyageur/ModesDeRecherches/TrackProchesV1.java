package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;

public class TrackProchesV1 implements ModeRecherche {

    private int overFlow;

    private Pays pays;

    private byte villeInitial;
    private byte nombreDeVilles;

    private double distanceOptimum;
    private byte[] villesEmprunteesOptimum;

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

        rechercheAuxDistanceProche(1 << villeInitial, villeInitial, 0.0);
        rechercheAuxDistance(1 << villeInitial, villeInitial, 0.0);
        rechercheAuxVillesEmpruntees(1 << villeInitial, villeInitial, 0.0,
                emprunteVille(new byte[nombreDeVilles + 1], 0, villeInitial));
    }

    // #region Badtrack v2

    /**
     * Recherche récursivement le parcours le plus court possible. En vérifiant que
     * la distance parcourue ne soit pas plus longue que la distance la plus courte
     * enregistré.
     * 
     * @param villesVisite      Villes qui reste à visitée.
     * @param villeActuel       Ville où se situe l'algorithme
     * @param distanceParcourue Distance parcourure depuis la première itération
     */
    private void rechercheAuxDistance(int villesVisite, byte villeActuel, double distanceParcourue) {

        // Je prend en compte que la VilleActuell est déjà une ville visité

        if (distanceParcourue < distanceOptimum) {
            if (villesVisite == overFlow) {
                double distanceParcourueFinal = distanceParcourue
                        + pays.getDistanceEntreVilles(villeActuel, villeInitial);

                if (distanceParcourueFinal < distanceOptimum) {
                    distanceOptimum = distanceParcourueFinal;
                }
            } else {

                for (int villeFomatBinaire = villeNonVisite(1,
                        villesVisite); villeFomatBinaire < overFlow; villeFomatBinaire = villeNonVisite(
                                villeFomatBinaire << 1, villesVisite)) {

                    byte villeChoisie = (byte) (Math.getExponent(villeFomatBinaire));

                    rechercheAuxDistance(villesVisite + villeFomatBinaire, (villeChoisie),
                            distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeChoisie));

                }

            }
        }

    }
    // #endregion BadTrack v2

    // #region PlusProche v2

    /**
     * Recherche la ville la plus proche parmis les villes non visitées pour y
     * aller.
     * 
     * @param villesVisite      Villes qui ont été visitées jusqu'à présent.
     * @param villeActuel       Ville où se situe l'algorithme.
     * @param distanceParcourue Distance parcourue depuis la première itération
     */
    private void rechercheAuxDistanceProche(int villesVisite, byte villeActuel, double distanceParcourue) {

        // Je prend en compte que la VilleActuell est déjà une ville visité

        if (villesVisite == overFlow) {
            if (distanceOptimum > distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeInitial))
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
            rechercheAuxDistanceProche((villesVisite + (1 << villePlusProche)), (villePlusProche),
                    distanceParcourue + distanceMin);

        }

    }

    // #endregion PlusProche v2

    // #region Récupére parcours effectué

    /**
     * Recherche récusivement le parcours le plus court possible. En vérifiant que
     * la distance parcourue ne soit pas plus longue que la distance la distance
     * optimum enregistré avec rchercheDistanceAux().
     * 
     * @param villesVisite      Villes qui ont étais visité jusqu'à présent
     * @param villeActuel       Ville dans laquelle se situe l'algo
     * @param distanceParcourue Distance parcourue depuis la première itération
     * @param villesEmprunté    Stock par ordre chronologique les numéros des villes
     *                          emprunté
     */
    private void rechercheAuxVillesEmpruntees(int villesVisite, byte villeActuel, double distanceParcourue,
            byte[] villesEmprunté) {

        // Je prend en compte que la VilleActuell est déjà une ville visité
        if (distanceParcourue < distanceOptimum) {

            if (villesVisite == overFlow) {
                double distanceParcourueFinal = distanceParcourue
                        + pays.getDistanceEntreVilles(villeActuel, villeInitial);

                if (distanceParcourueFinal == distanceOptimum) {
                    distanceOptimum = distanceParcourueFinal;
                    villesEmprunteesOptimum = emprunteVille(villesEmprunté, nombreDeVilles, villeInitial);
                }
            } else {

                for (int villeFomatBinaire = villeNonVisite(1,
                        villesVisite); villeFomatBinaire < overFlow; villeFomatBinaire = villeNonVisite(
                                villeFomatBinaire << 1, villesVisite)) {

                    byte villeChoisie = (byte) Math.getExponent(villeFomatBinaire);

                    rechercheAuxVillesEmpruntees(villesVisite + villeFomatBinaire, (villeChoisie),
                            distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeChoisie),
                            emprunteVille(villesEmprunté, Integer.bitCount(villesVisite), villeChoisie));

                }

            }
        }

    }

    // #endregion Récupére parcours

    // #region Outils utile

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
    private byte[] emprunteVille(byte[] villesEmpruntees, int index, byte villeSuivante) {
        villesEmpruntees[index] = villeSuivante;
        return villesEmpruntees.clone();
    }
    // #endregion Outils Utile

    // #region Getters

    /**
     * Dois être éxecuté après la recherche() Retourne le parcous le plus optimisé
     * 
     * @return {@code Parcours}
     */
    public Parcours getParcour() {
        // TODO: Ajouter l'exception avec un getParcours sans avoir fait de recherche
        String villesEmpruntees = String.valueOf(villesEmprunteesOptimum[0]);
        for (int i = 1; i < villesEmprunteesOptimum.length; i++) {
            villesEmpruntees += '>' + String.valueOf(villesEmprunteesOptimum[i]);
        }

        return new Parcours(distanceOptimum, villesEmpruntees);
    }

    /**
     * Renvoi le nom de l'algorithme de recherche
     * 
     * @return {@code String}
     */
    @Override
    public String getNom() {
        return "TrackProches";
    }

    // #endregion Getters

}