package projetS3Voyageur.CompositionPays;

class NombreVillesException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -5137140898095310798L;

    public NombreVillesException(int nombreDeVilles) {
        System.err.println("Une nombre de " + nombreDeVilles + " ville" + (nombreDeVilles < 1 ? "" : "s") + " vient dêtre saisie");
        System.err.println("Le nombre de villes doit être comprit entre 3 et 20");
    }

}