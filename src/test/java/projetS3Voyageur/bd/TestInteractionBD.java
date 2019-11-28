package projetS3Voyageur.bd;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestInteractionBD {


    @BeforeAll
    public static void connexion() {
        InteractionBD.connexion();
        InteractionBD.setRequete("INSERT INTO Carte Set idCarte =99, nbVille ='2';");

    }



    @Test
    public void test_Connexion() {

        assertTrue(InteractionBD.connexion());
    }

    @Test
    public void test_requete() {

        assertTrue(InteractionBD.setRequete("SELECT * FROM Carte "));
    }

    @Test
    public void test_recuperation_() {

        assertEquals("2", InteractionBD.recuperationBD("SELECT nbVille FROM Carte WHERE idCarte =99").get(0)[0]);


    }

    @Test
    public void test_getNbVille_() {

        assertEquals(2, GestionBD.getNbVille("99"));
    }

    /*
     * @Test public void test_getCarte_() {
     * 
     * InteractionBD.connexion(); GestionBD BD = new GestionBD();
     * assertEquals(BD.getCarte("1"), 3);// prb }
     */

    @AfterAll
    public static void suppresion_donnee() {

        InteractionBD.setRequete("DELETE FROM Carte WHERE idCarte ='99';");
    }
}
