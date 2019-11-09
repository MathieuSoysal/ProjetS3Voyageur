package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.CompositionPays.Pays;

class BackTrackV1 implements ModeRecherche {
    private static final boolean dejaVisitee = true;
    private static final boolean nonVisitee = false;
    private Pays pays;

    private int villeInitale;
    private int nombreDeVilles;

    private Parcours parcoursOptimum;

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
        villeInitale = villeInitiale;
        nombreDeVilles = pays.getNombreDeVilles();

        boolean villesAVisiter[] = new boolean[nombreDeVilles];
        this.parcoursOptimum = new Parcours(Double.MAX_VALUE, "Parcours par défaut");

        for (int i = 0; i < nombreDeVilles; i++)
            villesAVisiter[i] = nonVisitee;

        rechercheAux(villesAVisiter, villeInitale, 0.0, nombreDeVilles - 1, String.valueOf(villeInitale));
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
     * @param villesEmpruntees  Stock par ordre chronologique le numéro des villes
     *                          empruntées
     */
    private void rechercheAux(boolean villesAVisiter[], final int villeActuel, final double distanceParcourue,
            final int nbVillesAVisiter, final String villesEmpruntees) {

        villesAVisiter[villeActuel] = dejaVisitee;

        if (distanceParcourue < parcoursOptimum.getDistance()) {

            if (nbVillesAVisiter == 0) {
                double distanceParcourueFinal = distanceParcourue
                        + pays.getDistanceEntreVilles(villeActuel, villeInitale);

                if (distanceParcourueFinal < parcoursOptimum.getDistance())
                    parcoursOptimum = new Parcours(distanceParcourueFinal, villesEmpruntees + "->" + villeInitale);

            } else {

                double distanceParcourueActuel;

                for (int villeChoisie = 0; villeChoisie < nombreDeVilles; villeChoisie++) {

                    if (villesAVisiter[villeChoisie] == nonVisitee) {

                        distanceParcourueActuel = distanceParcourue
                                + pays.getDistanceEntreVilles(villeActuel, villeChoisie);

                        rechercheAux(villesAVisiter.clone(), villeChoisie, distanceParcourueActuel,
                                nbVillesAVisiter - 1, villesEmpruntees + "->" + villeChoisie);
                    }

                }
            }
        }

    }

    // #region Getters

    /**
     * @près-requis : Cette méthode doit être exécuté après la méthode recherche().
     * 
     * @return {@code Parcours} parcours le plus optimisé qu'il ai trouvé
     */
    @Override
    public Parcours getParcours() {
        return parcoursOptimum;
    }

    /**
     * Renvois le nom de l'algorithme de recherche
     * 
     * @return {@code String} Nom de l'algorithme
     */
    @Override
    public String getNom() {
        return "BackTrack v1";
    }

    // #endregion Getters

}