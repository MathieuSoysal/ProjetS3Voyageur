package projetS3Voyageur.ModesDeRecherches.acm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;

import org.junit.Test;

import projetS3Voyageur.CompositionPays.Pays;

public class TestKruskal {
    private Kruskal kruskal = new Kruskal();

    // #region Test genererArbre

    // #region Avec aucun sommet du graphe déjà visité

    @Test
    public void test_genererArbre_11noeuds() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { // liste d'adjacence des noeuds :
                { 9 }, // adjacences noeud num°1
                { 5, 6, 7 }, // adjacences noeud num°2
                { 4, 10, 11 }, // adjacences noeud num°3
                { 3 }, // adjacences noeud num°4
                { 2 }, // adjacences noeud num°5
                { 2, 8 }, // adjacences noeud num°6
                { 2 }, // adjacences noeud num°7
                { 6, 11 }, // adjacences noeud num°8
                { 1, 11 }, // adjacences noeud num°9
                { 3 }, // adjacences noeud num°10
                { 3, 8, 9 }, // adjacences noeud num°11
        };

        final String[] listeAdjacence = formateString(listeAdjacenceNum, 11);
        Pays pays = new Pays(11);

        pays.setPositionVille(0, new Point(6, 10));
        pays.setPositionVille(1, new Point(8, 3));
        pays.setPositionVille(2, new Point(2, 4));
        pays.setPositionVille(3, new Point(1, 7));
        pays.setPositionVille(4, new Point(9, 2));
        pays.setPositionVille(5, new Point(7, 1));
        pays.setPositionVille(6, new Point(8, 5));
        pays.setPositionVille(7, new Point(6, 2));
        pays.setPositionVille(8, new Point(5, 6));
        pays.setPositionVille(9, new Point(2, 1));
        pays.setPositionVille(10, new Point(4, 4));

        int i = 0;
        kruskal.genereArbre(pays);
        for (String adjacences : kruskal.toString().split("\n")) {

            assertEquals(listeAdjacence[i++], adjacences);
        }
    }

    @Test
    public void test_genererArbre_9noeuds() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { // liste d'adjacence des noeuds :
                { 3 }, // adjacences noeud num°1
                { 8 }, // adjacences noeud num°2
                { 1, 5, 6 }, // adjacences noeud num°3
                { 8, 9 }, // adjacences noeud num°4
                { 3 }, // adjacences noeud num°5
                { 3, 7, 9 }, // adjacences noeud num°6
                { 6 }, // adjacences noeud num°7
                { 2, 4 }, // adjacences noeud num°8
                { 4, 6 }, // adjacences noeud num°9
        };
        final String[] listeAdjacence = formateString(listeAdjacenceNum, 9);
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
        kruskal.genereArbre(pays);
        for (String adjacences : kruskal.toString().split("\n")) {
            assertEquals(listeAdjacence[i++], adjacences);
        }
    }

    @Test
    public void test_genererArbre_8noeuds() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { // liste d'adjacence des noeuds :
                { 2, 3 }, // adjacences noeud num°1
                { 1, 6 }, // adjacences noeud num°2
                { 1, 8 }, // adjacences noeud num°3
                { 5 }, // adjacences noeud num°4
                { 4, 6 }, // adjacences noeud num°5
                { 2, 5 }, // adjacences noeud num°6
                { 8 }, // adjacences noeud num°7
                { 3, 7 }, // adjacences noeud num°8
        };

        final String[] listeAdjacence = formateString(listeAdjacenceNum, 8);
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
        kruskal.genereArbre(pays);
        for (String adjacences : kruskal.toString().split("\n")) {
            assertEquals(listeAdjacence[i++], adjacences);
        }
    }

    @Test
    public void test_genererArbre_7noeuds_part1() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { // liste d'adjacence des noeuds :
                { 3 }, // adjacences noeud num°1
                { 7 }, // adjacences noeud num°2
                { 1, 5 }, // adjacences noeud num°3
                { 5, 7 }, // adjacences noeud num°4
                { 3, 4, 6 }, // adjacences noeud num°5
                { 5 }, // adjacences noeud num°6
                { 2, 4 } // adjacences noeud num°7
        };

        final String[] listeAdjacence = formateString(listeAdjacenceNum, 7);

        Pays pays = new Pays(7);

        pays.setPositionVille(0, new Point(4, 3));
        pays.setPositionVille(1, new Point(3, 5));
        pays.setPositionVille(2, new Point(3, 2));
        pays.setPositionVille(3, new Point(1, 3));
        pays.setPositionVille(4, new Point(2, 2));
        pays.setPositionVille(5, new Point(1, 1));
        pays.setPositionVille(6, new Point(2, 4));

        int i = 0;
        kruskal.genereArbre(pays);
        for (String adjacences : kruskal.toString().split("\n")) {
            assertEquals(listeAdjacence[i++], adjacences);
        }
    }

    @Test
    public void test_genererArbre_7noeuds_part2() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { // liste d'adjacence des noeuds :
                { 6 }, // adjacences noeud num°1
                { 4, 5 }, // adjacences noeud num°2
                { 5 }, // adjacences noeud num°3
                { 2, 7 }, // adjacences noeud num°4
                { 2, 3, 6 }, // adjacences noeud num°5
                { 1, 5 }, // adjacences noeud num°6
                { 4 } // adjacences noeud num°7
        };
        final String[] listeAdjacence = formateString(listeAdjacenceNum, 7);

        Pays pays = new Pays(7);

        pays.setPositionVille(0, new Point(4, 3));
        pays.setPositionVille(1, new Point(2, 4));
        pays.setPositionVille(2, new Point(2, 2));
        pays.setPositionVille(3, new Point(3, 5));
        pays.setPositionVille(4, new Point(2, 3));
        pays.setPositionVille(5, new Point(3, 3));
        pays.setPositionVille(6, new Point(4, 5));

        int i = 0;
        kruskal.genereArbre(pays);
        for (String adjacences : kruskal.toString().split("\n")) {
            assertEquals(listeAdjacence[i++], adjacences);
        }
    }

    @Test
    public void test_genererArbre_6noeuds_part1() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { // liste d'adjacence des noeuds :
                { 5, 6 }, // adjacences noeud num°1
                { 5 }, // adjacences noeud num°2
                { 4, 6 }, // adjacences noeud num°3
                { 3 }, // adjacences noeud num°4
                { 1, 2 }, // adjacences noeud num°5
                { 1, 3 } // adjacences noeud num°6
        };
        final String[] listeAdjacence = formateString(listeAdjacenceNum, 6);

        Pays pays = new Pays(6);

        pays.setPositionVille(0, new Point(1, 4));
        pays.setPositionVille(1, new Point(2, 5));
        pays.setPositionVille(2, new Point(3, 3));
        pays.setPositionVille(3, new Point(4, 2));
        pays.setPositionVille(4, new Point(1, 5));
        pays.setPositionVille(5, new Point(1, 3));

        int i = 0;
        kruskal.genereArbre(pays);
        for (String adjacences : kruskal.toString().split("\n")) {
            assertEquals(listeAdjacence[i++], adjacences);
        }
    }
    // #endregion Avec aucun sommet du graphe déjà visité

    // #region Avec des sommets déjà visités

    @Test
    public void test_genererArbre_11noeuds_Ignore_Noeud1_Noeud2_Noeud3_Noeud8_Noeud11() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { // liste d'adjacence des noeuds :
                {}, // adjacences noeud num°1 IGNORE
                {}, // adjacences noeud num°2 IGNORE
                {}, // adjacences noeud num°3 IGNORE
                { 9 }, // adjacences noeud num°4
                { 6, 7 }, // adjacences noeud num°5
                { 5, 10 }, // adjacences noeud num°6
                { 5, 9 }, // adjacences noeud num°7
                {}, // adjacences noeud num°8 IGNORE
                { 4, 7 }, // adjacences noeud num°9
                { 6 }, // adjacences noeud num°10
                {}, // adjacences noeud num°11 IGNORE
        };

        final byte[] listeNoirNoeud = { 1, 2, 3, 8, 11 };

        final String[] listeAdjacence = formateString(listeAdjacenceNum, 11);
        Pays pays = new Pays(11);

        pays.setPositionVille(0, new Point(6, 10));
        pays.setPositionVille(1, new Point(8, 3));
        pays.setPositionVille(2, new Point(2, 4));
        pays.setPositionVille(3, new Point(1, 7));
        pays.setPositionVille(4, new Point(9, 2));
        pays.setPositionVille(5, new Point(7, 1));
        pays.setPositionVille(6, new Point(8, 5));
        pays.setPositionVille(7, new Point(6, 2));
        pays.setPositionVille(8, new Point(5, 6));
        pays.setPositionVille(9, new Point(2, 1));
        pays.setPositionVille(10, new Point(4, 4));

        int i = 0;
        kruskal.genereArbre(pays, formatBinaire(listeNoirNoeud));
        for (String adjacences : kruskal.toString().split("\n")) {

            assertEquals(listeAdjacence[i++], adjacences);
        }
    }

    @Test
    public void test_genererArbre_9noeuds_Ignore_Noeud3_Noeud4_Noeud6() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { // liste d'adjacence des noeuds :
                { 9 }, // adjacences noeud num°1
                { 8 }, // adjacences noeud num°2
                {}, // adjacences noeud num°3 IGNORE
                {}, // adjacences noeud num°4 IGNORE
                { 7, 9 }, // adjacences noeud num°5
                {}, // adjacences noeud num°6 IGNORE
                { 5 }, // adjacences noeud num°7
                { 2, 9 }, // adjacences noeud num°8
                { 1, 5, 8 }, // adjacences noeud num°9
        };
        final String[] listeAdjacence = formateString(listeAdjacenceNum, 9);
        final byte[] listeNoirNoeud = { 3, 4, 6 };
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
        kruskal.genereArbre(pays, formatBinaire(listeNoirNoeud));
        for (String adjacences : kruskal.toString().split("\n")) {
            assertEquals(listeAdjacence[i++], adjacences);
        }
    }

    @Test
    public void test_genererArbre_8noeuds_Ignore_Noeud2_Noeud3_Noeud4_Noeud7() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { // liste d'adjacence des noeuds :
                { 6 }, // adjacences noeud num°1
                {}, // adjacences noeud num°2 IGNORE
                {}, // adjacences noeud num°3 IGNORE
                {}, // adjacences noeud num°4 IGNORE
                { 6 }, // adjacences noeud num°5
                { 1, 5, 8 }, // adjacences noeud num°6
                {}, // adjacences noeud num°7 IGNORE
                { 6 }, // adjacences noeud num°8
        };

        final String[] listeAdjacence = formateString(listeAdjacenceNum, 8);
        final byte[] listeNoirNoeud = { 2, 3, 4, 7 };
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
        kruskal.genereArbre(pays, formatBinaire(listeNoirNoeud));
        for (String adjacences : kruskal.toString().split("\n")) {
            assertEquals(listeAdjacence[i++], adjacences);
        }
    }

    @Test
    public void test_genererArbre_7noeuds_part1_Ignore_Noeud1_Noeud2_Noeud4_Noeud6() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { // liste d'adjacence des noeuds :
                {}, // adjacences noeud num°1 IGNORE
                {}, // adjacences noeud num°2 IGNORE
                { 5 }, // adjacences noeud num°3
                {}, // adjacences noeud num°4 IGNORE
                { 3, 7 }, // adjacences noeud num°5
                {}, // adjacences noeud num°6 IGNORE
                { 5 } // adjacences noeud num°7
        };

        final String[] listeAdjacence = formateString(listeAdjacenceNum, 7);
        final byte[] listeNoirNoeud = { 1, 2, 4, 6 };
        Pays pays = new Pays(7);

        pays.setPositionVille(0, new Point(4, 3));
        pays.setPositionVille(1, new Point(3, 5));
        pays.setPositionVille(2, new Point(3, 2));
        pays.setPositionVille(3, new Point(1, 3));
        pays.setPositionVille(4, new Point(2, 2));
        pays.setPositionVille(5, new Point(1, 1));
        pays.setPositionVille(6, new Point(2, 4));

        int i = 0;
        kruskal.genereArbre(pays, formatBinaire(listeNoirNoeud));
        for (String adjacences : kruskal.toString().split("\n")) {
            assertEquals(listeAdjacence[i++], adjacences);
        }
    }

    @Test
    public void test_genererArbre_7noeuds_part2_Ignore_Noeud5_Noeud7() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { // liste d'adjacence des noeuds :
                { 6 }, // adjacences noeud num°1
                { 4, 6 }, // adjacences noeud num°2
                { 6 }, // adjacences noeud num°3
                { 2 }, // adjacences noeud num°4
                {}, // adjacences noeud num°5 IGNORE
                { 1, 2, 3 }, // adjacences noeud num°6
                {} // adjacences noeud num°7 IGNORE
        };
        final String[] listeAdjacence = formateString(listeAdjacenceNum, 7);
        final byte[] listeNoirNoeud = { 5, 7 };
        Pays pays = new Pays(7);

        pays.setPositionVille(0, new Point(4, 3));
        pays.setPositionVille(1, new Point(2, 4));
        pays.setPositionVille(2, new Point(2, 2));
        pays.setPositionVille(3, new Point(3, 5));
        pays.setPositionVille(4, new Point(2, 3));
        pays.setPositionVille(5, new Point(3, 3));
        pays.setPositionVille(6, new Point(4, 5));

        int i = 0;
        kruskal.genereArbre(pays, formatBinaire(listeNoirNoeud));
        for (String adjacences : kruskal.toString().split("\n")) {
            assertEquals(listeAdjacence[i++], adjacences);
        }
    }

    @Test
    public void test_genererArbre_6noeuds_part1_IgnoreNoeud3() {

        // Trouver via calcul sur papier
        final byte[][] listeAdjacenceNum = { // liste d'adjacence des noeuds :
                { 5, 6 }, // adjacences noeud num°1
                { 5 }, // adjacences noeud num°2
                {}, // adjacences noeud num°3 IGNORE
                { 6 }, // adjacences noeud num°4
                { 1, 2 }, // adjacences noeud num°5
                { 1, 4 } // adjacences noeud num°6
        };

        final String[] listeAdjacence = formateString(listeAdjacenceNum, 6);
        final byte[] listeNoirNoeud = { 3 };
        Pays pays = new Pays(6);

        pays.setPositionVille(0, new Point(1, 4));
        pays.setPositionVille(1, new Point(2, 5));
        pays.setPositionVille(2, new Point(3, 3));
        pays.setPositionVille(3, new Point(4, 2));
        pays.setPositionVille(4, new Point(1, 5));
        pays.setPositionVille(5, new Point(1, 3));

        int i = 0;
        kruskal.genereArbre(pays, formatBinaire(listeNoirNoeud));
        for (String adjacences : kruskal.toString().split("\n")) {
            assertEquals(listeAdjacence[i++], adjacences);
        }
    }

    // #endregion Avec des sommets déjà visités

    // #endregion Test genererArbre

    // #region Outils

    /**
     * Condense la liste de {@code byte} en un vecteur de bit où chaque bit
     * représente un noeud.
     * 
     * @param listeNoirNoeud {@code byte[]} liste de numéros de noeud
     * 
     * @return {@code int} vecteur de bit où chaque bit représente un noeud.
     */
    private int formatBinaire(byte[] listeNoirNoeud) {
        int resultat = 0;

        for (byte numNoeud : listeNoirNoeud) {
            resultat |= 1 << (numNoeud - 1);
            // Moins 1 car le noeud commence par 1
        }

        return resultat;
    }

    /**
     * Récupère une liste d'adjacence de numéro de noeud afin de renvoyer un tableau
     * de String, (où chaque String représente l'adjacacence d'un noeud).
     * 
     * @param listeAdjacence {@code byte[][]} liste d'adjacence des numéros de noeud
     * @param taille         {@code int} la taille du tableau de int à retourner
     * @return {@code String[]} list d'adjacence des noeuds
     */
    private String[] formateString(byte[][] listeAdjacence, int taille) {
        String[] resultat = new String[taille];

        for (int i = 0; i < listeAdjacence.length; i++) {
            resultat[i] = String.format(" Noeud n°%s connectés : ", i + 1);
            for (byte noeud : listeAdjacence[i]) {
                resultat[i] += (noeud != 0) ? String.valueOf(noeud) + " " : "";
            }
        }

        return resultat;
    }
    // #endregion Outils

}
