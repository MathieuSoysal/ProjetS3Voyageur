package projetS3Voyageur;

import java.util.ArrayList;

public class Sommet {

    private Graphe graphe;
    private Ville ville;
    private int x;
    private int y;
    private ArrayList<Arrete> arretes;


    //Constructeurs

    public Sommet(Graphe graphe){
        this.graphe = graphe;
        this.ville = new Ville(this);
        this.arretes = new ArrayList<>();
        this.x = Util.randomMinMax(0, graphe.getTaille());
        this.y = Util.randomMinMax(0, graphe.getTaille());
    }


    //Getters & Setters

    public Graphe getGraphe() {
        return graphe;
    }

    public void setGraphe(Graphe graphe) {
        this.graphe = graphe;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    //Méthodes & Fonctions

    /**
     * @param a: Arrete
     * @return {@code true} ssi l'Arrete {@code a} a bien ajoutée.
     */
    public boolean ajouterArrete(Arrete a){
        if(!this.arretes.contains(a)){
            return false;
        }
        return this.arretes.add(a);
    }

    /**
     * @param s: Sommet
     * @return la distance entre {@code this} et {@code s}.
     */
    public double distance(Sommet s){
        return Math.sqrt(Math.pow(this.x-s.getX(), 2) + Math.pow(this.y-s.getY(), 2));
    }

    @Override
    public String toString() {
        return "Sommet{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
