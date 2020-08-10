package course.java.sdm.engine;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SuperDuperMarket {

    private final Map<Integer, Store> stores;
    private final Map<Integer, Item> items;
    private final Map<Integer, Order> orders;

    public SuperDuperMarket() {
        stores = new HashMap<>();
        items = new HashMap<>();
        orders = new HashMap<>();
    }

    public Map<Integer, Store> getStores() {
        return stores;
    }

    public Map<Integer, Item> getItems() {
        return items;
    }

    public Map<Integer, Order> getOrders() {
        return orders;
    }

    public void addStore(Store store) {
        int id = store.getId();
        if (!stores.containsKey(id)) {
            stores.put(id, store);
        }
    }

    public void addItem(Item item) {
        int id = item.getId();
        if (!items.containsKey(id)) {
            items.put(id, item);
        }
    }

    public void addOrder(Order order) {
        int id = order.getId();
        if (!orders.containsKey(id)) {
            orders.put(id, order);
        }
    }

    public Item getItem(int id) {
        return items.get(id);
    }

    public Store getStore(int id) {
        return stores.get(id);
    }

    public Order getOrder(int id) {
        return orders.get(id);
    }

    public int numberOfStoresSellingTheItem(Item item) {
       return ((int) stores.values().stream().filter(store -> store.isItemInTheStore(item)).count());
    }

    public float averageItemPrice(Item item) {
//        Stream stream = stores.stream().filter(store -> store.isItemInTheStore(item));

        float sum = 0f;
        for (Store store : stores.values()) {
            if (store.isItemInTheStore(item)) {
                sum += store.getItemPrice(item);
            }
        }
        return (sum / numberOfStoresSellingTheItem(item));
    }

    public int totalNumberItemSold(Item item) {
        int cnt = 0;
        for (Order order : orders.values()) {
            Store store = order.getStore();
            if (store.isItemInTheStore(item)) {
                cnt += store.getTotalNumberSold(item);
            }
        }
        return cnt;
    }
}
