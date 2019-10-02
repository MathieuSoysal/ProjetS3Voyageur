package projetS3Voyageur;


public class GestionDistance {

    private double distancesV[][]; // tableau de distance entre les villes
    private int nbVilles;
    private Villes villes;

    private int dernierNumVille; // pour facilité la lecture du code

    public GestionDistance(Villes villes) {

        this.villes = villes;
        this.nbVilles = villes.getNombreDeVilles();
        distancesV = new double[nbVilles][nbVilles];

        for (int i = 0; i < nbVilles; i++) {
            for (int j = 0; j < nbVilles; j++) {
                    distancesV[i][j] = hypotegnius(ecartEnX(i, j), ecartEnY(i, j));
            }
        }

    }
    

    /**
     * Renvoi la distance entre deux villes
     * 
     * @param numVille1 numéro de la premère ville donné en paramètre
     * @param numVille2 numéro de la seconde ville donné en paramètre
     * @return {@code}int
     */
    public double getDistance(int numVille1, int numVille2) {
        return distancesV[numVille1][numVille2];
    }

    /**
     * Cette commande est utilisé après un changement de localisation d'une des
     * villes
     * 
     * @param numVille Numéro de la ville dont la localisation à été modifier
     */
    public void actualiseNumVille(int numVille) {
        verifieNumVille(numVille);
        double hypotegnius;

        for (int numVilI = 0; numVilI < nbVilles; numVilI++) {
            if (numVilI != numVille) {
                hypotegnius = hypotegnius(ecartEnX(numVilI, numVille), ecartEnY(numVilI, numVille));
                distancesV[numVilI][numVille] = hypotegnius;
                distancesV[numVille][numVilI] = hypotegnius;

            }
        }
    }

    // #region ces méthodes sont simplement destiné à facilité la lisiblité du code

    /**
     * Retourne l'écart entre deux points sur une même ordonnée X
     * 
     * @param numVille1
     * @param numVille2
     * @return {@code int} ecart sur l'ordonnée x entre les deux villes. Attention
     *         le resultat peut-être négatif
     */
    private int ecartEnX(int numVille1, int numVille2) {
        return villes.getPositionVille(numVille1).getX() - villes.getPositionVille(numVille2).getX();
    }

    /**
     * Retourne l'écart entre deux points sur une même ordonnée y
     * 
     * @param numVille1
     * @param numVille2
     * @return {@code int} ecart sur l'ordonnée y entre les deux villes. Attention
     *         le resultat peut-être négatif
     */
    private int ecartEnY(int numVille1, int numVille2) {
        return villes.getPositionVille(numVille1).getY() - villes.getPositionVille(numVille2).getY();
    }

    /**
     * Retourne l'hyptégnius entre une droit x et une droite y, selon la formule de
     * Pythagore
     * 
     * @param distanceX ecart entre deux points sur l'ordonné x
     * @param distanceY ecart entre deux points sur l'ordonné y
     * @return {@code int} retourne l'hyptégnius
     */
    private double hypotegnius(int distanceX, int distanceY) {
        return  Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
    }

        /**
     * Vérifie si le numéro de la Ville saisi est bien valide
     * 
     * @param numVille numéro de Ville
     */
    private void verifieNumVille(int numVille) {
        if (numVille < 0 && numVille > dernierNumVille) {
            throw new IndexOutOfBoundsException(
                    "Le numéro de la ville doit être compris entre 0 et " + dernierNumVille);
        }
    }

    // #endregion
}