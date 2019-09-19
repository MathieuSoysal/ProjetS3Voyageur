package projetS3Voyageur;

public class Cellule {

    private boolean estVide;
    private Ville ville;
    private Grille grille;


    //Constructeurs

    public Cellule(Grille grille, boolean estVide) {
        this.grille = grille;
        this.estVide = estVide;
        if(!this.estVide){
            this.ville = new Ville(this);
        }else{
            this.ville = null;
        }
    }

    //Getters & Setters

    public Ville getVille(){
        if(!this.estVide){
            return this.ville;
        }else{
            throw new NullPointerException("Cette case est vide !!!");
        }
    }

    public boolean isEstVide() {
        return estVide;
    }

    public Grille getGrille() {
        return grille;
    }

    public void setEstVide(boolean estVide) {
        this.estVide = estVide;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }


    //Méthodes & Fonctions
}
