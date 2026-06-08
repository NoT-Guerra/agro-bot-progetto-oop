package it.unibo.agrobot.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DroneTest {

    @Test
    void testInitialPosition() {
        Position initial = new Position(0.0, 0.0);
        Drone drone = new DroneImpl(initial);
        
        assertEquals(0.0, drone.getPosition().getX(), 0.001);
        assertEquals(0.0, drone.getPosition().getY(), 0.001);
        System.out.println("testInitialPosition: PASSATO");
    }

    @Test
    void testMovement() {
        Position initial = new Position(10.0, 10.0);
        Drone drone = new DroneImpl(initial);
        
        drone.move(5.0, -2.0);
        
        assertEquals(15.0, drone.getPosition().getX(), 0.001);
        assertEquals(8.0, drone.getPosition().getY(), 0.001);
        System.out.println("testMovement: PASSATO");
    }

    @Test
    void testDirectionalMovement() {
        Position initial = new Position(0.0, 0.0);
        Drone drone = new DroneImpl(initial);
        
        //muoviamo in alto di 5 posizioni
        drone.move(Direction.UP, 5.0);
        assertEquals(0.0, drone.getPosition().getX(), 0.001);
        assertEquals(5.0, drone.getPosition().getY(), 0.001); // assumendo Y cresca verso l'alto

        //muovi a destra di 10 pos 
        drone.move(Direction.RIGHT, 10.0);
        assertEquals(10.0, drone.getPosition().getX(), 0.001);
        assertEquals(5.0, drone.getPosition().getY(), 0.001);

        System.out.println("testDirectionalMovement: PASSATO");
    }

    @Test
    void testMovementBatteryConsumption() {
        Position initial = new Position(0.0, 0.0);
        Drone drone = new DroneImpl(initial);
        double initialBattery = drone.getBatteryLevel();
        
        drone.move(5.0, -2.0);
        
        // Verifichiamo che il livello della batteria sia sceso
        assertTrue(drone.getBatteryLevel() < initialBattery);
        System.out.println("testMovementBatteryConsumption: PASSATO");
    }

    @Test
    void testNoMovementWhenDead() {
        Position initial = new Position(0.0, 0.0);
        Drone drone = new DroneImpl(initial);
        
        // Simuliamo movimenti finché la batteria non si scarica (100.0 / 2.0 = 50 mosse)
        for (int i = 0; i < 60; i++) {
            drone.move(1.0, 1.0);
        }
        
        // Salviamo la posizione dopo che la batteria è esaurita
        Position posBeforeDeadMove = new Position(drone.getPosition().getX(), drone.getPosition().getY());
        
        // Proviamo a muoverci ancora, non dovrebbe funzionare
        drone.move(1.0, 1.0);
        
        assertEquals(posBeforeDeadMove.getX(), drone.getPosition().getX(), 0.001);
        assertEquals(posBeforeDeadMove.getY(), drone.getPosition().getY(), 0.001);
        System.out.println("testNoMovementWhenDead: PASSATO");
    }
}
