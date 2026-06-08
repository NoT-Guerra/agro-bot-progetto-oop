package it.unibo.agrobot.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Verifica il comportamento della batteria.
 */
class BatteryTest {

    @Test
    void testBatteryCreation() {
        Battery battery = new Battery(100.0);
        assertEquals(100.0, battery.getLevel(), 0.001);
        System.out.println("testBatteryCreation: PASSATO");
    }

    @Test
    void testBatteryDecrease() {
        Battery battery = new Battery(100.0);
        
        //consumo normale
        battery.decrease(2.0);
        assertEquals(98.0, battery.getLevel(), 0.001);
        
        //Verifica del limite inferiore
        battery.decrease(100.0);
        assertEquals(0.0, battery.getLevel(), 0.001);
        
        System.out.println("testBatteryDecrease: PASSATO");
    }

    @Test
    void testBatteryRecharge() {
        Battery battery = new Battery(100.0);
        battery.decrease(50.0);
        battery.recharge();
        assertEquals(100.0, battery.getLevel(), 0.001);
        System.out.println("testBatteryRecharge: PASSATO");
    }

    @Test
    void testBatteryDead() {
        Battery battery = new Battery(100.0);
        assertFalse(battery.isDead());
        
        battery.decrease(100.0);
        assertTrue(battery.isDead());
        
        System.out.println("testBatteryDead: PASSATO");
    }
}
