package it.unibo.agrobot.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StorageTest {

    @Test
    void testStorageCreation() {
        Storage storage = new Storage();

        //il deposito parte con 6 slot tutti vuoti
        assertEquals(6, storage.getSlotCount());
        for (int i = 0; i < 6; i++) {
            assertTrue(storage.getSlot(i).isEmpty());
        }
        System.out.println("testStorageCreation: PASSATO");
    }

    @Test
    void testStorageAddItems() {
        Storage storage = new Storage();

        //aggiungiamo oggetti come in un inventario normale
        assertTrue(storage.addItem("Pomodoro", ItemType.CROP));
        assertTrue(storage.addItem("Pomodoro", ItemType.CROP));
        assertEquals(2, storage.getItemCount("Pomodoro"));

        System.out.println("testStorageAddItems: PASSATO");
    }

    @Test
    void testTransferFromInventoryToStorage() {
        Storage storage = new Storage();
        Inventory inv = new Inventory(3);

        //mettiamo 3 pomodori nell inventario del drone
        inv.addItem("Pomodoro", ItemType.CROP);
        inv.addItem("Pomodoro", ItemType.CROP);
        inv.addItem("Pomodoro", ItemType.CROP);

        //trasferiamo un pomodoro dall inventario al deposito
        assertTrue(storage.transferFromInventory("Pomodoro", inv));

        assertEquals(2, inv.getItemCount("Pomodoro"));
        assertEquals(1, storage.getItemCount("Pomodoro"));

        System.out.println("testTransferFromInventoryToStorage: PASSATO");
    }

    @Test
    void testTransferFromStorageToInventory() {
        Storage storage = new Storage();
        Inventory inv = new Inventory(3);

        //mettiamo 3 pomodori nel deposito
        storage.addItem("Pomodoro", ItemType.CROP);
        storage.addItem("Pomodoro", ItemType.CROP);
        storage.addItem("Pomodoro", ItemType.CROP);

        //trasferiamo un pomodoro dal deposito all inventario
        assertTrue(storage.transferToInventory("Pomodoro", inv));

        assertEquals(2, storage.getItemCount("Pomodoro"));
        assertEquals(1, inv.getItemCount("Pomodoro"));

        System.out.println("testTransferFromStorageToInventory: PASSATO");
    }

    @Test
    void testTransferFailsWhenEmpty() {
        Storage storage = new Storage();
        Inventory inv = new Inventory(3);

        //il deposito e vuoto, il trasferimento deve fallire
        assertFalse(storage.transferToInventory("Pomodoro", inv));

        //l inventario e vuoto, il trasferimento deve fallire
        assertFalse(storage.transferFromInventory("Grano", inv));

        System.out.println("testTransferFailsWhenEmpty: PASSATO");
    }

    @Test
    void testTransferFailsWhenFull() {
        Storage storage = new Storage();
        Inventory inv = new Inventory(3);

        //riempiamo l inventario del drone con 3 tipi diversi
        inv.addItem("Pomodoro", ItemType.SEED);
        inv.addItem("Grano", ItemType.CROP);
        inv.addItem("Carota", ItemType.SEED);

        //mettiamo del mais nel deposito
        storage.addItem("Mais", ItemType.CROP);

        //l inventario non ha slot liberi per il mais, il trasferimento deve fallire
        assertFalse(storage.transferToInventory("Mais", inv));

        //il mais deve essere rimasto nel deposito
        assertEquals(1, storage.getItemCount("Mais"));

        System.out.println("testTransferFailsWhenFull: PASSATO");
    }
}
