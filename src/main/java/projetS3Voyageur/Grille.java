package projetS3Voyageur;

import java.util.Arrays;

public class Grille {

    private Cellule[][] grille;


    //Constructeur

    public Grille(int taille, int nbSommet) {
        this.grille = new Cellule[taille][taille];
        int i = 0;
        while(nbSommet > 0 && i < this.grille.length){
            int j = 0;
            while(nbSommet > 0 && j < this.grille[i].length){
                if(Util.randomMinMax(1, 20) == 20){
                    this.grille[i][j] = new Cellule(this, false, i, j);
                }else{
                    this.grille[i][j] = new Cellule(this, true, i, j);
                }
                j++;
            }
            i++;
        }
    }


    //Getters & Setters

    public Cellule[][] getGrille() {
        return grille;
    }

    public void setGrille(Cellule[][] grille) {
        this.grille = grille;
    }

    public Cellule getACellule(int x, int y){
        return this.grille[x][y];
    }

    public void setACellule(int x, int y, Cellule c){
        this.grille[x][y] = c;
    }


    //Méthodes & Fonctions

    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < this.grille.length; i++){
            res += "[";
            for(int j = 0; j < this.grille[i].length; j++){
                res += this.grille[i][j] + ", ";
            }
            res = res.substring(0, res.length()-2);
            res += "]\n";
        }
        return res;
    }
}
