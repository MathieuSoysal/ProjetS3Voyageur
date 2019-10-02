package projetS3Voyageur;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.lang.reflect.Array;
import java.rmi.ServerError;
import java.util.ArrayDeque;
import java.util.ArrayList;

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
    public void ajouterTrajet1(){
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
        assertTrue(t.isEmpty());
    }

    @Test
    public void ajouterTrajet2(){
        Graphe g = new Graphe(100, 4);
        Sommet s1 = g.getOneSommet(0);
        Sommet s2 = g.getOneSommet(1);
        Sommet s3 = g.getOneSommet(2);
        Sommet s4 = g.getOneSommet(3);

        t.ajouterSommet(s1);
        t.ajouterSommet(s2);

        Trajet tBis = new Trajet();
        tBis.ajouterSommet(s3);
        tBis.ajouterSommet(s4);

        t.ajouterTrajet(tBis);

        assertEquals(s1, t.getSommets().poll());
        assertEquals(s2, t.getSommets().poll());
        assertEquals(s3, t.getSommets().poll());
        assertEquals(s4, t.getSommets().poll());
        assertTrue(t.isEmpty());
    }

    @Test
    public void distance1(){
        Graphe g = new Graphe(10, 0);

        Sommet s1 = new Sommet(g);
        s1.setX(0);
        s1.setY(0);

        Sommet s2 = new Sommet(g);
        s2.setX(2);
        s2.setY(2);

        Trajet t = new Trajet();
        t.ajouterSommet(s1);
        t.ajouterSommet(s2);

        assertEquals(Math.sqrt(8), t.distance(), 0.0);
    }
}
