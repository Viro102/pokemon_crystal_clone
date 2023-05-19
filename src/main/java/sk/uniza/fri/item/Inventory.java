package sk.uniza.fri.item;

import java.util.HashMap;
import java.util.Iterator;

public class Inventory implements Iterable<Item> {
    private final HashMap<String, Item> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    @Override
    public Iterator<Item> iterator() {
        return this.items.values().iterator();
    }

    public void addItem(Item item) {
        this.items.put(item.getName(), item);
    }
}
