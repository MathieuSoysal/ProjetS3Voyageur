package projetS3Voyageur;

public class Villes {
    private int[][] positionDesVilles;
    private int nombreDeVilles;
    private int dernierNumVille;

    public Villes(int nombreDeVilles) {
        if (nombreDeVilles < 3 | nombreDeVilles > 15) {
            throw new IndexOutOfBoundsException("Veuillez écrire un nombre de villes compris entre 3 et 15");
        }

        positionDesVilles = new int[nombreDeVilles][nombreDeVilles - 1];

        this.nombreDeVilles = nombreDeVilles;
        this.dernierNumVille = nombreDeVilles - 1;

        for (int i = 0; i < dernierNumVille; i++) {
            positionDesVilles[i][0] = (int) (Math.random() * 50); // 0 = x
            positionDesVilles[i][1] = (int) (Math.random() * 50); // 1 = Y
        }
    }

    /**
     * Enregistre une nouvelle position pour une ville donné en paramètre
     * 
     * @param numVille    Numéro de la ville dont la position vas être modifier
     * @param newPosVille Nouvelle position
     */
    public void setPositionVille(int numVille, Position posVille) {
        verifieNumVille(numVille);
        positionDesVilles[numVille][0] = posVille.getX();
        positionDesVilles[numVille][1] = posVille.getY();

    }

    /**
     * Retourne la localisation d'un numéro de ville donnée en paramètre
     * 
     * @param numVille numéro de la ville à localiser
     * @return {@Code Position} la localisation de la ville
     */
    public Position getPositionVille(int numVille) {
        verifieNumVille(numVille);
        return new Position(positionDesVilles[numVille][0], positionDesVilles[numVille][1]);
    }

    public int getNombreDeVilles() {
        return nombreDeVilles;
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

}
