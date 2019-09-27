package projetS3Voyageur;

import java.util.ArrayList;

public class Graphe {

    private ArrayList<Sommet> sommets;
    private ArrayList<Arrete> arretes;
    private int taille;


    //Constructeurs

    public Graphe(int taille, int nbSommet) {
        assert taille > 0: "la taille doit être strictement positive";

        this.taille = taille;

        this.sommets = new ArrayList<>();
        genereSommet(nbSommet);

        this.arretes = new ArrayList<>();
        genereArretes();
    }


    //Getters & Setters

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public ArrayList<Sommet> getSommets() {
        return sommets;
    }

    public void setSommets(ArrayList<Sommet> sommets) {
        this.sommets = sommets;
    }

    public ArrayList<Arrete> getArretes() {
        return arretes;
    }

    public void setArretes(ArrayList<Arrete> arretes) {
        this.arretes = arretes;
    }


    //Méthodes & Fonctions

    /**
     * @param s: Sommet
     * @return {@code true} ssi le sommet {@code s} a été ajouté correctement.
     */
    public boolean ajouterSommet(Sommet s){
        if(this.sommets.contains(s)){
            return false;
        }
        return this.sommets.add(s);
    }

    /**
     * @param s: Sommet
     * @return {@code true} ssi le sommet {@code s} a été retiré correctement.
     */
    public boolean retirerSommet(Sommet s){
        return this.sommets.remove(s);
    }

    public void genereArretes(){

        for(int i = 0; i < this.sommets.size(); i++){
            Sommet[] autresSommets = new Sommet[this.sommets.size()-1];
            //On ajoute tous les sommets avant le sommet sélectioné
            for(int j = 0; j < i; j++){
                autresSommets[j] = this.sommets.get(j);
            }
            //On ajoute tous les sommets arpès le sommet sélectioné
            for (int j = i+1; j < this.sommets.size(); j++){
                autresSommets[j-1] = this.sommets.get(j);
            }

            //On relie chaque sommet à tous les autres
            for(int j = 0; j < autresSommets.length; j++){
                Arrete a = new Arrete(this, this.sommets.get(i), autresSommets[j]);
                this.sommets.get(i).ajouterArrete(a);
                autresSommets[j].ajouterArrete(a);
            }
        }
    }

    public Sommet getOneSommet(int i){
        return this.sommets.get(i);
    }

    public void genereSommet(int nbSommet){
        for(int i = 0; i < nbSommet; i++){
            this.sommets.add(new Sommet(this));
        }
    }

    @Override
    public String toString() {
        return "Graphe{" +
                "sommets=" + sommets +
                ", taille=" + taille +
                '}';
    }
}
