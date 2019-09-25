package projetS3Voyageur;

public class Ville {

    private static int instanceCount = 0;
    private int idVille;
    //private Cellule cellule;


    //Constructeur
    public Ville(/*Cellule cellule*/) {
        this.instanceCount++;
        this.idVille = this.instanceCount;
        //this.cellule = cellule;
    }

    public Ville(Ville v){
        this.idVille = v.getIdVille();
        //this.cellule = v.getCellule();
    }


    //Getters & Setters

    /*public Cellule getCellule() {
        return cellule;
    }*/

    public int getIdVille() {
        return idVille;
    }


    //Méthodes & Fonctions


    @Override
    public String toString() {
        if(idVille < 10){
            return "0" + idVille;
        }
        return "" + idVille;
    }
}
