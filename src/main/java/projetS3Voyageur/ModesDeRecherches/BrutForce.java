package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;

public class BrutForce implements ModeRecherche {
    final boolean dejaVisite = true;
    final boolean nonVisite = false;
    final int plusDeVillesAVisiter = 0;
    private Pays pays;

    private int villeInital;
    private int nombreDeVilles;

    private Parcours parcoursOptimum;

    /**
     * Recherche depuis une ville de départ le parcours le plus optimisé pour
     * visiter toutes les villes d'un pays.
     */
    @Override
    public void recherche(Pays pays, int villeDepart) {
        this.pays = pays;
        villeInital = villeDepart;
        nombreDeVilles = pays.getNombreDeVilles();

        boolean villesAVisiter[] = new boolean[nombreDeVilles];
        this.parcoursOptimum = new Parcours(Double.MAX_VALUE, "Parcours par défaut");

        for (int i = 0; i < nombreDeVilles; i++)
            villesAVisiter[i] = nonVisite;

        rechercheAux(villesAVisiter, villeInital, 0.0, nombreDeVilles - 1, String.valueOf(villeInital));
    }

    /**
     * Recherche récursivement le parcours le plus court possible.
     * 
     * @param villesAVisiter    villes qui reste à visité
     * @param villeActuel       ville dans la quelle se situe l'algo
     * @param distanceParcourue distance parcourue depuis la première itération
     * @param nbVillesAVisiter  Variable de fin de la récursiv
     * @param villesEmprunté    Stock par ordre chronologique les numéros des villes
     *                          emprunté
     */
    private void rechercheAux(boolean villesAVisiter[], int villeActuel, double distanceParcourue, int nbVillesAVisiter,
            String villesEmprunté) {

        villesAVisiter[villeActuel] = dejaVisite;

        if (nbVillesAVisiter == 0) {
            double distanceParcourueFinal = distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeInital);

            if (distanceParcourueFinal < parcoursOptimum.getDistance())
                parcoursOptimum = new Parcours(distanceParcourueFinal, villesEmprunté + "->" + villeInital);

        } else {
            for (int villeChoisie = 0; villeChoisie < nombreDeVilles; villeChoisie++) {

                if (villesAVisiter[villeChoisie] == nonVisite) {
                    double distanceParcourueActuel = distanceParcourue
                            + pays.getDistanceEntreVilles(villeActuel, villeChoisie);

                    rechercheAux(villesAVisiter.clone(), villeChoisie, distanceParcourueActuel, nbVillesAVisiter - 1,
                            villesEmprunté + "->" + villeChoisie);
                }

            }
        }

    }

    /**
     * Dois être exécuté après la recherche() Retourne le parcours le plus optimisé
     * 
     * @return {@code Parcours}
     */
    public Parcours getParcour() {
        return parcoursOptimum;
    }

    /**
     * Renvoi le nom de l'algorithme de recherche
     * 
     * @return {@code String}
     */
    @Override
    public String getNom() {
        return "BrutForce";
    }

}