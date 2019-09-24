package projetS3Voyageur;

import java.util.ArrayList;

public class Chemin {

    private Ville v;
    private ArrayList<Chemin> chemins;
    private boolean estDernier;
    private ArrayList<Ville> villesPossibles;


    //Constructeurs

    public Chemin(Ville villeDepart){
        this.v = villeDepart;
        this.chemins = new ArrayList<>();
        this.villesPossibles = new ArrayList<>();
        this.estDernier = true;
    }

    public Chemin(Ville villeDepart, ArrayList<Ville> villesPossibles) {
        this.v = villeDepart;
        this.chemins = new ArrayList<>();

        this.villesPossibles = new ArrayList<>();
        for(int i = 0; i < villesPossibles.size(); i++){
            this.villesPossibles.add(villesPossibles.get(i));
        }

        this.estDernier = this.villesPossibles.size() == 0;
    }

    public Chemin(Chemin c){
        this.v = new Ville(c.getVille());
        this.chemins = new ArrayList<>();
        for(int i = 0; i < c.getChemins().size(); i++){
            this.chemins.add(c.getChemins().get(i));
        }
        this.estDernier = c.estDernier;
        this.villesPossibles = new ArrayList<>();
        for(int i = 0; i < c.getVillesPossibles().size(); i++){
            this.villesPossibles.add(c.getVillesPossibles().get(i));
        }
    }


    //Getters & Setters

    public ArrayList<Chemin> getChemins() {
        if(!this.estDernier){
            return this.chemins;
        }else{
            throw new NullPointerException("Ce chemin n'a pas de suite !!!");
        }
    }

    public Ville getVille() {
        return this.v;
    }

    public ArrayList<Ville> getVillesPossibles() {
        return villesPossibles;
    }

    public void setVillesPossibles(ArrayList<Ville> villesPossibles) {
        this.villesPossibles = villesPossibles;
    }

    public Chemin getCheminPlusCourt(){
        this.generateAllChemin();
        if(this.estDernier){
            return this;
        }else{
            double min = Double.MAX_VALUE;
            int indexMin = 0;
            for(int i = 0; i < this.chemins.size(); i++){
                if(this.v.getCellule().getDistance(this.chemins.get(i).getVille().getCellule()) < min){
                    min = this.v.getCellule().getDistance(this.chemins.get(i).getVille().getCellule());
                    indexMin = i;
                }
            }
            Chemin c = new Chemin(this);
            c.ajouterVille(this.villesPossibles.get(indexMin));
            return c;
        }
    }

    //Méthodes & Fonctions

    public void ajouterVille(Ville v, ArrayList<Ville> villesPossibles){
        this.chemins.add(new Chemin(v, villesPossibles));
        this.estDernier = this.chemins.size() == 0;
    }

    public void ajouterVille(Ville v){
        this.chemins.add(new Chemin(v));
        this.estDernier = true;
    }

    public void generateAllChemin() {
        for(int i = 0; i < this.villesPossibles.size(); i++){
            this.ajouterVille(this.villesPossibles.get(i), this.villesPossibles);
            this.chemins.get(i).getVillesPossibles().remove(i);
            if(!this.chemins.get(i).estDernier){
                this.chemins.get(i).generateAllChemin();
            }else{
                this.chemins.get(i).ajouterVille(this.v);
            }

        }
    }

    @Override
    public String toString() {
        return this.v.toString() + "->" + this.chemins.get(0).getVille().toString();
    }
}
