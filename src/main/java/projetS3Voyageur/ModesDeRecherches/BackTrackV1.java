package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;

public class BackTrackV1 implements ModeRecherche {
    final boolean dejaVisitee = true;
    final boolean nonVisitee = false;
    final int plusDeVillesAVisiter = 0;
    private Pays pays;

    private int villeInital;
    private int nombreDeVilles;

    private Parcours parcoursOptimum;

    /**
     * Recherche depuis une ville de départ, le parcours le plus optimisé pour
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
            villesAVisiter[i] = nonVisitee;

        rechercheAux(villesAVisiter, villeInital, 0.0, nombreDeVilles - 1, String.valueOf(villeInital));
    }

    /**
     * Recherche récursivement le parcours le plus court possible. En vérifiant que
     * la distance parcourue ne soit pas plus longue que la distance la plus courte
     * enregistré.
     * 
     * @param villesAVisiter    villes qui restent à visiter
     * @param villeActuel       ville dans laquelle se situe l'algo
     * @param distanceParcourue distance parcourue depuis la première itération
     * @param nbVillesAVisiter  Variable marquant la fin de la méthode récursive
     * @param villesEmpruntees    Stock par ordre chronologique le numéro des villes
     *                          empruntées
     */
    private void rechercheAux(boolean villesAVisiter[], int villeActuel, double distanceParcourue, int nbVillesAVisiter,
            String villesEmpruntees) {
        villesAVisiter[villeActuel] = dejaVisitee;

        if (distanceParcourue < parcoursOptimum.getDistance()) {

            if (nbVillesAVisiter == 0) {
                double distanceParcourueFinal = distanceParcourue
                        + pays.getDistanceEntreVilles(villeActuel, villeInital);

                if (distanceParcourueFinal < parcoursOptimum.getDistance())
                    parcoursOptimum = new Parcours(distanceParcourueFinal, villesEmpruntees + "->" + villeInital);

            } else {
                for (int villeChoisie = 0; villeChoisie < nombreDeVilles; villeChoisie++) {

                    if (villesAVisiter[villeChoisie] == nonVisitee) {
                        double distanceParcourueActuel = distanceParcourue
                                + pays.getDistanceEntreVilles(villeActuel, villeChoisie);

                        rechercheAux(villesAVisiter.clone(), villeChoisie, distanceParcourueActuel,
                                nbVillesAVisiter - 1, villesEmpruntees + "->" + villeChoisie);
                    }

                }
            }
        }

    }

    /**
     * Dois être exécuté après la recherche() Retourne le parcours le plus optimisé
     * 
     * @return {@code Parcours}
     */
    public Parcours getParcours() {
        return parcoursOptimum;
    }

    /**
     * Renvois le nom de l'algorithme de recherche
     * 
     * @return {@code String}
     */
    @Override
    public String getNom() {
        return "BackTrack v1";
    }

}