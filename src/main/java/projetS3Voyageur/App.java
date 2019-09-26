package projetS3Voyageur;

import java.util.ArrayList;

public class App {

    public Trajet bruteForce(Ville villeDepart){

    }

    private double getPlusCourt(Sommet s1, ArrayList<Sommet> sommetPossibles, Sommet sDepart){
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
    }




    //##########################################################//

    public static void main(String[] args) {


    }
}
