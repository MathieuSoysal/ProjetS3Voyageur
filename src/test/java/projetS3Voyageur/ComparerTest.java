package projetS3Voyageur;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import projetS3Voyageur.ModesDeRecherches.BrutForceV2;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;
import projetS3Voyageur.StatsAlgos.Comparer;

public class ComparerTest {

//TODO: ajouter les autres tests

    @Test
    public void test_temps_execution_moyen() {
        ModeRecherche[] listAlgo = {new BrutForceV2(), new BrutForceV2()};
        Comparer test = new Comparer(listAlgo);
        test.setNombreDeTest(100);
        test.setNombreDeVilles(10);
        test.calculeTempsExecutionBrut();

        double timeAlgo1 = test.getTempsMoyenAlgo(0);
        double timeAlgo2 = test.getTempsMoyenAlgo(1);

        // même si je lance deux fois le même programe il y a une légére fluctuation
        // même sur 100 tests
        assertTrue((Math.abs(timeAlgo1 - timeAlgo2)) < 5);
    }

    
}