package projetS3Voyageur.CompositionPays;

import java.awt.Point;
import java.util.List;

class Villes {
    private Point[] positionDesVilles;
    private int nombreDeVilles;

    Villes(int nombreDeVilles) throws NombreVillesException {
        if (nombreDeVilles < 3) {
            throw new NombreVillesException(nombreDeVilles);
        }
        this.nombreDeVilles = nombreDeVilles;
        positionDesVilles = new Point[nombreDeVilles];

        for (int ville = 0; ville < nombreDeVilles; ville++) {
            positionDesVilles[ville] = new Point((int) (Math.random() * 50), (int) (Math.random() * 50));
        }
    }

    Villes(List<Point> points) {
        this.nombreDeVilles = points.size();
        positionDesVilles = new Point[nombreDeVilles];
        int i = 0;
        for (Point point : points) {
            positionDesVilles[i++] = point;
        }
    }

    /**
     * Enregistre une nouvelle position pour une ville donnée en paramètre
     * 
     * @param ville       Numéro de la ville dont la position va être modifiée
     * @param newPosVille Nouvelle position
     * @throws NumVilleException
     */
    void setPositionVille(int ville, Point posVille) throws NumVilleException {
        verifieNumVille(ville);
        positionDesVilles[ville] = posVille;

    }

    /**
     * Retourne la localisation d'un numéro de ville donnée en paramètre
     * 
     * @param ville numéro de la ville à localiser
     * @return {@Code Position} la localisation de la ville
     * @throws NumVilleException
     */
    Point getPositionVille(int ville) throws NumVilleException {
        verifieNumVille(ville);
        return positionDesVilles[ville];
    }

    int getNombreDeVilles() {
        return nombreDeVilles;
    }

    /**
     * Vérifie si le numéro de la ville saisi est bien valide
     * 
     * @param ville Numéro de la ville à vérifier
     * @throws NumVilleException
     */
    private void verifieNumVille(int ville) throws NumVilleException {
        if (ville < 0 || ville > nombreDeVilles - 1) {
            throw new NumVilleException(ville, nombreDeVilles - 1);
        }
    }

}
