package projetS3Voyageur.ModesDeRecherches.acm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;

import org.junit.Test;

import projetS3Voyageur.CompositionPays.Pays;

public class TestKruskal {
    private Kruskal kruskal = new Kruskal();

    @Test
    public void test_genererArbre_9noeuds() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { { 3 }, { 8 }, {1, 5, 6 }, { 8, 9 }, { 3 }, { 3, 9, 7 }, { 6 }, { 2, 4 },
                { 4, 6 } };
        // TODO: réglé le problème avec le graphe planaire
        final int[] listeAdjacence = valeurBinaire(listeAdjacenceNum, 9);
        Pays pays = new Pays(9);

        pays.setPositionVille(0, new Point(1, 1));
        pays.setPositionVille(1, new Point(6, 4));
        pays.setPositionVille(2, new Point(1, 3));
        pays.setPositionVille(3, new Point(4, 2));
        pays.setPositionVille(4, new Point(1, 4));
        pays.setPositionVille(5, new Point(3, 3));
        pays.setPositionVille(6, new Point(3, 5));
        pays.setPositionVille(7, new Point(5, 3));
        pays.setPositionVille(8, new Point(3, 2));

        int i = 0;
        for (int arete : kruskal.genereArbre(pays)) {
            assertEquals(listeAdjacence[i++], arete);
        }
    }

    @Test
    public void test_genererArbre_8noeuds() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { { 2, 3 }, { 1, 6 }, { 1, 8 }, { 5 }, { 6, 4 }, { 5, 2 }, { 8 }, { 3, 7 } };
        final int[] listeAdjacence = valeurBinaire(listeAdjacenceNum, 8);
        Pays pays = new Pays(8);

        pays.setPositionVille(0, new Point(3, 8));
        pays.setPositionVille(1, new Point(3, 6));
        pays.setPositionVille(2, new Point(5, 8));
        pays.setPositionVille(3, new Point(8, 5));
        pays.setPositionVille(4, new Point(6, 4));
        pays.setPositionVille(5, new Point(5, 5));
        pays.setPositionVille(6, new Point(8, 10));
        pays.setPositionVille(7, new Point(7, 8));

        int i = 0;
        for (int arete : kruskal.genereArbre(pays)) {
            assertEquals(listeAdjacence[i++], arete);
        }
    }

    @Test
    public void test_genererArbre_7noeuds_part1() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { { 3 }, { 7 }, { 1, 5 }, { 5, 7 }, { 3, 4, 6 }, { 5 }, { 2, 4 } };
        final int[] listeAdjacence = valeurBinaire(listeAdjacenceNum, 7);

        Pays pays = new Pays(7);

        pays.setPositionVille(0, new Point(4, 3));
        pays.setPositionVille(1, new Point(3, 5));
        pays.setPositionVille(2, new Point(3, 2));
        pays.setPositionVille(3, new Point(1, 3));
        pays.setPositionVille(4, new Point(2, 2));
        pays.setPositionVille(5, new Point(1, 1));
        pays.setPositionVille(6, new Point(2, 4));

        int i = 0;
        for (int arete : kruskal.genereArbre(pays)) {
            assertEquals(listeAdjacence[i++], arete);
        }
    }

    @Test
    public void test_genererArbre_7noeuds_part2() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { { 6 }, { 5, 4 }, { 5 }, { 7, 2 }, { 2, 3, 6 }, { 5, 1 }, { 4 } };
        final int[] listeAdjacence = valeurBinaire(listeAdjacenceNum, 7);

        Pays pays = new Pays(7);

        pays.setPositionVille(0, new Point(4, 3));
        pays.setPositionVille(1, new Point(2, 4));
        pays.setPositionVille(2, new Point(2, 2));
        pays.setPositionVille(3, new Point(3, 5));
        pays.setPositionVille(4, new Point(2, 3));
        pays.setPositionVille(5, new Point(3, 3));
        pays.setPositionVille(6, new Point(4, 5));

        int i = 0;
        for (int arete : kruskal.genereArbre(pays)) {
            assertEquals(listeAdjacence[i++], arete);
        }
    }

    @Test
    public void test_genererArbre_6noeuds_part1() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { { 5, 6 }, { 5 }, { 4, 6 }, { 3 }, { 2, 1 }, { 1, 3 } };
        final int[] listeAdjacence = valeurBinaire(listeAdjacenceNum, 6);

        Pays pays = new Pays(6);

        pays.setPositionVille(0, new Point(1, 4));
        pays.setPositionVille(1, new Point(2, 5));
        pays.setPositionVille(2, new Point(3, 3));
        pays.setPositionVille(3, new Point(4, 2));
        pays.setPositionVille(4, new Point(1, 5));
        pays.setPositionVille(5, new Point(1, 3));

        int i = 0;
        for (int arete : kruskal.genereArbre(pays)) {
            assertEquals(listeAdjacence[i++], arete);
        }
    }

    /**
     * Récupère une liste d'adjacence de numéro de noeud afin de renvoyer un tableau
     * de int, (où chaque bite du int représente un noeud).
     * 
     * @param listeAdjacence {@code byte[][]} liste d'adjacence des numéros de noeud
     * @param taille         {@code int} la taille du tableau de int à retourner
     * @return {@code int[]} list d'adjacence sous forme binaire
     */
    private int[] valeurBinaire(byte[][] listeAdjacence, int taille) {
        int[] resultat = new int[taille];

        int i = 0;

        for (byte[] noeudAdjacents : listeAdjacence) {
            for (byte noeud : noeudAdjacents) {
                resultat[i] |= (noeud != 0) ? (1 << (noeud - 1)) : 0;
            }
            i++;
        }
        return resultat;
    }
}
