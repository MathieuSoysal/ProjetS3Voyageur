package projetS3Voyageur;

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
        ArrayList<Chemin> chemins = new ArrayList<>();
        for(int i = 0; i < Util.factorielle(g.getNbSommetPlaces()); i++){
            Chemin c = new Chemin(villeDepart);
            chemins.add(c);
        }

        //Affichage de chemins
        for(int i = 0; i < chemins.size(); i++){
            System.out.println(chemins.get(i));
        }
    }


    @Override
    public String toString() {
        return this.g.toString();
    }
}
