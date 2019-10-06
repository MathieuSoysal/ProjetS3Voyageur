package projetS3Voyageur.RepertoireAlgo.MatthiasDV1;

import java.util.ArrayList;

public class Resolveur {

    private Graphe g;
    private Ville villeDepart;


    //Constructeur

    public Resolveur(Graphe g, Ville villeDepart) {
        this.g = g;
        this.villeDepart = villeDepart;
    }


    //Méthodes & Fonctions

    public Trajet bruteForce(){
        Sommet sommetDepart = this.villeDepart.getSommet();

        ArrayList<Sommet> autresSommets = new ArrayList<>(this.g.getSommets());
        autresSommets.remove(sommetDepart);

        return getPlusCourt(sommetDepart, autresSommets, sommetDepart);
    }

    private Trajet getPlusCourt(Sommet s1, ArrayList<Sommet> sommetPossibles, Sommet sDepart) {
        Trajet t = new Trajet();
        t.ajouterSommet(s1);
        if (sommetPossibles.isEmpty()) {
            t.ajouterSommet(sDepart);
            return t;
        }else {
            double min = Double.MAX_VALUE;
            Trajet court = new Trajet();
            for (int i = 0; i < sommetPossibles.size(); i++) {
                ArrayList<Sommet> autresSommets = new ArrayList<>(sommetPossibles);
                autresSommets.remove(sommetPossibles.get(i));
                double dist = s1.distance(sommetPossibles.get(i)) + this.getPlusCourt(sommetPossibles.get(i), autresSommets, sDepart).distance();
                if (dist <= min) {

                    //t.ajouterTrajet(getPlusCourt(sommetPossibles.get(i), autresSommets, sDepart));
                    min = dist;
                    court = getPlusCourt(sommetPossibles.get(i), autresSommets, sDepart);
                }
            }
            t.ajouterTrajet(court);
            return t;
        }
    }
}
