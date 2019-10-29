package projetS3Voyageur;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static projetS3Voyageur.OutilsTest.parcoursVilles;

import org.junit.Test;

import projetS3Voyageur.CompositionPays.Position;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;
import projetS3Voyageur.ModesDeRecherches.PlusProche;
import projetS3Voyageur.ModesDeRecherches.PlusProcheV2;

public class testPlusProcheV2 {
    private ModeRecherche plusProchev2 = new PlusProcheV2();
    private ModeRecherche badTrackv1 = new PlusProche();

    // #region test sur Y
    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_4villes() {

        Pays pays = new Pays(4);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(positionX, 2));
        pays.setPositionVille(1, new Position(positionX, 3));
        pays.setPositionVille(2, new Position(positionX, 4));
        pays.setPositionVille(3, new Position(positionX, 5));

        plusProchev2.recherche(pays, 0);

        long distanceBadTrackv1 = (long) badTrackv1.getParcour().getDistance();
        long distanceBadTrackv2 = (long) plusProchev2.getParcour().getDistance();

        assertEquals("0>1>2>3>0", plusProchev2.getParcour().getVillesEmprunté());
        assertEquals(6, distanceBadTrackv2);

    }

    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_5villes() {

        Pays pays = new Pays(5);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(positionX, 2));
        pays.setPositionVille(1, new Position(positionX, 3));
        pays.setPositionVille(2, new Position(positionX, 4));
        pays.setPositionVille(3, new Position(positionX, 5));
        pays.setPositionVille(4, new Position(positionX, 6));

        plusProchev2.recherche(pays, 0);


        assertEquals("0>1>2>3>4>0", plusProchev2.getParcour().getVillesEmprunté());
        assertEquals((1 + 1 + 1 + 1 + 4), (long) plusProchev2.getParcour().getDistance());

    }

    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_6villes_distance_linaire() {

        Pays pays = new Pays(6);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(positionX, 2));
        pays.setPositionVille(1, new Position(positionX, 3));
        pays.setPositionVille(2, new Position(positionX, 4));
        pays.setPositionVille(3, new Position(positionX, 5));
        pays.setPositionVille(4, new Position(positionX, 6));
        pays.setPositionVille(5, new Position(positionX, 7));

        plusProchev2.recherche(pays, 0);

        assertEquals("0>1>2>3>4>5>0", plusProchev2.getParcour().getVillesEmprunté());
        assertEquals((10), (long) plusProchev2.getParcour().getDistance());

    }
    // #endregion test sur Y
    // #region distance linaire sur X

    @Test
    public void test_distanceLinaireSurX_distanceLinaireSurX_ParcourOptimum_Pour_4villes() {

        Pays pays = new Pays(4);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(2, positionY));
        pays.setPositionVille(1, new Position(3, positionY));
        pays.setPositionVille(2, new Position(4, positionY));
        pays.setPositionVille(3, new Position(5, positionY));

        plusProchev2.recherche(pays, 0);

        assertEquals("0>1>2>3>0", plusProchev2.getParcour().getVillesEmprunté());
        assertEquals(6, (long) plusProchev2.getParcour().getDistance());

    }

    @Test
    public void test_distanceLinaireSurX_ParcourOptimum_Pour_5villes() {

        Pays pays = new Pays(5);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(2, positionY));
        pays.setPositionVille(1, new Position(3, positionY));
        pays.setPositionVille(2, new Position(4, positionY));
        pays.setPositionVille(3, new Position(5, positionY));
        pays.setPositionVille(4, new Position(6, positionY));

        plusProchev2.recherche(pays, 0);

        assertEquals("0>1>2>3>4>0", plusProchev2.getParcour().getVillesEmprunté());
        assertEquals(8, (long) plusProchev2.getParcour().getDistance());

    }

    @Test
    public void test_distanceLinaireSurX_ParcourOptimum_Pour_6villes_distance_linaire() {

        Pays pays = new Pays(6);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(2, positionY));
        pays.setPositionVille(1, new Position(3, positionY));
        pays.setPositionVille(2, new Position(4, positionY));
        pays.setPositionVille(3, new Position(5, positionY));
        pays.setPositionVille(4, new Position(6, positionY));
        pays.setPositionVille(5, new Position(7, positionY));

        plusProchev2.recherche(pays, 0);

        assertEquals("0>1>2>3>4>5>0", plusProchev2.getParcour().getVillesEmprunté());
        assertEquals(10, (long) plusProchev2.getParcour().getDistance());

    }

}