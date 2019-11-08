package projetS3Voyageur.ModesDeRecherches;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import projetS3Voyageur.CompositionPays.Pays;
import projetS3Voyageur.CompositionPays.Position;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;
import projetS3Voyageur.ModesDeRecherches.PlusProche;

public class TestPlusProche { 

    // class Obsoléte se référé à la deuxième version
    

    private ModeRecherche plusProche = new PlusProche();

    // #region test sur Y
    @Ignore
    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_4villes() {

        Pays pays = new Pays(4);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(positionX, 2));
        pays.setPositionVille(1, new Position(positionX, 3));
        pays.setPositionVille(2, new Position(positionX, 4));
        pays.setPositionVille(3, new Position(positionX, 5));

        plusProche.recherche(pays, 0);

        assertEquals("0->1->2->3->0", plusProche.getParcours().getVillesEmprunté());
        assertEquals(6, (long) plusProche.getParcours().getDistance());
        

    }

    @Ignore
    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_5villes() {

        Pays pays = new Pays(5);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(positionX, 2));
        pays.setPositionVille(1, new Position(positionX, 3));
        pays.setPositionVille(2, new Position(positionX, 4));
        pays.setPositionVille(3, new Position(positionX, 5));
        pays.setPositionVille(4, new Position(positionX, 6));

        plusProche.recherche(pays, 0);

        assertEquals("0->1->2->3->4->0", plusProche.getParcours().getVillesEmprunté());
        assertEquals(8, (long) plusProche.getParcours().getDistance());

    }

    @Ignore
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

        plusProche.recherche(pays, 0);

        assertEquals("0->1->2->3->4->5->0", plusProche.getParcours().getVillesEmprunté());
        assertEquals(10, (long) plusProche.getParcours().getDistance());

    }

    @Ignore
    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_6villes() {

        Pays pays = new Pays(6);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(positionX, 2));
        pays.setPositionVille(1, new Position(positionX, 3));
        pays.setPositionVille(2, new Position(positionX, 4));
        pays.setPositionVille(3, new Position(positionX, 5));
        pays.setPositionVille(4, new Position(positionX, 6));
        pays.setPositionVille(5, new Position(positionX, 7));

        plusProche.recherche(pays, 0);

        assertEquals("0->1->2->3->4->5->0", plusProche.getParcours().getVillesEmprunté());
        assertEquals(10, (long) plusProche.getParcours().getDistance());

    }
    // #endregion test sur Y
    // #region distance linaire sur X

    @Ignore
    @Test
    public void test_distanceLinaireSurX_distanceLinaireSurX_ParcourOptimum_Pour_4villes() {

        Pays pays = new Pays(4);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(2, positionY));
        pays.setPositionVille(1, new Position(3, positionY));
        pays.setPositionVille(2, new Position(4, positionY));
        pays.setPositionVille(3, new Position(5, positionY));

        plusProche.recherche(pays, 0);

        assertEquals("0->1->2->3->0", plusProche.getParcours().getVillesEmprunté());
        assertEquals(6, (long) plusProche.getParcours().getDistance());

    }

    @Ignore
    @Test
    public void test_distanceLinaireSurX_ParcourOptimum_Pour_5villes() {

        Pays pays = new Pays(5);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(2, positionY));
        pays.setPositionVille(1, new Position(3, positionY));
        pays.setPositionVille(2, new Position(4, positionY));
        pays.setPositionVille(3, new Position(5, positionY));
        pays.setPositionVille(4, new Position(6, positionY));

        plusProche.recherche(pays, 0);

        assertEquals("0->1->2->3->4->0", plusProche.getParcours().getVillesEmprunté());
        assertEquals(8, (long) plusProche.getParcours().getDistance());

    }

    @Ignore
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

        plusProche.recherche(pays, 0);

        assertEquals("0->1->2->3->4->5->0", plusProche.getParcours().getVillesEmprunté());
        assertEquals(10, (long) plusProche.getParcours().getDistance());

    }

}