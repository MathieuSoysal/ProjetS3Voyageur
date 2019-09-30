package projetS3Voyageur;

import java.util.ArrayList;

public class App {

    public static Trajet bruteForce(Graphe graphe, Ville villeDepart){
        Sommet sommetDepart = villeDepart.getSommet();

        ArrayList<Sommet> autresSommets = new ArrayList<>(graphe.getSommets());
        autresSommets.remove(villeDepart.getSommet());

        return getPlusCourt(sommetDepart, autresSommets, sommetDepart);
    }

    private static Trajet getPlusCourt(Sommet s1, ArrayList<Sommet> sommetPossibles, Sommet sDepart) {
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

        Trajet t = bruteForce(g, g.getOneSommet(0).getVille());

        System.out.println("Trajet: " + t);
        System.out.println("Distance: " + t.distance());

    }
}
