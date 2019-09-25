package projetS3Voyageur;

import java.util.ArrayList;

public class Arbre {

    private Sommet sommet;
    private ArrayList<Arbre> arbres;
    private boolean estDernier;


    //Constructeur

    public Arbre(Sommet sommet){
        this.sommet = sommet;
        this.arbres = new ArrayList<>();
        this.estDernier = true;
    }


    //Getters & Setters

    public Sommet getSommet() {
        return sommet;
    }

    public boolean isEstDernier() {
        return estDernier;
    }


    //Méthodes & Fonctions

    public void ajouterSommet(Sommet sommet){
        this.arbres.add(new Arbre(sommet));
        this.estDernier = false;
    }

    public void retirerSommet(Sommet sommet){
        this.arbres.remove(new Arbre(sommet));
        this.estDernier = this.arbres.isEmpty();
    }

}
