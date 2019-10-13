package projetS3Voyageur;

import projetS3Voyageur.CompositionPays.Position;
import projetS3Voyageur.ModesDeRecherches.*;
import projetS3Voyageur.StatsAlgos.Comparer;

public class App {

    public static void main(String[] args) throws Exception {

        Comparer compare = new Comparer(new BrutForceV3_2(), new BrutForce());
        compare.setNombreDeTest(5);
        compare.setNombreDeVilles(12);
        compare.afficher();
    }
}
// Pays france = new Pays(11);
// france.setPositionVille(0, new Position(188, 458));
// france.setPositionVille(1, new Position(141, 799));
// france.setPositionVille(2, new Position(611, 326));
// france.setPositionVille(3, new Position(681, 386));
// france.setPositionVille(4, new Position(255, 790));
// france.setPositionVille(5, new Position(291, 12));
// france.setPositionVille(6, new Position(153, 113));
// france.setPositionVille(7, new Position(133, 685));
// france.setPositionVille(8, new Position(652, 707));
// france.setPositionVille(9, new Position(518, 817));
// france.setPositionVille(10, new Position(25, 104));

// Voyageur mrSmins = new Voyageur(france, 0);

// System.out.println(mrSmins.getParcours(new BrutForce()));

// for (int i = 0; i < 11; i++) {
// System.out.println((int) (Math.random() * 1000));
// }

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
