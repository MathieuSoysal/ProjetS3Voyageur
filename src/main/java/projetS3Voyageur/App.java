package projetS3Voyageur;

import projetS3Voyageur.ModesDeRecherches.*;
import projetS3Voyageur.StatsAlgos.Analyser;
import projetS3Voyageur.StatsAlgos.Comparer;
import projetS3Voyageur.StatsAlgos.GenererCSV;

public class App {

    public static void main(String[] args) throws Exception {

        // ModeRecherche[] listeAlgo = {new BrutForce(), new BrutForceV3(), new BrutForceV3_1(),new BrutForceV4(), new BadTrack(), new BadTrackV2(),new TrackProches()};
        // GenererCSV fichierCSV = new GenererCSV(listeAlgo, "Stats.csv");
        // fichierCSV.setTempsMax(7);
        // fichierCSV.setNbVillesMax(20);
        // fichierCSV.Genere();

        // Analyser analyse = new Analyser(new BrutForceV4());
        // analyse.setNbIteration(120);
        //analyse.calculer();
        // analyse.afficher();

        // byte nbVillesMax = (byte) 11;
        // String[] attributs=new String[nbVillesMax+1];
        // attributs[0] = "Algorithmes";
        // for (byte i = 0; i < nbVillesMax; i++) {
        //     attributs[i + 1] = String.valueOf(i + 3);
        // }

        ModeRecherche[] listAlgo = { new BadTrackV2(),new TrackProches(), new PlusProcheV2(), new PlusProche() };
        Comparer compare = new Comparer(listAlgo);
        compare.setNombreDeTest(1000);
        compare.setNombreDeVilles(11);
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
