package sk.uniza.fri.item;

import java.util.HashMap;
import java.util.Iterator;

public class Inventory implements Iterable<Item> {
    private final HashMap<String, Item> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public void addItem(Item item) {
        this.items.put(item.getName(), item);
    }

    public Item getItem(String name) {
        return this.items.get(name);
    }

    public void removeItem(String name) {
        this.items.remove(name);
    }

    public int getSize() {
        return this.items.size();
    }

    @Override
    public Iterator<Item> iterator() {
        return this.items.values().iterator();
    }
}