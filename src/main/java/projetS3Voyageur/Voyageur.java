package projetS3Voyageur;

import java.util.ArrayList;

public class Voyageur {

    private Pays pays;
    private ArrayList<Ville> villesAVisiter;
    private Ville villeDepart;


    //Constructeurs

    public Voyageur(Pays pays, Ville villeDepart) {
        this.pays = pays;
        initVillesAVisiter();
        setVilleDepart(villeDepart);
    }


    //Méthodes & Fonctions

    public void initVillesAVisiter() {
        this.villesAVisiter = new ArrayList<>();
        for(int i = 0; i < this.pays.getNbVille(); i++){
            this.villesAVisiter.add(this.pays.getOneVille(i));
        }
    }

    public void setVilleDepart(Ville v){
        this.villeDepart = v;
        this.villesAVisiter.remove(v);
    }

    public Parcours recherche(){
        BruteForce bf = new BruteForce(this.pays, this.villeDepart);
        return bf.recherche();
    }

}
