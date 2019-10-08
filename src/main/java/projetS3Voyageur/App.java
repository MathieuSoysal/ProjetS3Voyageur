package projetS3Voyageur;

import projetS3Voyageur.ModesDeRecherches.BrutForce;

public class App {

    public static void main(String[] args) {

        Pays france = new Pays(12);
        Voyageur mrSmins = new Voyageur(france, 0);
         System.out.println(mrSmins.getParcour(new BrutForce()));

    }
}
