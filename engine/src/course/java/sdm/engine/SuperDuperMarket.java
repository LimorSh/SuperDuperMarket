package course.java.sdm.engine;

import java.util.*;
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

    public Collection<Store> getStores() {
        return stores.values();
    }

    public Collection<Item> getItems() {
        return items.values();
    }

    public Collection<Order> getOrders() {
        return orders.values();
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

//    public int numberOfStoresSellingTheItem(Item item) {
//       return ((int) stores.values().stream().filter(store -> store.isItemInTheStore(item)).count());
//    }

    public int getNumberOfStoresSellingTheItem(int id) {
        return ((int) stores.values().stream().filter(store -> store.isItemInTheStore(items.get(id))).count());
    }

    public float getAverageItemPrice(int id) {
//        Stream<Store> s = stores.values().stream().filter(store -> store.isItemInTheStore(item));
//        s.forEach();

        float sum = 0f;
        Item item = items.get(id);
        for (Store store : stores.values()) {
            if (store.isItemInTheStore(item)) {
                sum += store.getItemPrice(item);
            }
        }
        return (sum / getNumberOfStoresSellingTheItem(id));
    }

//    public int totalNumberItemSold(Item item) {
//        int cnt = 0;
//        for (Order order : orders.values()) {
//            Store store = order.getStore();
//            if (store.isItemInTheStore(item)) {
//                cnt += store.getTotalNumberSold(item);
//            }
//        }
//        return cnt;
//    }

    public float getTotalAmountOfItemSells(int id) {
        float amount = 0f;
        Item item = items.get(id);

        for (Order order : orders.values()) {
            amount += order.getItems().get(item);
        }
        return amount;
    }
}
