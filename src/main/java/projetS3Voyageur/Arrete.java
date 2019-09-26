package projetS3Voyageur;

public class Arrete {

    Graphe graphe;
    Sommet sommet1;
    Sommet sommet2;
    double distance;


    //Constructeurs

    public Arrete(Graphe graphe, Sommet sommet1, Sommet sommet2) {
        this.graphe = graphe;
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
        this.distance = sommet1.distance(sommet2);
    }


    //Getters & Setters

    public Graphe getGraphe() {
        return graphe;
    }

    public void setGraphe(Graphe graphe) {
        this.graphe = graphe;
    }

    public Sommet getSommet1() {
        return sommet1;
    }

    public void setSommet1(Sommet sommet1) {
        this.sommet1 = sommet1;
    }

    public Sommet getSommet2() {
        return sommet2;
    }

    public void setSommet2(Sommet sommet2) {
        this.sommet2 = sommet2;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    //Méthodes & Fonctions

    public Sommet getAutreSommet(Sommet s){
        if(this.sommet1.equals(s)){
            return this.sommet2;
        }else if(this.sommet2.equals(s)){
            return this.sommet1;
        }else{
            throw new IllegalArgumentException("Le sommet doit être un des deux sommets de l'arrête!");
        }
    }



}
