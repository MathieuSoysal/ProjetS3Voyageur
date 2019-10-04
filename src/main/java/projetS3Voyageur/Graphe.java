package projetS3Voyageur;

import java.util.ArrayList;

public class Graphe {

    private ArrayList<Sommet> sommets;
    private int taille;


    //Constructeurs

    public Graphe(int taille, int nbSommet) {
        assert taille > 0: "la taille doit être strictement positive";

        this.taille = taille;

        this.sommets = new ArrayList<>();
        genereSommet(nbSommet);
    }


    //Getters & Setters

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public ArrayList<Sommet> getSommets() {
        return sommets;
    }

    public void setSommets(ArrayList<Sommet> sommets) {
        this.sommets = sommets;
    }
    public int getNbSommets(){
        return this.sommets.size();
    }


    //Méthodes & Fonctions

    /**
     * @param s: Sommet
     * @return {@code true} ssi le sommet {@code s} a été ajouté correctement.
     */
    public boolean ajouterSommet(Sommet s){
        if(this.sommets.contains(s)){
            return false;
        }
        return this.sommets.add(s);
    }

    /**
     * @param s: Sommet
     * @return {@code true} ssi le sommet {@code s} a été retiré correctement.
     */
    public boolean retirerSommet(Sommet s){
        return this.sommets.remove(s);
    }


    public Sommet getOneSommet(int i){
        return this.sommets.get(i);
    }

    public void genereSommet(int nbSommet){
        for(int i = 0; i < nbSommet; i++){
            this.sommets.add(new Sommet(this));
        }
    }

    @Override
    public String toString() {
        return "Graphe{" +
                "sommets=" + sommets +
                ", taille=" + taille +
                '}';
    }
}
