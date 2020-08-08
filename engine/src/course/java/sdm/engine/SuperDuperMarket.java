package course.java.sdm.engine;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SuperDuperMarket {

    private final String name;
    private final Set<Store> stores;
    private final Set<Item> items;
    private final Set<Order> orders;

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

    public int numberOfStoresSellingTheItem(Item item) {
       return ((int) stores.stream().filter(store -> store.isItemInTheStore(item)).count());
    }

    public float averageItemPrice(Item item) {
//        Stream stream = stores.stream().filter(store -> store.isItemInTheStore(item));
//        float x = stream.collect(Collectors.toList())
//        return ((float) .count());
        return 0;
    }
}
