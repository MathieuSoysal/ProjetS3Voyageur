package projetS3Voyageur.CompositionPays;

import java.awt.Point;
import java.util.List;

public final class Pays {
    private Villes v;
    private GestionDistance d;

    public Pays(int nombreDeVilles) {
        try {
            v = new Villes(nombreDeVilles);
        } catch (NombreVillesException e) {
            e.printStackTrace();
        }
        try {
            d = new GestionDistance(v);
        } catch (NumVilleException e) {
            e.printStackTrace();
        }

    }

    public Pays(List<Point> points) throws NumVilleException {
        v = new Villes(points);
        d = new GestionDistance(v);

    }

    // #region méthodes déléguées

    // #region Villes :

    /**
     * Enregistre une nouvelle position pour une ville donné en paramètre
     * 
     * @param Ville       Numéro de la ville dont la position vas être modifier
     * @param newPosVille Nouvelle position
     * @throws NumVilleException
     */
    public void setPositionVille(int ville, Point posVille) throws NumVilleException {
        try {
            v.setPositionVille(ville, posVille);
        } catch (NumVilleException e) {
            e.printStackTrace();
        }
        d.actualiseNumVille(ville);
    }

    public Point getPositionVille(int ville) {
        try {
            return v.getPositionVille(ville);
        } catch (NumVilleException e) {
            e.printStackTrace();
            return null;
        }
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