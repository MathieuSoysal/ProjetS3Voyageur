package projetS3Voyageur;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

import static org.junit.jupiter.api.Assertions.*;

class ParcoursTest {

    @Test
    void ajouterParcours1() {
        Ville v1 = new Ville();
        Ville v2 = new Ville();
        Ville v3 = new Ville();
        Ville v4 = new Ville();

        Parcours p1 = new Parcours();
        p1.ajouterVille(v1);
        p1.ajouterVille(v2);

        Parcours p2 = new Parcours();
        p2.ajouterVille(v3);
        p2.ajouterVille(v4);

        p1.ajouterParcours(p2);

        ArrayDeque<Ville> villes = new ArrayDeque<>();
        villes.offer(v1);
        villes.offer(v2);
        villes.offer(v3);
        villes.offer(v4);

        assertEquals(villes.toString(), p1.getVillesEmpruntees().toString());
    }

    @Test
    void ajouterParcours2() {
        Ville v1 = new Ville();
        Ville v2 = new Ville();

        Parcours p1 = new Parcours();
        p1.ajouterVille(v1);
        p1.ajouterVille(v2);

        Parcours p2 = new Parcours();

        p1.ajouterParcours(p2);

        ArrayDeque<Ville> villes = new ArrayDeque<>();
        villes.offer(v1);
        villes.offer(v2);

        assertEquals(villes.toString(), p1.getVillesEmpruntees().toString());
    }

    @Test
    void ajouterParcours3() {
        Ville v1 = new Ville();
        Ville v2 = new Ville();

        Parcours p1 = new Parcours();

        Parcours p2 = new Parcours();
        p2.ajouterVille(v1);
        p2.ajouterVille(v2);

        p1.ajouterParcours(p2);

        ArrayDeque<Ville> villes = new ArrayDeque<>();
        villes.offer(v1);
        villes.offer(v2);

        assertEquals(villes.toString(), p1.getVillesEmpruntees().toString());
    }

    @Test
    void ajouterParcours4() {

        Parcours p1 = new Parcours();

        Parcours p2 = new Parcours();

        p1.ajouterParcours(p2);

        ArrayDeque<Ville> villes = new ArrayDeque<>();

        assertEquals(villes.toString(), p1.getVillesEmpruntees().toString());
    }
}