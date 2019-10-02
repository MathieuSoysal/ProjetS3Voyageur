package projetS3Voyageur;

public class Villes {
    private int[][] positionDesVilles;
    private int nombreDeVilles;
    private int derniereVille;

    final int x = 0;
    final int y = 1;
    final int villeSuivante = 1;

    public Villes(int nombreDeVilles) {
        if (nombreDeVilles < 3 | nombreDeVilles > 15) {
            throw new IndexOutOfBoundsException("Veuillez écrire un nombre de villes compris entre 3 et 15");
        }

        positionDesVilles = new int[nombreDeVilles][nombreDeVilles - 1];

        this.nombreDeVilles = nombreDeVilles;
        this.derniereVille = nombreDeVilles - 1;

        for (int ville = 0; ville != derniereVille; ville += villeSuivante) {
            positionDesVilles[ville][x] = (int) (Math.random() * 50); // x
            positionDesVilles[ville][y] = (int) (Math.random() * 50); // Y
        }
    }

    /**
     * Enregistre une nouvelle position pour une ville donné en paramètre
     * 
     * @param ville    Numéro de la ville dont la position vas être modifier
     * @param newPosVille Nouvelle position
     */
    public void setPositionVille(int ville, Position posVille) {
        verifieNumVille(ville);
        positionDesVilles[ville][x] = posVille.getX();
        positionDesVilles[ville][y] = posVille.getY();

    }

    /**
     * Retourne la localisation d'un numéro de ville donnée en paramètre
     * 
     * @param ville numéro de la ville à localiser
     * @return {@Code Position} la localisation de la ville
     */
    public Position getPositionVille(int ville) {
        verifieNumVille(ville);
        return new Position(positionDesVilles[ville][x], positionDesVilles[ville][y]);
    }

    public int getNombreDeVilles() {
        return nombreDeVilles;
    }

    /**
     * Vérifie si le numéro de la ville saisi est bien valide
     * 
     * @param ville numéro de ville
     */
    private void verifieNumVille(int ville) {
        if (ville < 0 || ville > derniereVille) {
            throw new IndexOutOfBoundsException(
                    "Le numéro de la ville doit être compris entre 0 et " + derniereVille);
        }
    }

}
