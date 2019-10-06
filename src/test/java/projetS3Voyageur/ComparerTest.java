package projetS3Voyageur;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ComparerTest {

    @Test
    public void test_temps_execution_moyen() {
        Comparer test = new Comparer(AlgoLuncher.MathieuS, AlgoLuncher.MathieuS);
        test.setNombreDeTest(100);
        test.setNombreDeVilles(10);
        test.calculeTempsExecutionBrut();

        long timeAlgo1 = test.getTempsMoyenAlgo1();
        long timeAlgo2 = test.getTempsMoyenAlgo2();

        // même si je lance deux fois le même programe il y a une légére fluctuation
        // même sur 100 tests
        assertTrue((Math.abs(timeAlgo1 - timeAlgo2)) < 5);
    }
}