package projetS3Voyageur;

import java.util.LinkedList;

public class Chemin {

    private LinkedList<Ville> villesParcourue;


    //Constructeurs

    public Chemin(Ville villeDepart) {
        this.villesParcourue = new LinkedList<>();
        this.villesParcourue.addFirst(villeDepart);
    }


    //Getters & Setters

    public LinkedList<Ville> getVillesParcourue() {
        return villesParcourue;
    }

    public void setVillesParcourue(LinkedList<Ville> villesParcourue) {
        this.villesParcourue = villesParcourue;
    }


    //Méthodes & Fonctions

    public void ajouterVille(Ville v){
        this.villesParcourue.addFirst(v);
    }

    @Override
    public String toString() {
        return "Chemin{" +
                "villesParcourue=" + villesParcourue +
                '}';
    }
}
