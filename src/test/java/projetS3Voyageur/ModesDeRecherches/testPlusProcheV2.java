package projetS3Voyageur.ModesDeRecherches;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import projetS3Voyageur.CompositionPays.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;
import projetS3Voyageur.ModesDeRecherches.PlusProcheV2;

import java.awt.Point;

public class testPlusProcheV2 {
    private final ModeRecherche plusProcheV2 = new PlusProcheV2();

    // #region test sur Y
    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_4villes() {

        Pays pays = new Pays(4);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Point(positionX, 2));
        pays.setPositionVille(1, new Point(positionX, 3));
        pays.setPositionVille(2, new Point(positionX, 4));
        pays.setPositionVille(3, new Point(positionX, 5));

        plusProcheV2.recherche(pays, 0);

        long distancePlusProcheV2 = (long) plusProcheV2.getParcours().getDistance();

        assertEquals("0>1>2>3>0", plusProcheV2.getParcours().getVillesEmprunté());
        assertEquals(6, distancePlusProcheV2);

    }

    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_5villes() {

        Pays pays = new Pays(5);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Point(positionX, 2));
        pays.setPositionVille(1, new Point(positionX, 3));
        pays.setPositionVille(2, new Point(positionX, 4));
        pays.setPositionVille(3, new Point(positionX, 5));
        pays.setPositionVille(4, new Point(positionX, 6));

        plusProcheV2.recherche(pays, 0);

        assertEquals("0>1>2>3>4>0", plusProcheV2.getParcours().getVillesEmprunté());
        // assertEquals((1 + 1 + 1 + 1 + 4), (long)
        // plusProcheV2.getParcours().getDistance());

    }

    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_6villes_distance_linaire() {

        Pays pays = new Pays(6);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Point(positionX, 2));
        pays.setPositionVille(1, new Point(positionX, 3));
        pays.setPositionVille(2, new Point(positionX, 4));
        pays.setPositionVille(3, new Point(positionX, 5));
        pays.setPositionVille(4, new Point(positionX, 6));
        pays.setPositionVille(5, new Point(positionX, 7));

        plusProcheV2.recherche(pays, 0);

        assertEquals("0>1>2>3>4>5>0", plusProcheV2.getParcours().getVillesEmprunté());
        assertEquals((10), (long) plusProcheV2.getParcours().getDistance());

    }
    // #endregion test sur Y
    // #region distance linaire sur X

    @Test
    public void test_distanceLinaireSurX_distanceLinaireSurX_ParcourOptimum_Pour_4villes() {

        Pays pays = new Pays(4);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Point(2, positionY));
        pays.setPositionVille(1, new Point(3, positionY));
        pays.setPositionVille(2, new Point(4, positionY));
        pays.setPositionVille(3, new Point(5, positionY));

        plusProcheV2.recherche(pays, 0);

        assertEquals("0>1>2>3>0", plusProcheV2.getParcours().getVillesEmprunté());
        assertEquals(6, (long) plusProcheV2.getParcours().getDistance());

    }

    @Test
    public void test_distanceLinaireSurX_ParcourOptimum_Pour_5villes() {

        Pays pays = new Pays(5);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Point(2, positionY));
        pays.setPositionVille(1, new Point(3, positionY));
        pays.setPositionVille(2, new Point(4, positionY));
        pays.setPositionVille(3, new Point(5, positionY));
        pays.setPositionVille(4, new Point(6, positionY));

        plusProcheV2.recherche(pays, 0);

        assertEquals("0>1>2>3>4>0", plusProcheV2.getParcours().getVillesEmprunté());
        assertEquals(8, (long) plusProcheV2.getParcours().getDistance());

    }

    @Test
    public void test_distanceLinaireSurX_ParcourOptimum_Pour_6villes_distance_linaire() {

        Pays pays = new Pays(6);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Point(2, positionY));
        pays.setPositionVille(1, new Point(3, positionY));
        pays.setPositionVille(2, new Point(4, positionY));
        pays.setPositionVille(3, new Point(5, positionY));
        pays.setPositionVille(4, new Point(6, positionY));
        pays.setPositionVille(5, new Point(7, positionY));

        plusProcheV2.recherche(pays, 0);

        assertEquals("0>1>2>3>4>5>0", plusProcheV2.getParcours().getVillesEmprunté());
        assertEquals(10, (long) plusProcheV2.getParcours().getDistance());

    }

    @Test
    public void test_distanceLinaireSurX_ParcourOptimum_Pour_6villes_distance_linaire_désordre() {

        Pays pays = new Pays(6);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Point(1, positionY));
        pays.setPositionVille(2, new Point(2, positionY));
        pays.setPositionVille(1, new Point(3, positionY));
        pays.setPositionVille(5, new Point(4, positionY));
        pays.setPositionVille(4, new Point(5, positionY));
        pays.setPositionVille(3, new Point(6, positionY));

        plusProcheV2.recherche(pays, 0);

        assertEquals("0>2>1>5>4>3>0", plusProcheV2.getParcours().getVillesEmprunté());
        assertEquals(10, (long) plusProcheV2.getParcours().getDistance());

    }

}