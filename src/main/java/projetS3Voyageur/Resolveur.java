package projetS3Voyageur;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Resolveur {

    private Grille g;


    //Constructeurs

    public Resolveur(Grille g) {
        this.g = g;
    }


    //Getters & Setters

    public Grille getG() {
        return g;
    }

    public void setG(Grille g) {
        this.g = g;
    }


    //Méthodes & Fonctions

    public void brutForce(Ville villeDepart) {
        Chemin c = new Chemin(villeDepart);
        ArrayList<Ville> villesPossibles= new ArrayList<>();
        for(int i = 0; i < this.g.getVilles().length; i++){
            villesPossibles.add(this.g.getVilles()[i]);
        }
        villesPossibles.remove(villeDepart);
        c.setVillesPossibles(villesPossibles);
        Chemin res = c.getCheminPlusCourt();
        System.out.println(res);
    }


    @Override
    public String toString() {
        return this.g.toString();
    }
}
