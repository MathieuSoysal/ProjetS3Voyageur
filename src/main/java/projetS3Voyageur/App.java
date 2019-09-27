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
        if(sommetPossibles.isEmpty()){
            t.ajouterSommet(sDepart);
        }else{
            //TODO
        }
        return t;
    }



    //##########################################################//

    public static void main(String[] args) {

        Graphe g = new Graphe(100, 3);

        System.out.println(g);

        System.out.println();

        Trajet t = bruteForce(g, g.getOneSommet(0).getVille());

        System.out.println("Trajet: " + t);
        System.out.println("Distance: " + t.distance());

    }
}
