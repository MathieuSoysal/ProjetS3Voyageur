package projetS3Voyageur;

public class Pays {
    private Villes v;
    private GestionDistance d;

    public Pays(int nombreDeVilles) {
        if (nombreDeVilles < 3 || nombreDeVilles > 15) {
            throw new IndexOutOfBoundsException("Le nombre de villes saisie doit être comprit entre 3 et 15");
        }

        v = new Villes(nombreDeVilles);

        d = new GestionDistance(v);
    }

    // #region méthodes déléguées

    // #region Villes :

    public void setPositionVille(int numVille, Position posVille) {
        v.setPositionVille(numVille, posVille);
        d.actualiseNumVille(numVille);
    }

    public Position getPositionVille(int numVille) {
        return v.getPositionVille(numVille);
    }

    public int getNombreDeVilles() {
        return v.getNombreDeVilles();
    }

    // #endregion Villes

    // #region Distance :

    public double getDistanceEntreVilles(int numVille1, int numVille2) {
        return d.getDistance(numVille1, numVille2);
    }

    // #endregion Distance

    // #endregion Méthodes Déléguées

}