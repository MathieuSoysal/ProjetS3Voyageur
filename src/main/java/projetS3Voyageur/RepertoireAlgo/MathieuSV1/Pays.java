package projetS3Voyageur.RepertoireAlgo.MathieuSV1;

public class Pays {
    private Villes v;
    private GestionDistance d;

    public Pays(int nombreDeVilles) {
        v = new Villes(nombreDeVilles);
        d = new GestionDistance(v);

    }

    // #region méthodes déléguées

    // #region Villes :

    /**
     * Enregistre une nouvelle position pour une ville donné en paramètre
     * 
     * @param Ville       Numéro de la ville dont la position vas être modifier
     * @param newPosVille Nouvelle position
     */
    public void setPositionVille(int ville, Position posVille) {
        v.setPositionVille(ville, posVille);
        d.actualiseNumVille(ville);
    }

    public Position getPositionVille(int ville) {
        return v.getPositionVille(ville);
    }

    public int getNombreDeVilles() {
        return v.getNombreDeVilles();
    }

    // #endregion Villes

    // #region Distance :

    /**
     * Renvoi la distance entre deux villes
     * 
     * @param numVille1 numéro de la premère ville donné en paramètre
     * @param numVille2 numéro de la seconde ville donné en paramètre
     * @return {@code double}
     */
    public double getDistanceEntreVilles(int ville1, int ville2) {
        return d.getDistance(ville1, ville2);
    }

    // #endregion Distance

    // #endregion Méthodes Déléguées

}