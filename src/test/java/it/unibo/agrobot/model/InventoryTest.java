package it.unibo.agrobot.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class InventoryTest {

    @Test
    void testSlotBasicUsage() {
        InventorySlot slot = new InventorySlot();

        assertTrue(slot.isEmpty());
        assertFalse(slot.isFull());

        //aggiungiamo un seme
        assertTrue(slot.addItem("Pomodoro", ItemType.SEED));
        assertEquals(1, slot.getQuantity());
        assertEquals("Pomodoro", slot.getItemName());

        //non possiamo mettere un oggetto diverso nello stesso slot
        assertFalse(slot.addItem("Grano", ItemType.SEED));

        System.out.println("testSlotBasicUsage: PASSATO");
    }

    @Test
    void testSlotMaxCapacity() {
        InventorySlot slot = new InventorySlot();

        //i semi hanno max 5 per slot
        for (int i = 0; i < 5; i++) {
            assertTrue(slot.addItem("Pomodoro", ItemType.SEED));
        }
        assertTrue(slot.isFull());

        //il sesto seme non ci sta
        assertFalse(slot.addItem("Pomodoro", ItemType.SEED));
        assertEquals(5, slot.getQuantity());

        System.out.println("testSlotMaxCapacity: PASSATO");
    }

    @Test
    void testSlotCropCapacity() {
        InventorySlot slot = new InventorySlot();

        //le colture hanno max 10 per slot
        for (int i = 0; i < 10; i++) {
            assertTrue(slot.addItem("Pomodoro", ItemType.CROP));
        }
        assertTrue(slot.isFull());
        assertFalse(slot.addItem("Pomodoro", ItemType.CROP));
        assertEquals(10, slot.getQuantity());

        System.out.println("testSlotCropCapacity: PASSATO");
    }

    @Test
    void testSlotRemove() {
        InventorySlot slot = new InventorySlot();

        slot.addItem("Grano", ItemType.CROP);
        slot.addItem("Grano", ItemType.CROP);
        assertEquals(2, slot.getQuantity());

        assertTrue(slot.removeItem());
        assertEquals(1, slot.getQuantity());

        //rimuoviamo l ultimo, lo slot torna vuoto
        assertTrue(slot.removeItem());
        assertTrue(slot.isEmpty());
        assertEquals(0, slot.getQuantity());

        //ora possiamo metterci un oggetto completamente diverso
        assertTrue(slot.addItem("Carota", ItemType.SEED));
        assertEquals("Carota", slot.getItemName());

        System.out.println("testSlotRemove: PASSATO");
    }

    
}
