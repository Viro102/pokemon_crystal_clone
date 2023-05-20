package sk.uniza.fri.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InventoryTest {
    @Test
    void testConstructor() {
        Inventory inventory = new Inventory();
        Assertions.assertEquals(0, inventory.getSize());
    }

    @Test
    void testAddItem() {
        Inventory inventory = new Inventory();
        Assertions.assertEquals(0, inventory.getSize());
        inventory.addItem(new Potion("Attack Potion", "Buffs attack", Effect.BUFF_ATTACK));
        Assertions.assertEquals(1, inventory.getSize());
    }

    @Test
    void testRemoveItem() {
        Inventory inventory = new Inventory();
        Assertions.assertEquals(0, inventory.getSize());
        inventory.addItem(new Potion("Attack Potion", "Buffs attack", Effect.BUFF_ATTACK));
        Assertions.assertEquals(1, inventory.getSize());
        inventory.removeItem("Attack Potion");
        Assertions.assertEquals(0, inventory.getSize());
    }

    @Test
    void testGetItem() {
        Inventory inventory = new Inventory();
        Assertions.assertEquals(0, inventory.getSize());
        inventory.addItem(new Potion("Attack Potion", "Buffs attack", Effect.BUFF_ATTACK));
        Assertions.assertEquals(1, inventory.getSize());
        Assertions.assertNotNull(inventory.getItem("Attack Potion"));
    }
}
