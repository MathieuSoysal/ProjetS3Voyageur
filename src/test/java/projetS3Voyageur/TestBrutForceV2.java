package projetS3Voyageur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static projetS3Voyageur.OutilsTest.parcoursVilles;

import org.junit.Test;

import projetS3Voyageur.CompositionPays.Position;
import projetS3Voyageur.ModesDeRecherches.BrutForceV2;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class TestBrutForceV2 {
    private ModeRecherche brutForceV2 = new BrutForceV2();

    @Test
    public void test_si_les_villes_ont_des_positions_random() {

        brutForceV2.recherche(new Pays(6), 0);

        String parcours1 = brutForceV2.getParcours().getVillesEmprunté();

        brutForceV2.recherche(new Pays(6), 0);

        assertNotEquals(parcours1, brutForceV2.getParcours().getVillesEmprunté());

    }

    // #region distance linaire sur Y

    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_4villes() {

        Pays pays = new Pays(4);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(positionX, 2));
        pays.setPositionVille(1, new Position(positionX, 3));
        pays.setPositionVille(2, new Position(positionX, 4));
        pays.setPositionVille(3, new Position(positionX, 5));

        brutForceV2.recherche(pays, 0);

        assertEquals("0->1->2->3->0", brutForceV2.getParcours().getVillesEmprunté());
        assertEquals(brutForceV2.getParcours().getDistance(),
                parcoursVilles(pays, brutForceV2.getParcours().getVillesEmprunté(),"->"));

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

        brutForceV2.recherche(pays, 0);

        assertEquals("0->1->2->3->4->0", brutForceV2.getParcours().getVillesEmprunté());
        assertEquals(brutForceV2.getParcours().getDistance(),
                parcoursVilles(pays, brutForceV2.getParcours().getVillesEmprunté(),"->"));

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

        brutForceV2.recherche(pays, 0);

        assertEquals("0->1->2->3->4->5->0", brutForceV2.getParcours().getVillesEmprunté());
        assertEquals(brutForceV2.getParcours().getDistance(),
                parcoursVilles(pays, brutForceV2.getParcours().getVillesEmprunté(),"->"));
    }

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

        brutForceV2.recherche(pays, 0);

        assertEquals("0->1->2->3->4->5->0", brutForceV2.getParcours().getVillesEmprunté());
        assertEquals(brutForceV2.getParcours().getDistance(),
                parcoursVilles(pays, brutForceV2.getParcours().getVillesEmprunté(),"->"));
    }

    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_6villes_desorde() {

        Pays pays = new Pays(6);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(positionX, 2));
        pays.setPositionVille(1, new Position(positionX, 3));
        pays.setPositionVille(2, new Position(positionX, 4));
        pays.setPositionVille(3, new Position(positionX, 5));
        pays.setPositionVille(4, new Position(positionX, 6));
        pays.setPositionVille(5, new Position(positionX, 7));

        brutForceV2.recherche(pays, 0);

        double distanceMinimum = brutForceV2.getParcours().getDistance();

        pays.setPositionVille(0, new Position(positionX, 2));
        pays.setPositionVille(4, new Position(positionX, 3));
        pays.setPositionVille(5, new Position(positionX, 4));
        pays.setPositionVille(3, new Position(positionX, 5));
        pays.setPositionVille(1, new Position(positionX, 6));
        pays.setPositionVille(2, new Position(positionX, 7));

        brutForceV2.recherche(pays, 0);

        assertEquals(distanceMinimum, brutForceV2.getParcours().getDistance());
        assertEquals(brutForceV2.getParcours().getDistance(),
                parcoursVilles(pays, brutForceV2.getParcours().getVillesEmprunté(),"->"));
    }

    @Test // TODO: Précision se test est possible de fair en sorte qu'il soit plus rapide
    public void test_EXTREME_distanceLinaireSurY_ParcourOptimum_Pour_6villes_on_connais_la_distance_en_avance() {

        Pays pays = new Pays(6);

        for (int i = 0; i < 30; i++) {

            int positionX = (int) (Math.random() * 50);

            int[] t = genereTableauDeIntRandom(6);

            pays.setPositionVille(1, new Position(positionX, t[1]));
            pays.setPositionVille(2, new Position(positionX, t[2]));
            pays.setPositionVille(3, new Position(positionX, t[3]));
            pays.setPositionVille(0, new Position(positionX, t[0]));
            pays.setPositionVille(4, new Position(positionX, t[4]));
            pays.setPositionVille(5, new Position(positionX, t[5]));

            brutForceV2.recherche(pays, 0);

            assertEquals(calculeDistanceLinaire(t), brutForceV2.getParcours().getDistance());
        }
        assertEquals(brutForceV2.getParcours().getDistance(),
                parcoursVilles(pays, brutForceV2.getParcours().getVillesEmprunté(),"->"));

    }

    // #endregion distance linaire sur Y

    // #region distance linaire sur X

    @Test
    public void test_distanceLinaireSurX_distanceLinaireSurX_ParcourOptimum_Pour_4villes() {

        Pays pays = new Pays(4);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(2, positionY));
        pays.setPositionVille(1, new Position(3, positionY));
        pays.setPositionVille(2, new Position(4, positionY));
        pays.setPositionVille(3, new Position(5, positionY));

        brutForceV2.recherche(pays, 0);

        assertEquals("0->1->2->3->0", brutForceV2.getParcours().getVillesEmprunté());
        assertEquals(brutForceV2.getParcours().getDistance(),
                parcoursVilles(pays, brutForceV2.getParcours().getVillesEmprunté(),"->"));
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

        brutForceV2.recherche(pays, 0);

        assertEquals("0->1->2->3->4->0", brutForceV2.getParcours().getVillesEmprunté());
        assertEquals(brutForceV2.getParcours().getDistance(),
                parcoursVilles(pays, brutForceV2.getParcours().getVillesEmprunté(),"->"));
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

        brutForceV2.recherche(pays, 0);

        assertEquals("0->1->2->3->4->5->0", brutForceV2.getParcours().getVillesEmprunté());
        assertEquals(brutForceV2.getParcours().getDistance(),
                parcoursVilles(pays, brutForceV2.getParcours().getVillesEmprunté(),"->"));
    }

    @Test
    public void test_distanceLinaireSurX_ParcourOptimum_Pour_6villes_desorde() {

        Pays pays = new Pays(6);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Position(2, positionY));
        pays.setPositionVille(1, new Position(3, positionY));
        pays.setPositionVille(2, new Position(4, positionY));
        pays.setPositionVille(3, new Position(5, positionY));
        pays.setPositionVille(4, new Position(6, positionY));
        pays.setPositionVille(5, new Position(7, positionY));

        brutForceV2.recherche(pays, 0);

        double distanceMinimum = brutForceV2.getParcours().getDistance();

        pays.setPositionVille(0, new Position(2, positionY));
        pays.setPositionVille(4, new Position(3, positionY));
        pays.setPositionVille(5, new Position(4, positionY));
        pays.setPositionVille(3, new Position(5, positionY));
        pays.setPositionVille(1, new Position(6, positionY));
        pays.setPositionVille(2, new Position(7, positionY));

        brutForceV2.recherche(pays, 0);

        assertEquals(distanceMinimum, brutForceV2.getParcours().getDistance());
        assertEquals(brutForceV2.getParcours().getDistance(),
                parcoursVilles(pays, brutForceV2.getParcours().getVillesEmprunté(),"->"));
    }

    @Test // TODO: Précision se test est possible de fair en sorte qu'il soit plus rapide
    public void test_EXTREME_distanceLinaireSurX_ParcourOptimum_Pour_6villes() {

        Pays pays = new Pays(6);

        for (int i = 0; i < 30; i++) {

            int positionY = (int) (Math.random() * 50);

            int[] t = genereTableauDeIntRandom(6);

            pays.setPositionVille(0, new Position(t[0], positionY));
            pays.setPositionVille(1, new Position(t[1], positionY));
            pays.setPositionVille(2, new Position(t[2], positionY));
            pays.setPositionVille(3, new Position(t[3], positionY));
            pays.setPositionVille(4, new Position(t[4], positionY));
            pays.setPositionVille(5, new Position(t[5], positionY));

            brutForceV2.recherche(pays, 0);

            assertEquals(calculeDistanceLinaire(t), brutForceV2.getParcours().getDistance());
        }
        assertEquals(brutForceV2.getParcours().getDistance(),
                parcoursVilles(pays, brutForceV2.getParcours().getVillesEmprunté(),"->"));

    }
    // #endregion distance linaire sur X

    private double calculeDistanceLinaire(int[] tableauDeIntRandom) {
        double resultat = 0;
        for (int i = 0; i < tableauDeIntRandom.length; i++) {
            resultat += Math.abs(tableauDeIntRandom[i] - tableauDeIntRandom[(i + 1) % tableauDeIntRandom.length]);
        }
        return resultat;
    };

    private int[] genereTableauDeIntRandom(int size) {
        int[] tableauDeIntRandom = new int[size];
        int positionPrécédante = 0;
        for (int i = 0; i < size; i++) {
            positionPrécédante += (int) (Math.random() * 50);
            tableauDeIntRandom[i] = positionPrécédante;
            // TODO: limite de 50
        }
        return tableauDeIntRandom;
    }

}