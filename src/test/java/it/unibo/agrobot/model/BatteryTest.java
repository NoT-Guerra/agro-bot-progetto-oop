package it.unibo.agrobot.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
