package course.java.sdm.engine;
import course.java.sdm.engine.exceptions.ItemDoesNotExistException;
import course.java.sdm.engine.exceptions.DuplicateStoreLocationException;
import java.util.*;

public class SuperDuperMarket {

    private final Map<Integer, Store> stores;
    private final Map<Integer, Item> items;
    private final Map<Integer, Order> orders;
    private final Set<Item> itemsSold;
    private Store[][] storesLocations;

    public SuperDuperMarket() {
        stores = new HashMap<>();
        items = new HashMap<>();
        orders = new HashMap<>();
        itemsSold = new HashSet<>();
        initializeStoresLocations();
    }

    private void initializeStoresLocations() {
        int maxLocation = Location.getMaxLocationValue();
        storesLocations = new Store[maxLocation][maxLocation];
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
            if (isLocationAlreadyExistsForStore(x, y)) {
                throw new DuplicateStoreLocationException(getStoreByLocation(x, y).getName(), x, y);
            }
            storesLocations[x - 1][y - 1] = store;
            stores.put(id, store);
        }
        else {
            Store existentStore = getStore(id);
            String sb = "Duplicate store ID:\n" +
                    store.getName() + " store ID " + id + " already exists for " +
                    existentStore.getName() + " store";
            throw new IllegalArgumentException(sb);
        }
    }

    public boolean isLocationAlreadyExistsForStore(int x, int y) {
        Store s = storesLocations[x - 1][y - 1];
        return s != null;
    }

    public Store getStoreByLocation(int x, int y) {
        return storesLocations[x - 1][y - 1];
    }

    public void addItem(Item item) {
        int id = item.getId();
        if (!isItemExists(id)) {
            items.put(id, item);
        }
        else {
            Item existentItem = getItem(id);
            String sb = "Duplicate item ID:\n" +
                    item.getName() + " item ID " + id + " already exists for " +
                    existentItem.getName() + " item";
            throw new IllegalArgumentException(sb);
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

    public boolean isItemExistsInStore(int storeId, int storeItemId) {
        Store store = getStore(storeId);
        return store.isItemInTheStore(storeItemId);
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
        if (isItemExists(itemId)) {
            Item item = getItem(itemId);
            store.addItem(item, itemPrice);
            addItemIdToItemsSoldIds(item);
        }
        else {
            throw new ItemDoesNotExistException(itemId);
        }
    }

    public void updateItemPriceInStore(int storeId, int storeItemId, float newItemPrice) {
        Store store = stores.get(storeId);
        store.updateItemPrice(storeItemId, newItemPrice);
    }

    public Set<Item> getItemsThatAreNotBeingSoldByAtLeastOneStore() {
        Set<Item> itemsCopy = new HashSet<>(items.values());
        itemsCopy.removeAll(itemsSold);
        return itemsCopy;
    }
    public int getNumberOfStoresSellingTheItem(int id) {
        return ((int) stores.values().stream().filter(store -> store.isItemInTheStore(items.get(id))).count());
    }

    public float getAverageItemPrice(int id) {
        float sum = 0f;
        Item item = items.get(id);
        for (Store store : stores.values()) {
            if (store.isItemInTheStore(item)) {
                sum += store.getItemPrice(item);
            }
        }
        return (sum / getNumberOfStoresSellingTheItem(id));
    }

    public float getTotalAmountOfItemSells(int id) {
        float amount = 0f;

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
