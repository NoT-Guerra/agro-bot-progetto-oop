package it.unibo.agrobot.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verifica il comportamento di base del drone.
 */
class DroneTest {

    @Test
    void testInitialPosition() {
        Position initial = new Position(0.0, 0.0);
        Drone drone = new DroneImpl(initial);
        
        assertEquals(0.0, drone.getPosition().getX(), 0.001);
        assertEquals(0.0, drone.getPosition().getY(), 0.001);
    }

    @Test
    void testMovement() {
        Position initial = new Position(10.0, 10.0);
        Drone drone = new DroneImpl(initial);
        
        drone.move(5.0, -2.0);
        
        assertEquals(15.0, drone.getPosition().getX(), 0.001);
        assertEquals(8.0, drone.getPosition().getY(), 0.001);
    }
}
