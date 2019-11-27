package projetS3Voyageur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import projetS3Voyageur.CompositionPays.*;

public class AppTest {


    @Test
    public void test_nbVille_inferieur0() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Pays(-1));
    }

}