package projetS3Voyageur.CompositionPays;


import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestPosition {
    private int y;
    private int x;
    private Position pos;

    @Before
    public void init() {
        x = (int) ((Math.random() - 0.5) * 10000);
        y = (int) ((Math.random() - 0.5) * 10000);

        pos = new Position(x, y);
    }

    // #region Test Position en X et Y

    @Test
    public void test_Position_X_et_Y() {

        assertEquals(x, pos.getX());
        assertEquals(y, pos.getY());

    }

    // #endregion Test Position en X et Y
    // #region Test position en X

    @Test
    public void test_PositionX() {

        assertEquals(x, pos.getX());

    }
    // #endregion Test Position en X
    // #region Test Position en Y

    @Test
    public void test_PositionY() {

        assertEquals(y, pos.getY());

    }

    // #endregion Test Postition en Y
    // #region Test toString

    @Test
    public void test_PositionToString() {

        Position pos2 = new Position(x, y);

        assertNotEquals(pos, pos2);
        assertEquals(pos.toString(), pos2.toString());
    }

    @Test
    public void test_PositionToString_nonEgual() {

        Position pos2 = new Position(y, x);

        assertNotEquals(pos.toString(), pos2.toString());
    }
    // #endregion Test ToString
}