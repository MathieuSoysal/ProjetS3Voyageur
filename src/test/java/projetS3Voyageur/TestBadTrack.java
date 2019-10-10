package projetS3Voyageur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

import projetS3Voyageur.CompositionPays.Position;
import projetS3Voyageur.ModesDeRecherches.BadTrack;
import projetS3Voyageur.ModesDeRecherches.BrutForce;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class TestBadTrack {
    private ModeRecherche brutForceMax = new BadTrack();

    // #region Ajout test Comparaison BrutForce

    // #region Test avec set ville de départ

    @Test
    public void test_comparaisont_10villes_avecBrutForce_ville_depart_3() {
        for (int i = 0; i < 10; i++) {
            Pays pays = new Pays(10);
            int villeDepart = (int) (Math.random() * 9);

            ModeRecherche brutForce = new BrutForce();

            brutForceMax.recherche(pays, villeDepart);
            brutForce.recherche(pays, villeDepart);

            assertEquals(brutForce.getParcour().getDistance(), brutForceMax.getParcour().getDistance());
        }
    }

    @Test
    public void test_comparaisont_9villes_avecBrutForce_villeDepart2() {
        for (int i = 0; i < 15; i++) {
            Pays pays = new Pays(9);
            int villeDepart = (int) (Math.random() * 8);
            ModeRecherche brutForce = new BrutForce();

            brutForceMax.recherche(pays, villeDepart);
            brutForce.recherche(pays, villeDepart);

            assertEquals(brutForce.getParcour().getDistance(), brutForceMax.getParcour().getDistance());
        }
    }

    // #endregion Test avec set ville de départ

    @Test
    public void test_comparaisont_10villes_avecBrutForce() {
        for (int i = 0; i < 10; i++) {
            Pays pays = new Pays(10);

            ModeRecherche brutForce = new BrutForce();

            brutForceMax.recherche(pays, 0);
            brutForce.recherche(pays, 0);

            assertEquals(brutForce.getParcour().getDistance(), brutForceMax.getParcour().getDistance());
        }
    }

    @Test
    public void test_comparaisont_9villes_avecBrutForce() {
        for (int i = 0; i < 15; i++) {
            Pays pays = new Pays(9);

            ModeRecherche brutForce = new BrutForce();

            brutForceMax.recherche(pays, 0);
            brutForce.recherche(pays, 0);

            assertEquals(brutForce.getParcour().getDistance(), brutForceMax.getParcour().getDistance());
        }
    }

    @Test
    public void test_comparaisont_8villes_avecBrutForce() {
        for (int i = 0; i < 50; i++) {
            Pays pays = new Pays(8);

            ModeRecherche brutForce = new BrutForce();

            brutForceMax.recherche(pays, 0);
            brutForce.recherche(pays, 0);

            assertEquals(brutForce.getParcour().getDistance(), brutForceMax.getParcour().getDistance());
        }
    }

    // #endregion Ajout test COmparaison BrutForce

    @Test
    public void test_si_les_villes_ont_des_positions_random() {

        brutForceMax.recherche(new Pays(6), 0);

        String parcours1 = brutForceMax.getParcour().getVillesEmprunté();

        brutForceMax.recherche(new Pays(6), 0);

        assertNotEquals(parcours1, brutForceMax.getParcour().getVillesEmprunté());

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

        brutForceMax.recherche(pays, 0);

        assertEquals("0->1->2->3->0", brutForceMax.getParcour().getVillesEmprunté());

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

        brutForceMax.recherche(pays, 0);

        assertEquals("0->1->2->3->4->0", brutForceMax.getParcour().getVillesEmprunté());

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

        brutForceMax.recherche(pays, 0);

        assertEquals("0->1->2->3->4->5->0", brutForceMax.getParcour().getVillesEmprunté());

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

        brutForceMax.recherche(pays, 0);

        assertEquals("0->1->2->3->4->5->0", brutForceMax.getParcour().getVillesEmprunté());

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

        brutForceMax.recherche(pays, 0);

        double distanceMinimum = brutForceMax.getParcour().getDistance();

        pays.setPositionVille(0, new Position(positionX, 2));
        pays.setPositionVille(4, new Position(positionX, 3));
        pays.setPositionVille(5, new Position(positionX, 4));
        pays.setPositionVille(3, new Position(positionX, 5));
        pays.setPositionVille(1, new Position(positionX, 6));
        pays.setPositionVille(2, new Position(positionX, 7));

        brutForceMax.recherche(pays, 0);

        assertEquals(distanceMinimum, brutForceMax.getParcour().getDistance());

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

            brutForceMax.recherche(pays, 0);

            assertEquals(calculeDistanceLinaire(t), brutForceMax.getParcour().getDistance());
        }

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

        brutForceMax.recherche(pays, 0);

        assertEquals("0->1->2->3->0", brutForceMax.getParcour().getVillesEmprunté());

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

        brutForceMax.recherche(pays, 0);

        assertEquals("0->1->2->3->4->0", brutForceMax.getParcour().getVillesEmprunté());

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

        brutForceMax.recherche(pays, 0);

        assertEquals("0->1->2->3->4->5->0", brutForceMax.getParcour().getVillesEmprunté());

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

        brutForceMax.recherche(pays, 0);

        double distanceMinimum = brutForceMax.getParcour().getDistance();

        pays.setPositionVille(0, new Position(2, positionY));
        pays.setPositionVille(4, new Position(3, positionY));
        pays.setPositionVille(5, new Position(4, positionY));
        pays.setPositionVille(3, new Position(5, positionY));
        pays.setPositionVille(1, new Position(6, positionY));
        pays.setPositionVille(2, new Position(7, positionY));

        brutForceMax.recherche(pays, 0);

        assertEquals(distanceMinimum, brutForceMax.getParcour().getDistance());

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

            brutForceMax.recherche(pays, 0);

            assertEquals(calculeDistanceLinaire(t), brutForceMax.getParcour().getDistance());
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