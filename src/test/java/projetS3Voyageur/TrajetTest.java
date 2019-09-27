package projetS3Voyageur;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.rmi.ServerError;
import java.util.ArrayDeque;

import static org.junit.Assert.*;

public class TrajetTest {

    Trajet t;

    @Before
    public void setUp() throws Exception {
        t = new Trajet();
    }

    @Test
    public void ajouterSommet1() {
        Graphe g = new Graphe(100, 1);
        Sommet s = g.getOneSommet(0);
        t.ajouterSommet(s);
        assertEquals(t.getSommets().getFirst(), s);
    }

    @Test
    public void ajouterSommet2() {
        Graphe g = new Graphe(100, 1);
        Sommet s = g.getOneSommet(0);
        t.ajouterSommet(s);
        t.ajouterSommet(s);
        assertEquals(s, t.getSommets().poll());
        assertEquals(s, t.getSommets().poll());
    }

    @Test
    public void ajouterTrajet(){
        Graphe g = new Graphe(100, 3);
        Sommet s1 = g.getOneSommet(0);
        Sommet s2 = g.getOneSommet(1);
        Sommet s3 = g.getOneSommet(2);

        t.ajouterSommet(s1);

        Trajet tBis = new Trajet();
        tBis.ajouterSommet(s2);
        tBis.ajouterSommet(s3);

        t.ajouterTrajet(tBis);

        assertEquals(s1, t.getSommets().poll());
        assertEquals(s2, t.getSommets().poll());
        assertEquals(s3, t.getSommets().poll());
    }
}
