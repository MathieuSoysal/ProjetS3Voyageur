package projetS3Voyageur.CompositionPays;

import java.awt.Point;
import java.util.List;

public final class Pays {
    private Villes v;
    private GestionDistance d;

    public Pays(int nombreDeVilles) {
        v = new Villes(nombreDeVilles);
        d = new GestionDistance(v);
    }

    public Pays(List<Point> points) {
        v = new Villes(points);
        d = new GestionDistance(v);

    }

    // #region méthodes déléguées

    // #region Villes :

    /**
     * Enregistre une nouvelle position pour une ville donné en paramètre
     * 
     * @param ville {@code int}       Numéro de la ville dont la position vas être modifier
     *                    (commence par 0)
     * 
     * @param posVille {@code Point} Nouvelle position
     */
    public void setPositionVille(int ville, Point posVille) {
        v.setPositionVille(ville, posVille);
        d.actualiseNumVille(ville);
    }

    public Point getPositionVille(int ville) {
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