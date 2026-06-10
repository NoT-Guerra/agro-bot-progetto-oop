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

    @Test
    void testInventoryAddAndStack() {
        Inventory inv = new Inventory(3);

        //aggiungiamo 3 semi di pomodoro, devono finire nello stesso slot
        assertTrue(inv.addItem("Pomodoro", ItemType.SEED));
        assertTrue(inv.addItem("Pomodoro", ItemType.SEED));
        assertTrue(inv.addItem("Pomodoro", ItemType.SEED));
        assertEquals(3, inv.getItemCount("Pomodoro"));

        //controlliamo che siano tutti nello slot 0
        assertEquals(3, inv.getSlot(0).getQuantity());
        assertTrue(inv.getSlot(1).isEmpty());

        System.out.println("testInventoryAddAndStack: PASSATO");
    }

    @Test
    void testInventoryFull() {
        Inventory inv = new Inventory(3);

        //riempiamo i 3 slot con 3 oggetti diversi
        assertTrue(inv.addItem("Pomodoro", ItemType.SEED));
        assertTrue(inv.addItem("Grano", ItemType.CROP));
        assertTrue(inv.addItem("Carota", ItemType.SEED));

        //il quarto tipo di oggetto non ha slot liberi
        assertFalse(inv.addItem("Mais", ItemType.CROP));

        System.out.println("testInventoryFull: PASSATO");
    }

    @Test
    void testInventoryRemove() {
        Inventory inv = new Inventory(3);

        inv.addItem("Pomodoro", ItemType.CROP);
        inv.addItem("Pomodoro", ItemType.CROP);

        assertTrue(inv.removeItem("Pomodoro"));
        assertEquals(1, inv.getItemCount("Pomodoro"));

        //rimuoviamo qualcosa che non esiste
        assertFalse(inv.removeItem("Grano"));

        System.out.println("testInventoryRemove: PASSATO");
    }

    @Test
    void testInventoryAddSlot() {
        Inventory inv = new Inventory(3);

        //riempiamo tutti gli slot
        inv.addItem("Pomodoro", ItemType.SEED);
        inv.addItem("Grano", ItemType.CROP);
        inv.addItem("Carota", ItemType.SEED);

        //non ce spazio
        assertFalse(inv.addItem("Mais", ItemType.CROP));

        //compriamo un nuovo slot al mercato
        inv.addSlot();
        assertEquals(4, inv.getSlotCount());

        //ora c e spazio
        assertTrue(inv.addItem("Mais", ItemType.CROP));

        System.out.println("testInventoryAddSlot: PASSATO");
    }
}
