package projetS3Voyageur.CompositionPays;

public class NumVilleException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 6496514898527691000L;

    public NumVilleException(int numVille, int numVilleMax) {
        System.err.println("Le numéro de la ville saisie est : " + numVille);
        System.err.println("Or il doit etre comprit entre 0 et "+numVilleMax);
    }

}