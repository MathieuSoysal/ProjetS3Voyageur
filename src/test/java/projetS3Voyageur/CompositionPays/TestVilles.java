package projetS3Voyageur.CompositionPays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import projetS3Voyageur.CompositionPays.Villes;

import java.awt.Point;


public class TestVilles {

//TODO : à voir s'il y a pas d'autre tests à rajouter 

    final int nbVille = (int) (Math.random() * 9) + 3;
    final int derniereVille = nbVille - 1;
    final int premiereVille = 0;

    // #region Test paramètre non valide

    @Test
    public void test_nbVille_inferieur0() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Villes(-1));
    }

    @Test
    public void test_nbVille_egale0() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Villes(0));
    }


    // #endregion

    Villes v = new Villes(nbVille);

    @Test
    public void test_getNombredeVilles() {
        assertEquals(nbVille, v.getNombreDeVilles());
    }

    @Test
    public void test_getPositionNumVille_indexOutOfBound() {
        assertThrows(IndexOutOfBoundsException.class, () -> v.getPositionVille(nbVille));
    }

    // #region test setPosition

    @Test
    public void test_setPositionVille_premiereVille() {
        Point nouvellePos = new Point((int) Math.random() * 50, (int) Math.random() * 50);
        v.setPositionVille(premiereVille, nouvellePos);
        assertEquals(nouvellePos.toString(), v.getPositionVille(premiereVille).toString());
    }

    @Test
    public void test_setPositionVille_derniereVille() {
        Point nouvellePos = new Point((int) Math.random() * 50, (int) Math.random() * 50);
        v.setPositionVille(derniereVille, nouvellePos);
        assertEquals(nouvellePos.toString(), v.getPositionVille(derniereVille).toString());
    }

    @Test
    public void test_setPositionVille_villeRandom() {
        int ville = (int) Math.random() * (nbVille - 1);
        Point nouvellePos = new Point((int) Math.random() * 50, (int) Math.random() * 50);
        v.setPositionVille(ville, nouvellePos);
        assertEquals(nouvellePos.toString(), v.getPositionVille(ville).toString());
    }

    // #endregion test setPosition

}