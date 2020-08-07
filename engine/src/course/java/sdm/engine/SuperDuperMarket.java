package course.java.sdm.engine;

import java.util.HashSet;
import java.util.Set;

public class SuperDuperMarket {

    private String name;
    private Set<Store> stores;
    private Set<Item> items;
    private Set<Order> orders;

    public SuperDuperMarket(String name) {
        this.name = name;
        stores = new HashSet<>();
        items = new HashSet<>();
        orders = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void addStore(Store store) {
        stores.add(store);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
