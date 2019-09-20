package projetS3Voyageur;

public class Ville {

    private static int instanceCount = 0;
    private int idVille;
    private Cellule cellule;


    //Constructeur
    public Ville(Cellule cellule) {
        this.instanceCount++;
        this.idVille = this.instanceCount;
        this.cellule = cellule;
    }


    //Méthodes & Fonctions


    @Override
    public String toString() {
        return "Ville{" +
                "idVille=" + idVille +
                '}';
    }
}
