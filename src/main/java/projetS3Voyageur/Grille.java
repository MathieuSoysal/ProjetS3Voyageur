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
                    this.grille[i][j] = new Cellule(this, false);
                }else{
                    this.grille[i][j] = new Cellule(this, true);
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


    //Méthodes & Fonctions

    @Override
    public String toString() {
        return "Grille{" +
                "grille=" + Arrays.toString(grille) +
                '}';
    }
}
