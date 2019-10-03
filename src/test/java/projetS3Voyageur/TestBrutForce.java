package projetS3Voyageur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class TestBrutForce {
    private BrutForce brutForce;


    @Test
    public void test_ParcourOptimum_Pour_4villes() {

        Pays pays = new Pays(4);

        brutForce = new BrutForce(pays);

        pays.setPositionVille(0, new Position(2, 2));
        pays.setPositionVille(1, new Position(2, 3));
        pays.setPositionVille(2, new Position(2, 4));
        pays.setPositionVille(3, new Position(2, 5));

        brutForce.recherche();

        assertEquals("01230", brutForce.getParcour().getVillesEmprunté());

    }

    @Test
    public void test_ParcourOptimum_Pour_5villes() {

        Pays pays = new Pays(5);

        brutForce = new BrutForce(pays);

        pays.setPositionVille(0, new Position(2, 2));
        pays.setPositionVille(1, new Position(2, 3));
        pays.setPositionVille(2, new Position(2, 4));
        pays.setPositionVille(3, new Position(2, 5));
        pays.setPositionVille(4, new Position(2, 6));


        brutForce.recherche();

        assertEquals("012340", brutForce.getParcour().getVillesEmprunté());

    }

    @Test
    public void test_ParcourOptimum_Pour_6villes() {

        Pays pays = new Pays(6);

        brutForce = new BrutForce(pays);

        pays.setPositionVille(0, new Position(2, 2));
        pays.setPositionVille(1, new Position(2, 3));
        pays.setPositionVille(2, new Position(2, 4));
        pays.setPositionVille(3, new Position(2, 5));
        pays.setPositionVille(4, new Position(2, 6));
        pays.setPositionVille(5, new Position(2, 7));


        brutForce.recherche();

        assertEquals("0123450", brutForce.getParcour().getVillesEmprunté());

    }

    //TODO: 
    @Test
    public void test_ParcourOptimum_Pour_6villes_desorde() {

        Pays pays = new Pays(6);

        brutForce = new BrutForce(pays);

        pays.setPositionVille(0, new Position(2, 2));
        pays.setPositionVille(4, new Position(2, 3));
        pays.setPositionVille(5, new Position(2, 4));
        pays.setPositionVille(3, new Position(2, 5));
        pays.setPositionVille(1, new Position(2, 6));
        pays.setPositionVille(2, new Position(2, 7));


        brutForce.recherche();

        assertEquals("0453120", brutForce.getParcour().getVillesEmprunté());

    }

    @Test
    public void test_ParcourOptimum_Pour_6villes_random() {

        Pays pays = new Pays(6);

        brutForce = new BrutForce(pays);


        brutForce.recherche();

        assertNotEquals("012345", brutForce.getParcour().getVillesEmprunté());

    }
}