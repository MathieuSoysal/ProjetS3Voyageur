package projetS3Voyageur;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ResolveurTest {

    Graphe g;
    Resolveur r;

    @Test
    public void testBruteForce1(){
        g = new Graphe(7, 6);

        g.getOneSommet(0).setCoords(2,2);
        g.getOneSommet(4).setCoords(2,3);
        g.getOneSommet(5).setCoords(2,4);
        g.getOneSommet(3).setCoords(2,5);
        g.getOneSommet(1).setCoords(2,6);
        g.getOneSommet(2).setCoords(2,7);

        r = new Resolveur(g, g.getOneSommet(0).getVille());

        Trajet t = r.bruteForce();

        assertEquals("[0, 4, 5, 3, 1, 2, 0]", t.toString());

    }

}
