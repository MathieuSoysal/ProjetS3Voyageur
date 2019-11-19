package projetS3Voyageur.CompositionPays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.Test;

import projetS3Voyageur.CompositionPays.Villes;

import java.awt.Point;

public class TestVilles {

    final private int nbVille = (int) (Math.random() * 9) + 3;
    final private int derniereVille = nbVille - 1;
    final private int premiereVille = 0;
    private Villes v;

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
    @BeforeClass
    //FIXME : à reglé
    public void init() throws NombreVillesException {
        this.v = new Villes(nbVille);

    }

    @Test
    public void test_getNombredeVilles() throws NombreVillesException {
        init();
        assertEquals(nbVille, v.getNombreDeVilles());
    }

    @Test
    public void test_getPositionNumVille_indexOutOfBound() {
        assertThrows(IndexOutOfBoundsException.class, () -> v.getPositionVille(nbVille));
    }

    // #region test setPosition

    @Test
    public void test_setPositionVille_premiereVille() throws NumVilleException {
        Point nouvellePos = new Point((int) Math.random() * 50, (int) Math.random() * 50);
        v.setPositionVille(premiereVille, nouvellePos);
        assertEquals(nouvellePos.toString(), v.getPositionVille(premiereVille).toString());
    }

    @Test
    public void test_setPositionVille_derniereVille() throws NumVilleException {
        Point nouvellePos = new Point((int) Math.random() * 50, (int) Math.random() * 50);
        v.setPositionVille(derniereVille, nouvellePos);
        assertEquals(nouvellePos.toString(), v.getPositionVille(derniereVille).toString());
    }

    @Test
    public void test_setPositionVille_villeRandom() throws NumVilleException {
        int ville = (int) Math.random() * (nbVille - 1);
        Point nouvellePos = new Point((int) Math.random() * 50, (int) Math.random() * 50);
        v.setPositionVille(ville, nouvellePos);
        assertEquals(nouvellePos.toString(), v.getPositionVille(ville).toString());
    }

    // #endregion test setPosition

}