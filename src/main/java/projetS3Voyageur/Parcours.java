package projetS3Voyageur;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Parcours {

    private double distance;
    private ArrayDeque<Ville> villesEmpruntees;


    //Constructeurs

    public Parcours(double distance, ArrayDeque<Ville> villesEmpruntees){
        this.distance = distance;
        this.villesEmpruntees = villesEmpruntees;
    }

    public Parcours(){
        this.distance = 0;
        this.villesEmpruntees = new ArrayDeque<>();
    }


    //Getters & Setters

    public double getDistance() {
        return distance;
    }

    public ArrayDeque<Ville> getVillesEmpruntees() {
        return villesEmpruntees;
    }


    //Méthodes & Fonctions

    public void ajouterVille(Ville v){
        //On ajoute v à this.villesEmpruntees
        this.villesEmpruntees.offer(v);

        //On rajoute la distance entre la dernière ville de this et v
        //si ce n'est pas la première ville que l'on ajoute
        if(!this.villesEmpruntees.isEmpty()){
            this.distance += this.villesEmpruntees.peekLast().distance(v);
        }
    }

    public void ajouterParcours(Parcours p){
        ArrayDeque<Ville> tmp = p.villesEmpruntees.clone();
        while(!tmp.isEmpty()){
            this.ajouterVille(tmp.poll());
        }
    }

    @Override
    public String toString() {
        return "Parcours{" +
                "distance=" + distance +
                ", villesEmpruntees=" + villesEmpruntees +
                '}';
    }
}
