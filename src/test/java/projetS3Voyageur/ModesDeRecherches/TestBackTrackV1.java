package projetS3Voyageur.ModesDeRecherches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

import projetS3Voyageur.CompositionPays.Pays;
import projetS3Voyageur.ModesDeRecherches.BackTrackV1;
import projetS3Voyageur.ModesDeRecherches.BrutForceV2;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

import java.awt.Point;

public class TestBackTrackV1 {
    private final ModeRecherche backTrackV1 = new BackTrackV1();

    // #region Ajout test Comparaison BrutForceV2

    
    // #region test avec les résultat issue de BrutForce v2
    @Test
    public void test_4villes_comparaisons_brutForceV2() {
        Pays pays = new Pays(4);
        pays.setPositionVille(0, new Point(867, 923));
        pays.setPositionVille(1, new Point(384, 183));
        pays.setPositionVille(2, new Point(193, 957));
        pays.setPositionVille(3, new Point(582, 183));

        backTrackV1.recherche(pays, 0);

        assertEquals(2463, (int) (backTrackV1.getParcours().getDistance()));
    }

    @Test
    public void test_5villes_comparaisons_brutForceV2() {
        Pays pays = new Pays(5);
        pays.setPositionVille(0, new Point(58, 264));
        pays.setPositionVille(1, new Point(39, 754));
        pays.setPositionVille(2, new Point(36, 124));
        pays.setPositionVille(3, new Point(54, 754));
        pays.setPositionVille(4, new Point(29, 745));

        backTrackV1.recherche(pays, 0);

        assertEquals(1281, (int) (backTrackV1.getParcours().getDistance()));
    }

    @Test
    public void test_6villes_comparaisons_brutForceV2() {
        Pays pays = new Pays(6);
        pays.setPositionVille(0, new Point(159, 536));
        pays.setPositionVille(1, new Point(433, 559));
        pays.setPositionVille(2, new Point(129, 560));
        pays.setPositionVille(3, new Point(546, 151));
        pays.setPositionVille(4, new Point(345, 855));
        pays.setPositionVille(5, new Point(645, 452));

        backTrackV1.recherche(pays, 0);

        assertEquals(1813, (int) (backTrackV1.getParcours().getDistance()));
    }

    @Test
    public void test_7villes_comparaisons_brutForceV2() {
        Pays pays = new Pays(7);
        pays.setPositionVille(0, new Point(347, 297));
        pays.setPositionVille(1, new Point(109, 307));
        pays.setPositionVille(2, new Point(295, 498));
        pays.setPositionVille(3, new Point(354, 798));
        pays.setPositionVille(4, new Point(186, 298));
        pays.setPositionVille(5, new Point(508, 209));
        pays.setPositionVille(6, new Point(298, 408));

        backTrackV1.recherche(pays, 0);

        assertEquals(1640, (int) (backTrackV1.getParcours().getDistance()));
    }

    @Test
    public void test_8villes_comparaisons_brutForceV2() {
        Pays pays = new Pays(8);
        pays.setPositionVille(0, new Point(134, 309));
        pays.setPositionVille(1, new Point(5325, 2494));
        pays.setPositionVille(2, new Point(536, 2553));
        pays.setPositionVille(3, new Point(284, 1384));
        pays.setPositionVille(4, new Point(1094, 198));
        pays.setPositionVille(5, new Point(932, 352));
        pays.setPositionVille(6, new Point(728, 374));
        pays.setPositionVille(7, new Point(898, 192));

        backTrackV1.recherche(pays, 0);

        assertEquals(13046, (int) (backTrackV1.getParcours().getDistance()));
    }

    @Test
    public void test_9villes_comparaisons_brutForceV2() {
        Pays pays = new Pays(9);
        pays.setPositionVille(0, new Point(1354, 3009));
        pays.setPositionVille(1, new Point(525, 2424));
        pays.setPositionVille(2, new Point(5316, 2553));
        pays.setPositionVille(3, new Point(2814, 1384));
        pays.setPositionVille(4, new Point(1094, 1918));
        pays.setPositionVille(5, new Point(9362, 3562));
        pays.setPositionVille(6, new Point(7208, 3704));
        pays.setPositionVille(7, new Point(8918, 1922));
        pays.setPositionVille(8, new Point(8098, 1142));

        backTrackV1.recherche(pays, 0);

        assertEquals(20058, (int) (backTrackV1.getParcours().getDistance()));
    }

    @Test
    public void test_10villes_comparaisons_brutForceV2() {
        Pays pays = new Pays(10);
        pays.setPositionVille(0, new Point(225, 993));
        pays.setPositionVille(1, new Point(812, 685));
        pays.setPositionVille(2, new Point(36, 490));
        pays.setPositionVille(3, new Point(237, 590));
        pays.setPositionVille(4, new Point(440, 635));
        pays.setPositionVille(5, new Point(471, 779));
        pays.setPositionVille(6, new Point(879, 270));
        pays.setPositionVille(7, new Point(704, 52));
        pays.setPositionVille(8, new Point(530, 24));
        pays.setPositionVille(9, new Point(890, 152));

        backTrackV1.recherche(pays, 0);

        assertEquals(3081, (int) (backTrackV1.getParcours().getDistance()));
    }

    @Test
    public void test_11villes_comparaisons_brutForceV2() {
        Pays pays = new Pays(11);
        pays.setPositionVille(0, new Point(188, 458));
        pays.setPositionVille(1, new Point(141, 799));
        pays.setPositionVille(2, new Point(611, 326));
        pays.setPositionVille(3, new Point(681, 386));
        pays.setPositionVille(4, new Point(255, 790));
        pays.setPositionVille(5, new Point(291, 12));
        pays.setPositionVille(6, new Point(153, 113));
        pays.setPositionVille(7, new Point(133, 685));
        pays.setPositionVille(8, new Point(652, 707));
        pays.setPositionVille(9, new Point(518, 817));
        pays.setPositionVille(10, new Point(25, 104));

        backTrackV1.recherche(pays, 0);

        assertEquals(2451, (int) (backTrackV1.getParcours().getDistance()));
    }

    // #endregion test avec les résultats issue de BrutForceV2 v2

    // #region Test avec set ville de départ

    @Test
    public void test_comparaisont_10villes_avecBrutForceV2_ville_depart_3() {
        for (int i = 0; i < 10; i++) {
            Pays pays = new Pays(10);
            int villeDepart = (int) (Math.random() * 9);

            ModeRecherche brutForceV2 = new BrutForceV2();

            backTrackV1.recherche(pays, villeDepart);
            brutForceV2.recherche(pays, villeDepart);

            assertEquals(brutForceV2.getParcours().getDistance(), backTrackV1.getParcours().getDistance());
        }
    }

    @Test
    public void test_comparaisont_9villes_avecBrutForceV2_villeDepart2() {
        for (int i = 0; i < 15; i++) {
            Pays pays = new Pays(9);
            int villeDepart = (int) (Math.random() * 8);
            ModeRecherche brutForceV2 = new BrutForceV2();

            backTrackV1.recherche(pays, villeDepart);
            brutForceV2.recherche(pays, villeDepart);

            assertEquals(brutForceV2.getParcours().getDistance(), backTrackV1.getParcours().getDistance());
        }
    }

    // #endregion Test avec set ville de départ

    @Test
    public void test_comparaisont_10villes_avecBrutForceV2() {
        for (int i = 0; i < 10; i++) {
            Pays pays = new Pays(10);

            ModeRecherche brutForceV2 = new BrutForceV2();

            backTrackV1.recherche(pays, 0);
            brutForceV2.recherche(pays, 0);

            assertEquals(brutForceV2.getParcours().getDistance(), backTrackV1.getParcours().getDistance());
        }
    }

    @Test
    public void test_comparaisont_9villes_avecBrutForceV2() {
        for (int i = 0; i < 15; i++) {
            Pays pays = new Pays(9);

            ModeRecherche brutForceV2 = new BrutForceV2();

            backTrackV1.recherche(pays, 0);
            brutForceV2.recherche(pays, 0);

            assertEquals(brutForceV2.getParcours().getDistance(), backTrackV1.getParcours().getDistance());
        }
    }

    @Test
    public void test_comparaisont_8villes_avecBrutForceV2() {
        for (int i = 0; i < 50; i++) {
            Pays pays = new Pays(8);

            ModeRecherche brutForceV2 = new BrutForceV2();

            backTrackV1.recherche(pays, 0);
            brutForceV2.recherche(pays, 0);

            assertEquals(brutForceV2.getParcours().getDistance(), backTrackV1.getParcours().getDistance());
        }
    }

    // #endregion Ajout test Comparaison BrutForceV2

    @Test
    public void test_si_les_villes_ont_des_positions_random() {

        backTrackV1.recherche(new Pays(6), 0);

        String parcours1 = backTrackV1.getParcours().getVillesEmprunté();

        backTrackV1.recherche(new Pays(6), 0);

        assertNotEquals(parcours1, backTrackV1.getParcours().getVillesEmprunté());

    }

    // #region distance linaire sur Y

    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_4villes() {

        Pays pays = new Pays(4);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Point(positionX, 2));
        pays.setPositionVille(1, new Point(positionX, 3));
        pays.setPositionVille(2, new Point(positionX, 4));
        pays.setPositionVille(3, new Point(positionX, 5));

        backTrackV1.recherche(pays, 0);

        assertEquals("0->1->2->3->0", backTrackV1.getParcours().getVillesEmprunté());

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

        backTrackV1.recherche(pays, 0);

        assertEquals("0->1->2->3->4->0", backTrackV1.getParcours().getVillesEmprunté());

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

        backTrackV1.recherche(pays, 0);

        assertEquals("0->1->2->3->4->5->0", backTrackV1.getParcours().getVillesEmprunté());

    }

    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_6villes() {

        Pays pays = new Pays(6);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Point(positionX, 2));
        pays.setPositionVille(1, new Point(positionX, 3));
        pays.setPositionVille(2, new Point(positionX, 4));
        pays.setPositionVille(3, new Point(positionX, 5));
        pays.setPositionVille(4, new Point(positionX, 6));
        pays.setPositionVille(5, new Point(positionX, 7));

        backTrackV1.recherche(pays, 0);

        assertEquals("0->1->2->3->4->5->0", backTrackV1.getParcours().getVillesEmprunté());

    }

    @Test
    public void test_distanceLinaireSurY_ParcourOptimum_Pour_6villes_desorde() {

        Pays pays = new Pays(6);

        int positionX = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Point(positionX, 2));
        pays.setPositionVille(1, new Point(positionX, 3));
        pays.setPositionVille(2, new Point(positionX, 4));
        pays.setPositionVille(3, new Point(positionX, 5));
        pays.setPositionVille(4, new Point(positionX, 6));
        pays.setPositionVille(5, new Point(positionX, 7));

        backTrackV1.recherche(pays, 0);

        double distanceMinimum = backTrackV1.getParcours().getDistance();

        pays.setPositionVille(0, new Point(positionX, 2));
        pays.setPositionVille(4, new Point(positionX, 3));
        pays.setPositionVille(5, new Point(positionX, 4));
        pays.setPositionVille(3, new Point(positionX, 5));
        pays.setPositionVille(1, new Point(positionX, 6));
        pays.setPositionVille(2, new Point(positionX, 7));

        backTrackV1.recherche(pays, 0);

        assertEquals(distanceMinimum, backTrackV1.getParcours().getDistance());

    }

    @Test // TODO: Précision se test est possible de fair en sorte qu'il soit plus rapide
    public void test_EXTREME_distanceLinaireSurY_ParcourOptimum_Pour_6villes_on_connais_la_distance_en_avance() {

        Pays pays = new Pays(6);

        for (int i = 0; i < 30; i++) {

            int positionX = (int) (Math.random() * 50);

            int[] t = genereTableauDeIntRandom(6);

            pays.setPositionVille(1, new Point(positionX, t[1]));
            pays.setPositionVille(2, new Point(positionX, t[2]));
            pays.setPositionVille(3, new Point(positionX, t[3]));
            pays.setPositionVille(0, new Point(positionX, t[0]));
            pays.setPositionVille(4, new Point(positionX, t[4]));
            pays.setPositionVille(5, new Point(positionX, t[5]));

            backTrackV1.recherche(pays, 0);

            assertEquals(calculeDistanceLinaire(t), backTrackV1.getParcours().getDistance());
        }

    }

    // #endregion distance linaire sur Y

    // #region distance linaire sur X

    @Test
    public void test_distanceLinaireSurX_distanceLinaireSurX_ParcourOptimum_Pour_4villes() {

        Pays pays = new Pays(4);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Point(2, positionY));
        pays.setPositionVille(1, new Point(3, positionY));
        pays.setPositionVille(2, new Point(4, positionY));
        pays.setPositionVille(3, new Point(5, positionY));

        backTrackV1.recherche(pays, 0);

        assertEquals("0->1->2->3->0", backTrackV1.getParcours().getVillesEmprunté());

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

        backTrackV1.recherche(pays, 0);

        assertEquals("0->1->2->3->4->0", backTrackV1.getParcours().getVillesEmprunté());

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

        backTrackV1.recherche(pays, 0);

        assertEquals("0->1->2->3->4->5->0", backTrackV1.getParcours().getVillesEmprunté());

    }

    @Test
    public void test_distanceLinaireSurX_ParcourOptimum_Pour_6villes_desorde() {

        Pays pays = new Pays(6);

        int positionY = (int) (Math.random() * 50);

        pays.setPositionVille(0, new Point(2, positionY));
        pays.setPositionVille(1, new Point(3, positionY));
        pays.setPositionVille(2, new Point(4, positionY));
        pays.setPositionVille(3, new Point(5, positionY));
        pays.setPositionVille(4, new Point(6, positionY));
        pays.setPositionVille(5, new Point(7, positionY));

        backTrackV1.recherche(pays, 0);

        double distanceMinimum = backTrackV1.getParcours().getDistance();

        pays.setPositionVille(0, new Point(2, positionY));
        pays.setPositionVille(4, new Point(3, positionY));
        pays.setPositionVille(5, new Point(4, positionY));
        pays.setPositionVille(3, new Point(5, positionY));
        pays.setPositionVille(1, new Point(6, positionY));
        pays.setPositionVille(2, new Point(7, positionY));

        backTrackV1.recherche(pays, 0);

        assertEquals(distanceMinimum, backTrackV1.getParcours().getDistance());

    }

    @Test // TODO: Précision se test est possible de fair en sorte qu'il soit plus rapide
    public void test_EXTREME_distanceLinaireSurX_ParcourOptimum_Pour_6villes() {

        Pays pays = new Pays(6);

        for (int i = 0; i < 15; i++) {

            int positionY = (int) (Math.random() * 50);

            int[] t = genereTableauDeIntRandom(6);

            pays.setPositionVille(0, new Point(t[0], positionY));
            pays.setPositionVille(1, new Point(t[1], positionY));
            pays.setPositionVille(2, new Point(t[2], positionY));
            pays.setPositionVille(3, new Point(t[3], positionY));
            pays.setPositionVille(4, new Point(t[4], positionY));
            pays.setPositionVille(5, new Point(t[5], positionY));

            backTrackV1.recherche(pays, 0);

            assertEquals(calculeDistanceLinaire(t), backTrackV1.getParcours().getDistance());
        }

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