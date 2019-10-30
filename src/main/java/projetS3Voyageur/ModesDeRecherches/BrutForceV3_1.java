package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;

public class BrutForceV3_1 implements ModeRecherche {

    private int toutesVillesVisitees;

    private Pays pays;

    private int villeInitiale;
    private int nombreDeVilles;

    private double distanceOptimum;
    private int[] villesEmprunteesOptimum;

    /**
     * Recherche depuis une ville de départ le parcours le plus optimisé pour
     * visiter toutes les villes d'un pays.
     * 
     * @param pays          Le pays concerné par la recherche
     * @param villeInitiale Le numéro de la ville de départ
     */
    @Override
    public void recherche(Pays pays, int villeInitiale) {
        this.pays = pays;
        this.villeInitiale = villeInitiale;
        nombreDeVilles = pays.getNombreDeVilles();
        toutesVillesVisitees = 1 << nombreDeVilles;
        distanceOptimum = Double.MAX_VALUE;

        rechercheAux(1 << villeInitiale, villeInitiale, 0.0,
                emprunteVille(new int[nombreDeVilles + 1], 0, villeInitiale));
    }

    /**
     * recherche récursivement le parcours le plus court possible.
     * 
     * @param villesVisitees    villes qui ont été visitées
     * @param villeActuelle     ville dans la quelle se situe l'algo
     * @param distanceParcourue distance parcourue depuis la première itération
     * @param villesEmpruntees  Stock par ordre chronologique les numéros des villes
     *                          emprunté
     */
    private void rechercheAux(int villesVisitees, int villeActuelle, double distanceParcourue, int[] villesEmpruntees) {

        // Je prend en compte que la VilleActuell est déjà une ville visité

        if ((villesVisitees + 1) == toutesVillesVisitees) {
            double distanceParcourueFinal = distanceParcourue
                    + pays.getDistanceEntreVilles(villeActuelle, villeInitiale);

            if (distanceParcourueFinal < distanceOptimum) {
                distanceOptimum = distanceParcourueFinal;
                villesEmprunteesOptimum = emprunteVille(villesEmpruntees, nombreDeVilles, villeInitiale);
            }
        } else {

            for (int villeFormatBinaire = villeNonVisitee(1,
                    villesVisitees); villeFormatBinaire < toutesVillesVisitees; villeFormatBinaire = villeNonVisitee(
                            villeFormatBinaire << 1, villesVisitees)) {

                int villeChoisie = Math.getExponent(villeFormatBinaire);

                rechercheAux(villesVisitees + villeFormatBinaire, (villeChoisie),
                        distanceParcourue + pays.getDistanceEntreVilles(villeActuelle, villeChoisie),
                        emprunteVille(villesEmpruntees, Integer.bitCount(villesVisitees), villeChoisie));

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
    private int villeNonVisitee(int villeActuelle, int villesVisitees) {
        villeActuelle += villesVisitees;
        return villeActuelle - (villeActuelle & villesVisitees);
    }

    /**
     * Stock par ordre chronologique la nouvelle ville visitée à la liste des villes
     * visitées.
     * 
     * @param villesEmpruntees  Tableau de int où chaque case représente le numéro
     *                          d'une ville visité
     * 
     * @param indexVilleVisitee index auquel le numéro de la ville visité doit être
     *                          ajouté
     * 
     * @param villeVisitee      Numéro de la ville visitée
     * 
     * @return {@code int[]} Une liste de int contenant par ordre chronologique les
     *         villes empruntées
     */
    private int[] emprunteVille(int[] villesEmpruntees, int indexVilleVisitee, int villeVisitee) {
        villesEmpruntees[indexVilleVisitee] = villeVisitee;
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
        return "BrutForce v3.1";
    }

    // #endregion Getters

}