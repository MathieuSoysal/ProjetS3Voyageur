package projetS3Voyageur.CompositionPays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.awt.Point;



public class TestGestionDistance {
    Villes v;
    GestionDistance d;

    @Before
    public void init() {
        v = new Villes(3);
        d = new GestionDistance(v);
    }

    // #region testGetDistance & setDistance

    @Test
    public void test_getDistance_and_actualisation_0_et_1() {
        init();
        int numVille1 = 0;
        int numVille2 = 1;
        Point posVille1 = new Point(10, 20);
        Point posVille2 = new Point(23, 40);

        v.setPositionVille(numVille1, posVille1);
        d.actualiseNumVille(numVille1);
        v.setPositionVille(numVille2, posVille2);
        d.actualiseNumVille(numVille2);
        assertEquals(hypotegnius(posVille1, posVille2), d.getDistance(numVille1, numVille2));
    }

    @Test
    public void test_getDistance_and_actualisation_0_et_2() {
        int numVille1 = 0;
        int numVille2 = 2;
        Point posVille1 = new Point(2, 2);
        Point posVille2 = new Point(2, 3);

        v.setPositionVille(numVille1, posVille1);
        d.actualiseNumVille(numVille1);
        v.setPositionVille(numVille2, posVille2);
        d.actualiseNumVille(numVille2);
        assertEquals(hypotegnius(posVille1, posVille2), d.getDistance(numVille1, numVille2));
    }

    @Test
    public void test_getDistance_and_actualisation_1_et_2() {
        int numVille1 = 1;
        int numVille2 = 2;
        Point posVille1 = new Point(2, 2);
        Point posVille2 = new Point(2, 3);

        v.setPositionVille(numVille1, posVille1);
        d.actualiseNumVille(numVille1);
        v.setPositionVille(numVille2, posVille2);
        d.actualiseNumVille(numVille2);
        assertEquals(hypotegnius(posVille1, posVille2), d.getDistance(numVille1, numVille2));
    }

    // #region Test (getDistance(X,Y) == getDistance(Y,X) ) avec un setPosition
    @Test
    public void test_getDistance_0et1_eguale_getDistance_1et0_avecSetPosition() {
        int numVille1 = 0;
        int numVille2 = 2;
        Point posVille1 = new Point(2, 2);
        Point posVille2 = new Point(2, 3);

        v.setPositionVille(numVille1, posVille1);
        d.actualiseNumVille(numVille1);
        v.setPositionVille(numVille2, posVille2);
        d.actualiseNumVille(numVille2);
        assertEquals(d.getDistance(numVille2, numVille1), d.getDistance(numVille1, numVille2));
    }

    @Test
    public void test_getDistance_2et1_eguale_getDistance_1et2_avecSetPosition() {
        int numVille1 = 1;
        int numVille2 = 2;
        Point posVille1 = new Point(2, 2);
        Point posVille2 = new Point(2, 3);

        v.setPositionVille(numVille1, posVille1);
        d.actualiseNumVille(numVille1);
        v.setPositionVille(numVille2, posVille2);
        d.actualiseNumVille(numVille2);
        assertEquals(d.getDistance(numVille2, numVille1), d.getDistance(numVille1, numVille2));
    }

    @Test
    public void test_getDistance_2et0_eguale_getDistance_0et2_avecSetPosition() {
        int numVille1 = 1;
        int numVille2 = 2;
        Point posVille1 = new Point(2, 2);
        Point posVille2 = new Point(2, 3);

        v.setPositionVille(numVille1, posVille1);
        d.actualiseNumVille(numVille1);
        v.setPositionVille(numVille2, posVille2);
        d.actualiseNumVille(numVille2);
        assertEquals(d.getDistance(numVille2, numVille1), d.getDistance(numVille1, numVille2));
    }

    // #endregion test GetDistance avec un SetPosition

    // #region test getDistance
    @Test
    public void test_getDistance_entre_0_et_2() {
        int numVille1 = 0;
        int numVille2 = 2;

        Point ville1 = v.getPositionVille(numVille1);
        Point ville2 = v.getPositionVille(numVille2);

        assertEquals(hypotegnius(ville1, ville2), d.getDistance(numVille1, numVille2));
    }

    @Test
    public void test_getDistance_entre_1_et_2() {
        int numVille1 = 1;
        int numVille2 = 2;

        Point ville1 = v.getPositionVille(numVille1);
        Point ville2 = v.getPositionVille(numVille2);

        assertEquals(hypotegnius(ville1, ville2), d.getDistance(numVille1, numVille2));
    }

    @Test
    public void test_getDistance_entre_0_et_1() {
        int numVille1 = 1;
        int numVille2 = 2;

        Point ville1 = v.getPositionVille(numVille1);
        Point ville2 = v.getPositionVille(numVille2);

        assertEquals(hypotegnius(ville1, ville2), d.getDistance(numVille1, numVille2));
    }

    @Test
    public void test_EXTREME_getDistance_entre_deux_villes() {

        for (int i = 0; i < 30; i++) {
            Villes villes = new Villes(5);
            GestionDistance gD = new GestionDistance(villes);

            int numVille1 = (int) (Math.random() * 4);
            int numVille2 = (int) (Math.random() * 4);

            Point ville1 = villes.getPositionVille(numVille1);
            Point ville2 = villes.getPositionVille(numVille2);

            assertEquals(hypotegnius(ville1, ville2), gD.getDistance(numVille1, numVille2));
        }
    }

    // #region Test getDistance(X,Y) == getDistance(Y,X)

    @Test
    public void test_getDistance_0et1_eguale_getDistance_1et0() {
        int numVille1 = 0;
        int numVille2 = 1;

        assertEquals(d.getDistance(numVille2, numVille1), d.getDistance(numVille1, numVille2));
    }

    @Test
    public void test_getDistance_0et2_eguale_getDistance_2et0() {
        int numVille1 = 0;
        int numVille2 = 2;

        assertEquals(d.getDistance(numVille2, numVille1), d.getDistance(numVille1, numVille2));
    }

    @Test
    public void test_getDistance_1et2_eguale_getDistance_2et1() {
        int numVille1 = 1;
        int numVille2 = 2;

        assertEquals(d.getDistance(numVille2, numVille1), d.getDistance(numVille1, numVille2));
    }

    // #endregion Test getDistance(X,Y) == getDistance(Y,X)
    // #endregion getDistance

    /**
     * Retourne l'hyptégnius entre deux villes. selon la formule de Pythagore
     * 
     * @param nulVille1 numéro de la première ville en paramètre
     * @param distanceY numéro de la seconde ville en apramètre
     * @return {@code int} retourne l'hyptégnius
     */
    private double hypotegnius(Point ville1, Point ville2) {
        return Math.sqrt(Math.pow(ville1.getX() - ville2.getX(), 2) + Math.pow(ville1.getY() - ville2.getY(), 2));
    }
}