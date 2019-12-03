package projetS3Voyageur.ModesDeRecherches.acm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;

import org.junit.Test;

import projetS3Voyageur.CompositionPays.Pays;

public class TestKruskal {
    private Kruskal kruskal = new Kruskal();

    @Test
    public void test_genererArbre() {

        // Trouver via calcul sur papier
        final byte[][] arbre = { { 4, 5 }, { 0, 1 }, { 0, 2 }, { 2, 7 }, { 1, 5 }, { 3, 4 }, { 6, 7 } };
        Pays pays = new Pays(8);

        pays.setPositionVille(0, new Point(3, 8));
        pays.setPositionVille(1, new Point(3, 6));
        pays.setPositionVille(2, new Point(5, 8));
        pays.setPositionVille(3, new Point(8, 5));
        pays.setPositionVille(4, new Point(6, 4));
        pays.setPositionVille(5, new Point(5, 5));
        pays.setPositionVille(6, new Point(8, 10));
        pays.setPositionVille(7, new Point(7, 8));

        int i =0;
        for (Byte[] arete : kruskal.genereArbre(pays)) {
            assertEquals(arbre[i][0], arete[0]);
            assertEquals(arbre[i++][1], arete[1]);

        }
    }
}