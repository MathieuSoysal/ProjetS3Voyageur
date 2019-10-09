package projetS3Voyageur;

import projetS3Voyageur.ModesDeRecherches.*;

public class App {

    public static void main(String[] args) {

        Pays france = new Pays(10);
        Voyageur mrSmins = new Voyageur(france, 0);
         System.out.println(mrSmins.getParcours(new PlusProche()));
         System.out.println(mrSmins.getParcours(new BrutForceMax()));
         System.out.println(mrSmins.getParcours(new BrutForce()));

    }
}

// double ecart = 0;
// int pourcent = 0;

// int nbIteration = 10000;
// for (int i = 0; i < nbIteration; i++) {
//     Pays france = new Pays(10);
//     Voyageur mrSmins = new Voyageur(france, 0);
//     ModeRecherche plusProche = new PlusProche();
//     ModeRecherche bruteForceMax = new BrutForceMax();
//     plusProche.recherche(france, 0);
//     bruteForceMax.recherche(france, 0);
//     ecart += Math.abs(plusProche.getParcour().getDistance() - bruteForceMax.getParcour().getDistance()) / nbIteration;
//     if (i % (nbIteration/100) == 0)
//         System.out.println(pourcent++ + "%");
// }
// System.out.println(ecart);
// }
