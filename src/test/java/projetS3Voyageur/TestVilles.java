package projetS3Voyageur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class TestVilles {

    // #region Test paramètre non valide

    @Test
    public void test_nbVille_inferieur0() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Villes(-1));
    }

    @Test
    public void test_nbVille_egale0() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Villes(0));
    }

    @Test
    public void test_nbVille_tropelevee() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Villes(16));
    }

    // #endregion

    Villes v = new Villes(3);

    @Test
    public void test_getNombredeVilles() {
        assertEquals(3, v.getNombreDeVilles());
    }

    @Test
    public void test_getPositionNumVille_indexOutOfBound(){
        assertThrows(IndexOutOfBoundsException.class, () -> v.getPositionVille(6));
    }

    @Test
    public void test_setPositionVille(){
        Position nouvellePos = new Position(2, 2);
        v.setPositionVille(0,nouvellePos);
        assertEquals(nouvellePos.toString(), v.getPositionVille(0).toString());
    }



}