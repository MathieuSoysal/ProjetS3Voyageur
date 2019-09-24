package projetS3Voyageur;

public class Cellule {

    private boolean estVide;
    private Ville ville;
    private Grille grille;
    private int x;
    private int y;


    //Constructeurs

    public Cellule(Grille grille, boolean estVide, int x, int y) {
        this.grille = grille;
        this.estVide = estVide;
        this.x = x;
        this.y = y;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setEstVide(boolean estVide) {
        this.estVide = estVide;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
        this.estVide = false;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    //Méthodes & Fonctions

    /**
     * @param c: Cellule
     * @return la distance entre @{code this} et @{code c}.
     */
    public double calculDistance(Cellule c) {
        double distX = Math.abs(this.x - c.x);
        double distY = Math.abs(this.y - c.y);
        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
    }

    public double getDistance(Cellule c){
        return Math.sqrt(Math.pow(this.getX() - c.getX(), 2) + Math.pow(this.getY() - c.getY(), 2));
    }

    @Override
    public String toString() {
        if(this.estVide){
            return "--";
        }else{
            return this.ville.toString();
        }
    }
}
