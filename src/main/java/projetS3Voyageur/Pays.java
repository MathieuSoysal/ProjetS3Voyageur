package projetS3Voyageur;

import java.util.Arrays;

public class Pays {

    private Ville[] villes;


    //Constructeurs

    public Pays(int nbVilles){
        if(nbVilles <= 0){
            throw new IllegalArgumentException("Un pays doit avoir au moins une ville !");
        }
        this.villes = new Ville[nbVilles];
        for(int i = 0; i < this.villes.length; i++) {
            this.villes[i] = new Ville();
            this.villes[i].setId(this.villes[i].getId() - this.villes[0].getId());
        }
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


    //Méthodes & Fonctions


    @Override
    public String toString() {
        return "Pays{" +
                "villes=" + Arrays.toString(villes) +
                '}';
    }
}
