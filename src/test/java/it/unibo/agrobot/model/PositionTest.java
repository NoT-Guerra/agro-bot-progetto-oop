package it.unibo.agrobot.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verifica il corretto salvataggio e aggiornamento delle coordinate spaziali.
 */
class PositionTest {

    @Test
    void testPositionCreation() {
        Position pos = new Position(10.0, 20.0);
        assertEquals(10.0, pos.getX(), 0.001);
        assertEquals(20.0, pos.getY(), 0.001);
    }

    @Test
    void testPositionUpdate() {
        Position pos = new Position(0.0, 0.0);
        pos.setX(5.0);
        pos.setY(15.0);
        assertEquals(5.0, pos.getX(), 0.001);
        assertEquals(15.0, pos.getY(), 0.001);
    }
}
