package projetS3Voyageur;

import java.util.ArrayList;

public class Voyageur {

    private Pays pays;
    private ArrayList<Ville> villesAVisiter;


    //Constructeurs

    public Voyageur(Pays pays, Ville villeDepart) {
        this.pays = pays;
        initVillesAVisiter();
        setVilleDepart(villeDepart);
    }


    //Méthodes & Fonctions

    public void initVillesAVisiter() {
        for(int i = 0; i < this.pays.getNbVille(); i++){
            this.villesAVisiter.add(this.pays.getOneVille(i));
        }
    }

    public void setVilleDepart(Ville v){
        this.villesAVisiter.remove(v);
    }

}
