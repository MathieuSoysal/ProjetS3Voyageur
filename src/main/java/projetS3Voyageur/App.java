package projetS3Voyageur;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        Graphe g = new Graphe(100, 6);
        System.err.println(g);

        Resolveur r = new Resolveur(g, g.getOneSommet(0).getVille());

        Trajet t = r.bruteForce();

        System.out.println("Trajet: " + t);
        System.out.println("Distance: " + t.distance());

    }
}
