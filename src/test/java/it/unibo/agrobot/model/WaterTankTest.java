package it.unibo.agrobot.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * verifica la logica del serbatoio dell'acqua
 */
class WaterTankTest {

    @Test
    void testTankCreation() {
        WaterTank tank = new WaterTank(50.0);
        // Di base il serbatoio nasce vuoto
        assertEquals(0.0, tank.getLevel(), 0.001);
        System.out.println("testTankCreation: PASSATO");
    }

    @Test
    void testAddAndRemoveWater() {
        WaterTank tank = new WaterTank(50.0);
        
        tank.add(30.0);
        assertEquals(30.0, tank.getLevel(), 0.001);
        
        tank.remove(10.0);
        assertEquals(20.0, tank.getLevel(), 0.001);
        
        // Verifica overflow (non può superare la capacità massima)
        tank.add(100.0);
        assertEquals(50.0, tank.getLevel(), 0.001);
        
        // Verifica underflow (non può scendere sotto zero)
        tank.remove(100.0);
        assertEquals(0.0, tank.getLevel(), 0.001);
        
        System.out.println("testAddAndRemoveWater: PASSATO");
    }
}
