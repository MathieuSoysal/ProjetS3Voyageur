package projetS3Voyageur;

import projetS3Voyageur.ModesDeRecherches.*;

public class App {

    public static void main(String[] args) {

        Pays france = new Pays(10);
        Voyageur mrSmins = new Voyageur(france, 0);
         System.out.println(mrSmins.getParcours(new PlusProche()));
         System.out.println(mrSmins.getParcours(new BrutForce()));
         System.out.println(mrSmins.getParcours(new BrutForceMax()));


    }
}
