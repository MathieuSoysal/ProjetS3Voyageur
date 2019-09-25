package projetS3Voyageur;

import java.util.ArrayList;

public class Sommet {

    private Ville v;
    ArrayList<Arrete> arretes;


    //Constructeurs

    public Sommet(){
        this.v = new Ville();
        this.arretes = new ArrayList<>();
    }


    //Getters & Setters

    public Ville getV() {
        return v;
    }

    public void setV(Ville v) {
        this.v = v;
    }

    public ArrayList<Arrete> getArretes() {
        return arretes;
    }

    public void setArretes(ArrayList<Arrete> arretes) {
        this.arretes = arretes;
    }


    //Méthodes & Fonctions


}
