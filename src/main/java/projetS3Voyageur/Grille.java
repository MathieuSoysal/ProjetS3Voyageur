package projetS3Voyageur;

import java.util.Arrays;

public class Grille {

    private Cellule[][] grille;


    //Constructeur

    public Grille(int taille, int nbSommet) {
        this.grille = new Cellule[taille][taille];

        //On remplit la grille de cellules vides
        for(int i = 0; i < this.grille.length; i++){
            for(int j = 0; j < this.grille[i].length; j++){
                this.grille[i][j] = new Cellule(this, true, i, j);
            }
        }

        //On mets nbSommet villes dans la grille de manière aléatoire
        for(int i = 0; i < nbSommet; i++){
            int x = Util.randomMinMax(0, this.grille.length-1);
            int y = Util.randomMinMax(0, this.grille[x].length-1);
            while(!this.grille[x][y].isEstVide()){
                x = Util.randomMinMax(0, this.grille.length-1);
                y = Util.randomMinMax(0, this.grille[x].length-1);
            }
            this.grille[x][y].setVille(new Ville(this.grille[x][y]));
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

    public int getNbSommetPlaces() {
        int res = 0;
        for(int i = 0; i < this.grille.length; i++){
            for(int j = 0; j < this.grille[i].length; j++){
                if(!this.grille[i][j].isEstVide()){
                    res++;
                }
            }
        }
        return res;
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
