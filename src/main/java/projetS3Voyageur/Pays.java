package projetS3Voyageur;

public class Pays {

    private Ville[] villes;


    //Constructeurs

    public Pays(int nbVilles){
        if(nbVilles < 2){
            throw new IllegalArgumentException("Il doit y avoir au moins 2 villes dans un pays");
        }
        this.villes = new Ville[nbVilles];
    }


    //Getters & Setters

    public Ville getOneVille(int indexVille) {
        return this.villes[indexVille];
    }

    public void setPositionVille(int indexVille, int x, int y){
        this.villes[indexVille].setCoords(x, y);
    }

    public int getNbVille(){
        return this.villes.length;
    }

    public double getDistance(int indexVille1, int indexVille2){
        return this.villes[indexVille1].distance(this.villes[indexVille2]);
    }

}
