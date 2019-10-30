package projetS3Voyageur.CompositionPays;

public class Villes {
    private Position[] positionDesVilles;
    private int nombreDeVilles;

    public Villes(int nombreDeVilles) {
        if (nombreDeVilles < 3) {
            throw new /* TODO : utilisais des exceptions pérsonalisée */ IndexOutOfBoundsException("Veuillez écrire un nombre de villes compris entre 3 et 15");
        }
        this.nombreDeVilles = nombreDeVilles;
        positionDesVilles = new Position[nombreDeVilles];

        for (int ville = 0; ville < nombreDeVilles; ville++) {
            positionDesVilles[ville] = new Position((int) (Math.random() * 50), (int) (Math.random() * 50));
        }
    }

    /**
     * Enregistre une nouvelle position pour une ville donnée en paramètre
     * 
     * @param ville       Numéro de la ville dont la position va être modifiée
     * @param newPosVille Nouvelle position
     */
    public void setPositionVille(int ville, Position posVille) {
        verifieNumVille(ville);
        positionDesVilles[ville] = posVille;

    }

    /**
     * Retourne la localisation d'un numéro de ville donnée en paramètre
     * 
     * @param ville numéro de la ville à localiser
     * @return {@Code Position} la localisation de la ville
     */
    public Position getPositionVille(int ville) {
        verifieNumVille(ville);
        return positionDesVilles[ville];
    }

    public int getNombreDeVilles() {
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
