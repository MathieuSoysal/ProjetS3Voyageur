package projetS3Voyageur.bd;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestGestionBD {


    @Test
    public void test_Connexion() {

        assertEquals(InteractionBD.connexion(), true);
    }

    @Test
    public void test_requete() {

        InteractionBD.connexion();
        assertEquals(InteractionBD.setRequete("SELECT * FROM Carte "), true);
    }
    @Test
    public void test_recuperation_() {

        InteractionBD.connexion();
        assertEquals(InteractionBD.setRequete("SELECT nbVille FROM Carte WHERE idCarte = 1"), true);
    }

    @Test
    public void test_getNbVille_() {

        InteractionBD.connexion();
        assertEquals(GestionBD.getNbVille("1"), 3);
    }
/*58*/
   /* @Test
    public void test_getCarte_() {

        InteractionBD.connexion();
        GestionBD BD = new GestionBD();
       // assertEquals(BD.getCarte("1"), 3);// prb
    }*/

}
