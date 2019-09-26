package projetS3Voyageur;

import java.util.ArrayList;

public class App {

    public Trajet bruteForce(Ville villeDepart){
        //TODO
        return null;
    }

    /*private double getPlusCourt(Sommet s1, ArrayList<Sommet> sommetPossibles, Sommet sDepart){
        if(sommetPossibles.isEmpty()){
            return s1.distance(sDepart);
        }
        double min = Double.MAX_VALUE;
        for(int i = 0; i < sommetPossibles.size(); i++){
            ArrayList<Sommet> autresSommets = new ArrayList<>(sommetPossibles);
            autresSommets.remove(s1);
            double dist = s1.distance(sommetPossibles.get(i)) + getPlusCourt(sommetPossibles.get(i), autresSommets, sDepart);
            if(dist < min){
                min = dist;
            }
        }
        return min;
    }*/

    private Trajet getPlusCourt(Sommet s1, ArrayList<Sommet> sommetPossibles, Sommet sDepart) {
        Trajet t = new Trajet();
        t.ajouterSommet(s1);
        if (sommetPossibles.isEmpty()) {
            t.ajouterSommet(sDepart);
            return t;
        }
        double min = Double.MAX_VALUE;
        for (int i = 0; i < sommetPossibles.size(); i++) {
            ArrayList<Sommet> autresSommets = new ArrayList<>(sommetPossibles);
            autresSommets.remove(s1);
            double dist = s1.distance(sommetPossibles.get(i)) + getPlusCourt(sommetPossibles.get(i), autresSommets, sDepart).distance();
            if (dist < min) {
                t.ajouterTrajet(getPlusCourt(sommetPossibles.get(i), autresSommets, sDepart));
            }
        }
        return t;
    }



    //##########################################################//

    public static void main(String[] args) {

        Graphe g = new Graphe(100, 2);

        Trajet t = new Trajet();

        t.ajouterSommet(g.getOneSommet(0));
        t.ajouterSommet(g.getOneSommet(1));

        System.out.println("Distance: " + t.distance());
        System.out.println("SommetT 0: " + t.getSommets().poll());
        System.out.println("SommetT 1: " + t.getSommets().poll());

    }
}
