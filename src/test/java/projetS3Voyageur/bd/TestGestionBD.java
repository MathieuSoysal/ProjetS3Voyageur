package projetS3Voyageur.bd;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestGestionBD {


    @Test
    public void test_Connexion() {

        assertTrue(InteractionBD.connexion());
    }

    @Test
    public void test_requete() {

        InteractionBD.connexion();
        assertTrue(InteractionBD.setRequete("SELECT * FROM Carte "));
    }
    @Test
    public void test_recuperation_() {

        InteractionBD.connexion();
        assertTrue(InteractionBD.setRequete("SELECT nbVille FROM Carte WHERE idCarte = 1"));
    }

    @Test
    public void test_getNbVille_() {

        InteractionBD.connexion();
        assertEquals(3, GestionBD.getNbVille("1"));
    }
    
   /* @Test
    public void test_getCarte_() {

        InteractionBD.connexion();
        GestionBD BD = new GestionBD();
       // assertEquals(BD.getCarte("1"), 3);// prb
    }*/

}
