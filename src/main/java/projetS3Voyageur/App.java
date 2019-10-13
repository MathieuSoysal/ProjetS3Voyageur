package projetS3Voyageur;

import projetS3Voyageur.ModesDeRecherches.*;

public class App {

    public static void main(String[] args) throws Exception {

        // long timeAlgo1 = 0;
        // int nbIteration = 50;
        // for (int i = 0; i < nbIteration; i++) {

            Pays france = new Pays(13);

        //     long timeAlgo = new CurrentCPU(new BrutForce(), pays, 0).getTime();
        //     if (timeAlgo != 0)
        //         timeAlgo1 += timeAlgo;
        //     else
        //         nbIteration--;
        //     // System.out.println(i+"%");
        // }

        // System.out.println(timeAlgo1 / nbIteration);

        Voyageur mrSmins = new Voyageur(france, 0);
        long start = System.currentTimeMillis();
        System.out.println(mrSmins.getParcours(new BrutForceV3()));
        System.out.println(start-System.currentTimeMillis());
        start = System.currentTimeMillis();
        System.out.println(mrSmins.getParcours(new BrutForce()));
        System.out.println(start-System.currentTimeMillis());

    }
}

// double ecart = 0;
// int pourcent = 0;

// int nbIteration = 10000;
// for (int i = 0; i < nbIteration; i++) {
// Pays france = new Pays(10);
// Voyageur mrSmins = new Voyageur(france, 0);
// ModeRecherche plusProche = new PlusProche();
// ModeRecherche bruteForceMax = new BrutForceMax();
// plusProche.recherche(france, 0);
// bruteForceMax.recherche(france, 0);
// ecart += Math.abs(plusProche.getParcour().getDistance() -
// bruteForceMax.getParcour().getDistance()) / nbIteration;
// if (i % (nbIteration/100) == 0)
// System.out.println(pourcent++ + "%");
// }
// System.out.println(ecart);
// }
