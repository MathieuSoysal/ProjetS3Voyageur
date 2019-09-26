package projetS3Voyageur;

import java.util.ArrayDeque;

public class Trajet {

    private ArrayDeque<Sommet> sommets;


    //Constructeurs

    public Trajet(){
        this.sommets = new ArrayDeque<>();
    }


    //Getters & Setters

    public ArrayDeque<Sommet> getSommets() {
        return sommets;
    }

    public void setSommets(ArrayDeque<Sommet> sommets) {
        this.sommets = sommets;
    }


    //Méthodes & Fonctions

    public void ajouterSommet(Sommet s){
        if(!this.sommets.contains(s)) {
            this.sommets.addFirst(s);
        }
    }



}
