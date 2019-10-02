package projetS3Voyageur;

import java.util.ArrayDeque;
import java.util.Objects;

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
        if(!this.sommets.contains(s) || s.equals(this.sommets.peekFirst())){
            this.sommets.addLast(s);
        }
    }

    public void ajouterTrajet(Trajet t){
        while(!t.sommets.isEmpty()){
            this.ajouterSommet(t.getSommets().pollFirst());
        }
    }

    public double distance(){
        ArrayDeque<Sommet> tmp = new ArrayDeque<>(this.sommets);
        double dist = 0;
        for(int i = 0; i < tmp.size()-1; i++){
            dist += tmp.poll().distance(tmp.peek());
        }
        return dist;
    }

    public boolean isEmpty(){
        return this.sommets.isEmpty();
    }

    @Override
    public String toString(){
        return this.sommets.toString();
    }

}
