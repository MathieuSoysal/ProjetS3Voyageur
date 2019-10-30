package projetS3Voyageur.CompositionPays;

import static java.lang.Math.hypot;

public class GestionDistance {

    private double distancesVille[][]; // tableau de distance entre les villes
    private int nbVilles;
    private Villes villes;

    private int dernierNumVille; // pour faciliter la lecture du code

    public GestionDistance(Villes villes) {

        this.villes = villes;
        this.nbVilles = villes.getNombreDeVilles();
        distancesVille = new double[nbVilles][nbVilles];

        for (int i = 0; i < nbVilles; i++) {
            for (int j = 0; j < nbVilles; j++) {
                distancesVille[i][j] = hypot(ecartEnX(i, j), ecartEnY(i, j));
            }
        }

    }

    /**
     * Renvois la distance entre deux villes
     * 
     * @param ville1 Numéro de la première ville donnée en paramètre
     * @param ville2 Numéro de la seconde ville donnée en paramètre
     * @return {@code double} La distance entre les deux villes
     */
    public double getDistance(int ville1, int ville2) {
        return distancesVille[ville1][ville2];
    }

    /**
     * Cette commande est utilisée après un changement de localisation d'une des
     * villes
     * 
     * @param numVille Numéro de la ville dont la localisation à été modifier
     */
    public void actualiseNumVille(int numVille) {
        verifieNumVille(numVille);
        double hypotegnius;

        for (int villeI = 0; villeI < nbVilles; villeI++) {
            if (villeI != numVille) {
                hypotegnius = hypot(ecartEnX(villeI, numVille), ecartEnY(villeI, numVille));
                distancesVille[villeI][numVille] = hypotegnius;
                distancesVille[numVille][villeI] = hypotegnius;

            }
        }
    }

    // #region ces méthodes sont simplement destinées à faciliter la lisibilité du code

    /**
     * Retourne l'écart entre deux points sur une même ordonner X
     * 
     * @param ville1
     * @param ville2
     * @return {@code int} Écart sur l'ordonnée x entre les deux villes. Attention
     *         le résultat peut être négatif
     */
    private int ecartEnX(int ville1, int ville2) {
        return villes.getPositionVille(ville1).getX() - villes.getPositionVille(ville2).getX();
    }

    /**
     * Retourne l'écart entre deux points sur une même ordonner y
     * 
     * @param ville1
     * @param ville2
     * @return {@code int} Écart sur l'ordonnée y entre les deux villes. Attention
     *         le résultat peut être négatif
     */
    private int ecartEnY(int ville1, int ville2) {
        return villes.getPositionVille(ville1).getY() - villes.getPositionVille(ville2).getY();
    }

    /**
     * Vérifie si le numéro de la Ville saisi est bien valide
     * 
     * @param numVille Numéro de la ville à vérifier
     */
    private void verifieNumVille(int numVille) {
        if (numVille < 0 && numVille > dernierNumVille) {
            throw new IndexOutOfBoundsException(
                    "Le numéro de la ville doit être compris entre 0 et " + dernierNumVille);
        }
    }

    // #endregion
}