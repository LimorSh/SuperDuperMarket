package course.java.sdm.engine;

import course.java.sdm.engine.exceptions.LocationAlreadyExistsException;

import java.util.*;
import java.util.stream.Collectors;

public class SuperDuperMarket {

    private final Map<Integer, Store> stores;
    private final Map<Integer, Item> items;
    private final Map<Integer, Order> orders;
    private Set<Item> itemsSold;
    private boolean[][] storesLocations;

    public SuperDuperMarket() {
        stores = new HashMap<>();
        items = new HashMap<>();
        orders = new HashMap<>();
        itemsSold = new HashSet<>();
        initializeStoresLocations();
    }

    private void initializeStoresLocations() {
        int maxLocation = Location.getMaxLocationValue();
        storesLocations = new boolean[maxLocation][maxLocation];
        for (int x = 0; x < maxLocation; x++) {
            for (int y = 0; y < maxLocation; y++) {
                storesLocations[x][y] = false;
            }
        }
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

    public boolean isItemInTheStore(int storeId, int itemId) {
        Store store = stores.get(storeId);
        return store.isItemInTheStore(itemId);
    }

    public float getItemPriceInStore(int storeId, int itemId) {
        Store store = stores.get(storeId);
        return store.getItemPrice(itemId);
    }

    public String getItemName(int itemId) {
        return items.get(itemId).getName();
    }

    public String getItemPurchaseCategory(int itemId) {
        return items.get(itemId).getPurchaseCategory().getPurchaseCategoryStr();
    }

    public void addItemIdToItemsSoldIds(Item item) {
        itemsSold.add(item);
    }

    public void addStore(Store store) {
        int id = store.getId();
        if (!isStoreExists(id)) {
            int x = store.getLocation().getCoordinate().x;
            int y = store.getLocation().getCoordinate().y;
            if (storesLocations[x - 1][y - 1])
                throw new LocationAlreadyExistsException(x, y);
            storesLocations[x - 1][y - 1] = true;
            stores.put(id, store);
        }
        else {
            throw new IllegalArgumentException("The store id " + id + " is already exists.");
        }
    }

    public void addItem(Item item) {
        int id = item.getId();
        if (!isItemExists(id)) {
            items.put(id, item);
        }
        else {
            throw new IllegalArgumentException("The item id " + id + " is already exists.");
        }
    }

    public void addOrder(Order order) {
        int id = order.getId();
        if (!orders.containsKey(id)) {
            orders.put(id, order);
        }
    }

    public boolean isItemExists(int id) {
        return items.containsKey(id);
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

    public boolean isAllItemsAreBeingSoldByAtLeastOneStore() {
        return items.values().containsAll(itemsSold);
    }

    public void addItemToStore(int itemId, float itemPrice, Store store) {
        Item item = getItem(itemId);
        store.addItem(item, itemPrice);
        addItemIdToItemsSoldIds(item);
    }

    public Set<Item> getItemsThatAreNotBeingSoldByAtLeastOneStore() {
        Set<Item> itemsCopy = new HashSet<>(items.values());
        itemsCopy.removeAll(itemsSold);
        return itemsCopy;
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
            if (order.isItemInTheOrder(id))
                amount += order.getOrderLines().get(id).getQuantity();
        }
        return amount;
    }

    public void createOrder(Date date, int customerLocationX, int customerLocationY, int storeId, Map<Integer, Float> itemsIdsAndQuantities) {
        Location customerLocation = new Location(customerLocationX, customerLocationY);
        Store store = getStore(storeId);
        Order order = new Order(date, customerLocation, store);
        addOrder(order);

        Map<Item, Float> itemsAndQuantities = new HashMap<>();
        itemsIdsAndQuantities.forEach((itemId,itemQuantity) -> {
            Item item = getItem(itemId);
            itemsAndQuantities.put(item, itemQuantity);
        });
        order.addOrderLines(itemsAndQuantities);
        order.finish();
    }

    public boolean isStoreExists(int id) {
        return stores.containsKey(id);
    }
}
