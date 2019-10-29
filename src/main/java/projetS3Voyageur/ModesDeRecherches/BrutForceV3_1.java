package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;

public class BrutForceV3_1 implements ModeRecherche {

    private int overFlow;

    private Pays pays;

    private int villeInitial;
    private int nombreDeVilles;

    private double distanceOptimum;
    private int[] villesEmprunteesOptimum;

    /**
     * Recherche depuis une ville de départ le parcours le plus optimisé pour
     * visiter toutes les villes d'un pays.
     */
    @Override
    public void recherche(Pays pays, int villeInitial) {
        this.pays = pays;
        this.villeInitial = villeInitial;
        nombreDeVilles = pays.getNombreDeVilles();
        overFlow = 1 << nombreDeVilles;
        distanceOptimum = Double.MAX_VALUE;

        rechercheAux(1 << villeInitial, villeInitial, 0.0, emprunteVille(new int[nombreDeVilles + 1], 0, villeInitial));
    }

    /**
     * recherche récursivement le parcours le plus court possible.
     * 
     * @param villesAVisiter    villes qui reste à visité
     * @param villeActuel       ville dans la quelle se situe l'algo
     * @param distanceParcourue distance parcourue depuis la première itération
     * @param nbVillesAVisiter  Variable de fin de la récursiv
     * @param villesEmprunté    Stock par ordre chronologique les numéros des villes
     *                          emprunté
     */
    private void rechercheAux(int villesVisite, int villeActuel, double distanceParcourue, int[] villesEmprunté) {

        // Je prend en compte que la VilleActuell est déjà une ville visité

        if ((villesVisite + 1) == overFlow) {
            double distanceParcourueFinal = distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeInitial);

            if (distanceParcourueFinal < distanceOptimum) {
                distanceOptimum = distanceParcourueFinal;
                villesEmprunteesOptimum = emprunteVille(villesEmprunté, nombreDeVilles, villeInitial);
            }
        } else {

            for (int villeFomatBinaire = villeNonVisite(1,
                    villesVisite); villeFomatBinaire < overFlow; villeFomatBinaire = villeNonVisite(
                            villeFomatBinaire << 1, villesVisite)) {

                int villeChoisie = Math.getExponent(villeFomatBinaire);

                rechercheAux(villesVisite + villeFomatBinaire, (villeChoisie),
                        distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeChoisie),
                        emprunteVille(villesEmprunté, Integer.bitCount(villesVisite), villeChoisie));

            }

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
        return villeActuel - (villeActuel & villesVisite);
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
    private int[] emprunteVille(int[] villesEmpruntees, int index, int villeVisitee) {
        villesEmpruntees[index] = villeVisitee;
        return villesEmpruntees.clone();
    }

    // #endregion Outils

    // #region Getters

    /**
     * Renvois le nom de l'algorithme de recherche
     * 
     * @return {@code String}
     */
    @Override
    public String getNom() {
        return "BrutForce v3.1";
    }

    /**
     * Dois être exécuté après la recherche() Retourne le parcours le plus optimisé
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

    // #endregion Getters

}