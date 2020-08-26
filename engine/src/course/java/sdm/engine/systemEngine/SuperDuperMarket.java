package course.java.sdm.engine.systemEngine;
import course.java.sdm.engine.exceptions.DuplicateElementIdException;
import course.java.sdm.engine.exceptions.ItemDoesNotExistInTheStoreException;
import course.java.sdm.engine.exceptions.ItemDoesNotExistInTheSuperException;
import course.java.sdm.engine.exceptions.DuplicateLocationException;
import java.util.*;

public class SuperDuperMarket {

    private final Map<Integer, Customer> customers;
    private final Map<Integer, Store> stores;
    private final Map<Integer, Item> items;
    private final Map<Integer, Order> orders;
    private final Set<Item> itemsSold;
    private Object[][] locationGrid;

    public SuperDuperMarket() {
        customers = new HashMap<>();
        stores = new HashMap<>();
        items = new HashMap<>();
        orders = new HashMap<>();
        itemsSold = new HashSet<>();
        initializeLocationGrid();
    }

    private void initializeLocationGrid() {
        int maxLocation = Location.getMaxLocationValue();
        locationGrid = new Object[maxLocation][maxLocation];
    }

    public Collection<Customer> getCustomers() {
        return customers.values();
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

    public void addCustomer(Customer customer) {
        int id = customer.getId();
        if (!isCustomerExists(id)) {
            int x = customer.getLocation().getCoordinate().x;
            int y = customer.getLocation().getCoordinate().y;
            if (isLocationAlreadyExists(x, y)) {
                throw new DuplicateLocationException(getObjectByLocation(x, y), x, y);
            }
            locationGrid[x - 1][y - 1] = customer;
            customers.put(id, customer);
        }
        else {
            Customer existentCustomer = getCustomer(id);
            throw new DuplicateElementIdException(customer, existentCustomer);
        }
    }

    public void addStore(Store store) {
        int id = store.getId();
        if (!isStoreExists(id)) {
            int x = store.getLocation().getCoordinate().x;
            int y = store.getLocation().getCoordinate().y;
            if (isLocationAlreadyExists(x, y)) {
                throw new DuplicateLocationException(getObjectByLocation(x, y), x, y);
            }
            locationGrid[x - 1][y - 1] = store;
            stores.put(id, store);
        }
        else {
            Store existentStore = getStore(id);
            throw new DuplicateElementIdException(store, existentStore);
        }
    }

    public boolean isLocationAlreadyExists(int x, int y) {
        Object o = locationGrid[x - 1][y - 1];
        return o != null;
    }

    public Object getObjectByLocation(int x, int y) {
        return locationGrid[x - 1][y - 1];
    }

    public void addItem(Item item) {
        int id = item.getId();
        if (!isItemExists(id)) {
            items.put(id, item);
        }
        else {
            Item existentItem = getItem(id);
            throw new DuplicateElementIdException(item, existentItem);
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

    private Customer getCustomer(int id) {
        return customers.get(id);
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
        return itemsSold.containsAll(items.values());
    }

    public void addItemToStore(int itemId, float itemPrice, Store store) {
        if (isItemExists(itemId)) {
            Item item = getItem(itemId);
            store.addItem(item, itemPrice);
            addItemIdToItemsSoldIds(item);
        }
        else {
            throw new ItemDoesNotExistInTheSuperException(itemId);
        }
    }

    public void addItemToStore(int itemId, float itemPrice, int storeId) {
        Store store = stores.get(storeId);
        addItemToStore(itemId, itemPrice, store);
    }

    public void updateItemPriceInStore(int storeId, float newItemPrice, int storeItemId) {
        Store store = stores.get(storeId);
        if (!store.isItemInTheStore(storeItemId)) {
            Item item = items.get(storeItemId);
            throw new ItemDoesNotExistInTheStoreException(store.getName(), item.getName(), storeItemId);
        }
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

    public boolean isCustomerExists(int id) {
        return customers.containsKey(id);
    }

    public void deleteItemFromStore(int storeItemId, int storeId) {
        if (getNumberOfStoresSellingTheItem(storeItemId) == 1) {
            throw new IllegalArgumentException("The item is currently being sold by this store only." +
                    "\nItem must be sell in at least one store in the super market.");
        }
        else {
            Store store = stores.get(storeId);
            store.deleteItem(storeItemId);
        }

    }
}
