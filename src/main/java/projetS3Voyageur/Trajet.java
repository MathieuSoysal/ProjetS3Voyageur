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

    public void ajouterTrajet(Trajet t){
        this.sommets.addAll(t.getSommets());
    }

    public double distance(){
        ArrayDeque<Sommet> tmp = new ArrayDeque<>(this.sommets);
        double dist = 0;
        for(int i = 0; i < tmp.size()-1; i++){
            dist += tmp.poll().distance(tmp.peek());
        }
        return dist;
    }

    @Override
    public String toString(){
        return this.sommets.toString();
    }

}
