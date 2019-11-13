package projetS3Voyageur.CompositionPays;

import java.awt.Point;
import java.util.List;

class Villes {
    private Point[] positionDesVilles;
    private int nombreDeVilles;



    Villes(int nombreDeVilles) {
        if (nombreDeVilles < 3) {
            throw new /* TODO : utilisais des exceptions pérsonalisée */ IndexOutOfBoundsException(
                    "Veuillez écrire un nombre de villes compris entre 3 et 15");
        }
        this.nombreDeVilles = nombreDeVilles;
        positionDesVilles = new Point[nombreDeVilles];

        for (int ville = 0; ville < nombreDeVilles; ville++) {
            positionDesVilles[ville] = new Point((int) (Math.random() * 50), (int) (Math.random() * 50));
        }
    }

    Villes(List<Point> points){
        this.nombreDeVilles = points.size();
        positionDesVilles = new Point[nombreDeVilles];
        int i =0;
        for (Point point : points) {
            positionDesVilles[i++] = point;
        }
    }

    /**
     * Enregistre une nouvelle position pour une ville donnée en paramètre
     * 
     * @param ville       Numéro de la ville dont la position va être modifiée
     * @param newPosVille Nouvelle position
     */
    void setPositionVille(int ville, Point posVille) {
        verifieNumVille(ville);
        positionDesVilles[ville] = posVille;

    }

    /**
     * Retourne la localisation d'un numéro de ville donnée en paramètre
     * 
     * @param ville numéro de la ville à localiser
     * @return {@Code Position} la localisation de la ville
     */
    Point getPositionVille(int ville) {
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
     */
    private void verifieNumVille(int ville) {
        if (ville < 0 || ville > nombreDeVilles - 1) {
            throw new IndexOutOfBoundsException(
                    "Le numéro de la ville doit être compris entre 0 et " + (nombreDeVilles - 1));
        }
    }

}
