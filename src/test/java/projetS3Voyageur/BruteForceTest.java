package projetS3Voyageur;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;

import static org.junit.Assert.*;

public class BruteForceTest {

    BruteForce bf;

    @Test
    public void test_repetition() {
        for (int i = 0; i < 5; i++) {
            Pays pays = new Pays(5);
            bf = new BruteForce(pays, pays.getOneVille(0));
            bf.recherche();
        }
    }

    @Test
    public void recherche1() {
        Pays pays = new Pays(5);
        pays.setPositionVille(0, 2, 2);
        pays.setPositionVille(1, 2, 3);
        pays.setPositionVille(2, 2, 4);
        pays.setPositionVille(3, 2, 5);
        pays.setPositionVille(4, 2, 6);

        bf = new BruteForce(pays, pays.getOneVille(0));

        ArrayDeque<Ville> villes = new ArrayDeque<>();
        villes.offer(pays.getOneVille(0));
        villes.offer(pays.getOneVille(1));
        villes.offer(pays.getOneVille(2));
        villes.offer(pays.getOneVille(3));
        villes.offer(pays.getOneVille(4));
        villes.offer(pays.getOneVille(0));

        assertEquals(villes.toString(), bf.recherche().getVillesEmpruntees().toString());
    }
}
