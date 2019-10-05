package projetS3Voyageur.MatthiasD;

public class Ville {

    private static int instanceCount = 0;
    private int idVille;
    private Sommet sommet;


    //Constructeur
    public Ville(Sommet sommet) {
        this.idVille = this.instanceCount;
        this.instanceCount++;
        this.sommet = sommet;
    }

    public Ville(Ville v){
        this.idVille = v.getIdVille();
    }


    //Getters & Setters

    public int getIdVille() {
        return idVille;
    }

    public Sommet getSommet() {
        return sommet;
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
