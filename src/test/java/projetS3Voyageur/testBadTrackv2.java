package projetS3Voyageur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

import projetS3Voyageur.CompositionPays.Position;
import projetS3Voyageur.ModesDeRecherches.BadTrack;
import projetS3Voyageur.ModesDeRecherches.BadTrackV2;
import projetS3Voyageur.ModesDeRecherches.BrutForce;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class testBadTrackv2 {
    private ModeRecherche badTrackv2 = new BadTrackV2();

    // #region Ajout test Comparaison BrutForce

    
    // #region test avec les résultat issue de BrutForce v2
    @Test
    public void test_4villes_comparaisons_brutForce() {
        Pays pays = new Pays(4);
        pays.setPositionVille(0, new Position(867, 923));
        pays.setPositionVille(1, new Position(384, 183));
        pays.setPositionVille(2, new Position(193, 957));
        pays.setPositionVille(3, new Position(582, 183));

        badTrackv2.recherche(pays, 0);

        assertEquals(2463, (int) (badTrackv2.getParcour().getDistance()));
    }

    @Test
    public void test_5villes_comparaisons_brutForce() {
        Pays pays = new Pays(5);
        pays.setPositionVille(0, new Position(58, 264));
        pays.setPositionVille(1, new Position(39, 754));
        pays.setPositionVille(2, new Position(36, 124));
        pays.setPositionVille(3, new Position(54, 754));
        pays.setPositionVille(4, new Position(29, 745));

        badTrackv2.recherche(pays, 0);

        assertEquals(1281, (int) (badTrackv2.getParcour().getDistance()));
    }

    @Test
    public void test_6villes_comparaisons_brutForce() {
        Pays pays = new Pays(6);
        pays.setPositionVille(0, new Position(159, 536));
        pays.setPositionVille(1, new Position(433, 559));
        pays.setPositionVille(2, new Position(129, 560));
        pays.setPositionVille(3, new Position(546, 151));
        pays.setPositionVille(4, new Position(345, 855));
        pays.setPositionVille(5, new Position(645, 452));

        badTrackv2.recherche(pays, 0);

        assertEquals(1813, (int) (badTrackv2.getParcour().getDistance()));
    }

    @Test
    public void test_7villes_comparaisons_brutForce() {
        Pays pays = new Pays(7);
        pays.setPositionVille(0, new Position(347, 297));
        pays.setPositionVille(1, new Position(109, 307));
        pays.setPositionVille(2, new Position(295, 498));
        pays.setPositionVille(3, new Position(354, 798));
        pays.setPositionVille(4, new Position(186, 298));
        pays.setPositionVille(5, new Position(508, 209));
        pays.setPositionVille(6, new Position(298, 408));

        badTrackv2.recherche(pays, 0);

        assertEquals(1640, (int) (badTrackv2.getParcour().getDistance()));
    }

    @Test
    public void test_8villes_comparaisons_brutForce() {
        Pays pays = new Pays(8);
        pays.setPositionVille(0, new Position(134, 309));
        pays.setPositionVille(1, new Position(5325, 2494));
        pays.setPositionVille(2, new Position(536, 2553));
        pays.setPositionVille(3, new Position(284, 1384));
        pays.setPositionVille(4, new Position(1094, 198));
        pays.setPositionVille(5, new Position(932, 352));
        pays.setPositionVille(6, new Position(728, 374));
        pays.setPositionVille(7, new Position(898, 192));

        badTrackv2.recherche(pays, 0);

        assertEquals(13046, (int) (badTrackv2.getParcour().getDistance()));
    }

    @Test
    public void test_9villes_comparaisons_brutForce() {
        Pays pays = new Pays(9);
        pays.setPositionVille(0, new Position(1354, 3009));
        pays.setPositionVille(1, new Position(525, 2424));
        pays.setPositionVille(2, new Position(5316, 2553));
        pays.setPositionVille(3, new Position(2814, 1384));
        pays.setPositionVille(4, new Position(1094, 1918));
        pays.setPositionVille(5, new Position(9362, 3562));
        pays.setPositionVille(6, new Position(7208, 3704));
        pays.setPositionVille(7, new Position(8918, 1922));
        pays.setPositionVille(8, new Position(8098, 1142));

        badTrackv2.recherche(pays, 0);

        assertEquals(20058, (int) (badTrackv2.getParcour().getDistance()));
    }

    @Test
    public void test_10villes_comparaisons_brutForce() {
        Pays pays = new Pays(10);
        pays.setPositionVille(0, new Position(225, 993));
        pays.setPositionVille(1, new Position(812, 685));
        pays.setPositionVille(2, new Position(36, 490));
        pays.setPositionVille(3, new Position(237, 590));
        pays.setPositionVille(4, new Position(440, 635));
        pays.setPositionVille(5, new Position(471, 779));
        pays.setPositionVille(6, new Position(879, 270));
        pays.setPositionVille(7, new Position(704, 52));
        pays.setPositionVille(8, new Position(530, 24));
        pays.setPositionVille(9, new Position(890, 152));

        badTrackv2.recherche(pays, 0);

        assertEquals(3081, (int) (badTrackv2.getParcour().getDistance()));
    }

    @Test
    public void test_11villes_comparaisons_brutForce() {
        Pays pays = new Pays(11);
        pays.setPositionVille(0, new Position(188, 458));
        pays.setPositionVille(1, new Position(141, 799));
        pays.setPositionVille(2, new Position(611, 326));
        pays.setPositionVille(3, new Position(681, 386));
        pays.setPositionVille(4, new Position(255, 790));
        pays.setPositionVille(5, new Position(291, 12));
        pays.setPositionVille(6, new Position(153, 113));
        pays.setPositionVille(7, new Position(133, 685));
        pays.setPositionVille(8, new Position(652, 707));
        pays.setPositionVille(9, new Position(518, 817));
        pays.setPositionVille(10, new Position(25, 104));

        badTrackv2.recherche(pays, 0);

        assertEquals(2451, (int) (badTrackv2.getParcour().getDistance()));
    }

    // #endregion test avec les résultats issue de BrutForce v2

    // #region Test avec set ville de départ

    @Test
    public void test_comparaisont_10villes_avecBrutForce_ville_depart_3() {
        for (int i = 0; i < 10; i++) {
            Pays pays = new Pays(10);
            int villeDepart = (int) (Math.random() * 9);

            ModeRecherche brutForce = new BrutForce();

            badTrackv2.recherche(pays, villeDepart);
            brutForce.recherche(pays, villeDepart);

            assertEquals(brutForce.getParcour().getDistance(), badTrackv2.getParcour().getDistance());
        }
    }

    @Test
    public void test_comparaisont_9villes_avecBrutForce_villeDepart2() {
        for (int i = 0; i < 15; i++) {
            Pays pays = new Pays(9);
            int villeDepart = (int) (Math.random() * 8);
            ModeRecherche brutForce = new BrutForce();

            badTrackv2.recherche(pays, villeDepart);
            brutForce.recherche(pays, villeDepart);

            assertEquals(brutForce.getParcour().getDistance(), badTrackv2.getParcour().getDistance());
        }
    }

    // #endregion Test avec set ville de départ

    @Test
    public void test_comparaisont_10villes_avecBrutForce() {
        for (int i = 0; i < 10; i++) {
            Pays pays = new Pays(10);

            ModeRecherche brutForce = new BrutForce();

            badTrackv2.recherche(pays, 0);
            brutForce.recherche(pays, 0);

            assertEquals(brutForce.getParcour().getDistance(), badTrackv2.getParcour().getDistance());
        }
    }

    @Test
    public void test_comparaisont_9villes_avecBrutForce() {
        for (int i = 0; i < 15; i++) {
            Pays pays = new Pays(9);

            ModeRecherche brutForce = new BrutForce();

            badTrackv2.recherche(pays, 0);
            brutForce.recherche(pays, 0);

            assertEquals(brutForce.getParcour().getDistance(), badTrackv2.getParcour().getDistance());
        }
    }

    @Test
    public void test_comparaisont_8villes_avecBrutForce() {
        for (int i = 0; i < 50; i++) {
            Pays pays = new Pays(8);

            ModeRecherche brutForce = new BrutForce();

            badTrackv2.recherche(pays, 0);
            brutForce.recherche(pays, 0);

            assertEquals(brutForce.getParcour().getDistance(), badTrackv2.getParcour().getDistance());
        }
    }

    // #endregion Ajout test COmparaison BrutForce

    @Test
    public void test_si_les_villes_ont_des_positions_random() {

        badTrackv2.recherche(new Pays(6), 0);

        String parcours1 = badTrackv2.getParcour().getVillesEmprunté();

        badTrackv2.recherche(new Pays(6), 0);

        assertNotEquals(parcours1, badTrackv2.getParcour().getVillesEmprunté());

    }

    // #region distance linaire sur Y


    //TODO: Corrigé l'affichage

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

        badTrackv2.recherche(pays, 0);

        double distanceMinimum = badTrackv2.getParcour().getDistance();

        pays.setPositionVille(0, new Position(positionX, 2));
        pays.setPositionVille(4, new Position(positionX, 3));
        pays.setPositionVille(5, new Position(positionX, 4));
        pays.setPositionVille(3, new Position(positionX, 5));
        pays.setPositionVille(1, new Position(positionX, 6));
        pays.setPositionVille(2, new Position(positionX, 7));

        badTrackv2.recherche(pays, 0);

        assertEquals(distanceMinimum, badTrackv2.getParcour().getDistance());

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

            badTrackv2.recherche(pays, 0);

            assertEquals(calculeDistanceLinaire(t), badTrackv2.getParcour().getDistance());
        }

    }

    // #endregion distance linaire sur Y

    // #region distance linaire sur X

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

        badTrackv2.recherche(pays, 0);

        double distanceMinimum = badTrackv2.getParcour().getDistance();

        pays.setPositionVille(0, new Position(2, positionY));
        pays.setPositionVille(4, new Position(3, positionY));
        pays.setPositionVille(5, new Position(4, positionY));
        pays.setPositionVille(3, new Position(5, positionY));
        pays.setPositionVille(1, new Position(6, positionY));
        pays.setPositionVille(2, new Position(7, positionY));

        badTrackv2.recherche(pays, 0);

        assertEquals(distanceMinimum, badTrackv2.getParcour().getDistance());

    }

    @Test // TODO: Précision se test est possible de fair en sorte qu'il soit plus rapide
    public void test_EXTREME_distanceLinaireSurX_ParcourOptimum_Pour_6villes() {

        Pays pays = new Pays(6);

        for (int i = 0; i < 15; i++) {

            int positionY = (int) (Math.random() * 50);

            int[] t = genereTableauDeIntRandom(6);

            pays.setPositionVille(0, new Position(t[0], positionY));
            pays.setPositionVille(1, new Position(t[1], positionY));
            pays.setPositionVille(2, new Position(t[2], positionY));
            pays.setPositionVille(3, new Position(t[3], positionY));
            pays.setPositionVille(4, new Position(t[4], positionY));
            pays.setPositionVille(5, new Position(t[5], positionY));

            badTrackv2.recherche(pays, 0);

            assertEquals(calculeDistanceLinaire(t), badTrackv2.getParcour().getDistance());
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