package projetS3Voyageur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class AppTest {

    @Test
    public void test_if_main_method_was_implemented() {
        assertDoesNotThrow(() -> App.main(new String[1]));
    }

    @Test
    public void test_nbVille_inferieur0() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Villes(-1));
    }

}