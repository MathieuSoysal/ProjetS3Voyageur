package projetS3Voyageur;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VilleTest {

    @Test
    void distance1_1() {
        Ville v1 = new Ville();
        v1.setCoords(0, 0);
        Ville v2 = new Ville();
        v2.setCoords(0, 2);

        assertEquals(2.0, v1.distance(v2));
    }

    @Test
    void distance1_2() {
        Ville v1 = new Ville();
        v1.setCoords(0, 0);
        Ville v2 = new Ville();
        v2.setCoords(0, 2);

        assertEquals(2.0, v2.distance(v1));
    }

    @Test
    void distance2_1() {
        Ville v1 = new Ville();
        v1.setCoords(0,0);
        Ville v2 = new Ville();
        v2.setCoords(3,0);

        assertEquals(3.0, v1.distance(v2));
    }

    @Test
    void distance2_2() {
        Ville v1 = new Ville();
        v1.setCoords(0,0);
        Ville v2 = new Ville();
        v2.setCoords(3,0);

        assertEquals(3.0, v2.distance(v1));
    }

    @Test
    void distance3_1(){
        Ville v1 = new Ville();
        v1.setCoords(2,2);
        Ville v2 = new Ville();
        v2.setCoords(3,4);

        assertEquals(Math.sqrt(1+4), v1.distance(v2));
    }

    @Test
    void distance3_2(){
        Ville v1 = new Ville();
        v1.setCoords(2,2);
        Ville v2 = new Ville();
        v2.setCoords(3,4);

        assertEquals(Math.sqrt(1+4), v2.distance(v1));
    }

}