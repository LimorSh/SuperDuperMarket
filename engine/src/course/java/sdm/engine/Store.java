package course.java.sdm.engine;

import course.java.sdm.engine.exceptions.DuplicateStoreItemIdException;
import course.java.sdm.engine.exceptions.StoreLocationOutOfRangeException;
import course.java.sdm.engine.jaxb.schema.generated.SDMStore;

import java.text.DecimalFormat;
import java.util.*;

public class Store {

    private final int id;
    private String name;
    private final int ppk;
    private final Location location;
    private final Map<Integer, StoreItem> storeItems;
    private final Map<Integer, Order> orders;
    private float totalDeliveriesRevenue;

    public Store(int id, String name, int ppk, Location location) {
        this(id, name, ppk, location.getCoordinate().x, location.getCoordinate().y);
    }

    public Store(int id, String name, int ppk, int xLocation, int yLocation) {
        this.id = id;
        setName(name);
        this.ppk = ppk;
        try {
            this.location = new Location(xLocation, yLocation);
        }
        catch (Exception e) {
            throw new StoreLocationOutOfRangeException(name, xLocation, yLocation);
        }
        storeItems = new HashMap<>();
        orders = new HashMap<>();
    }

    public Store(SDMStore sdmStore) {
        this(sdmStore.getId(), sdmStore.getName().toLowerCase(),
                sdmStore.getDeliveryPpk(), sdmStore.getLocation().getX(), sdmStore.getLocation().getY());
    }

    private void setName(String name) {
        if (!Utils.isStringAnEnglishWord(name)) {
            throw new IllegalArgumentException("The store name " + name + " is not valid: should contain English letters or spaces only.");
        }
        this.name = name.toLowerCase();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPpk() {
        return ppk;
    }

    public Collection<Order> getOrders() {
        return orders.values();
    }

    public Collection<StoreItem> getStoreItems() {
        return storeItems.values();
    }

    public float getTotalDeliveriesRevenue() {
        return totalDeliveriesRevenue;
    }

    public Location getLocation() {
        return location;
    }

    public void addItem(Item item, float price) {
        int id = item.getId();
        if (!storeItems.containsKey(id)) {
            storeItems.put(id, new StoreItem(item, price));
        }
        else {
            Item existedItem = storeItems.get(id);
            throw new DuplicateStoreItemIdException(name, existedItem.getName(), id, item.getName());
        }
    }

    public void addOrder(Order order) {
        int id = order.getId();
        if (!orders.containsKey(id)) {
            orders.put(id, order);
        }
        else {
            // throw exception
        }
    }

    public void updateTotalDeliveriesRevenue(Location location) {
        totalDeliveriesRevenue += getDeliveryCost(location);
    }

    public float getDeliveryCost(Location location) {
        float distance = (float) (Distance.getDistanceBetweenTwoLocations(location, this.location));
        return (distance * ppk);
    }

    public float getItemPrice(Item item) {
        int id = item.getId();
        return storeItems.get(id).getPrice();
    }

    public float getItemPrice(int itemId) {
        return storeItems.get(itemId).getPrice();
    }

    public float getTotalNumberSold(Item item) {
        int id = item.getId();
        return storeItems.get(id).getTotalSold();
    }

    public void updateTotalNumberSoldItem(Item item, float quantity) {
        int id = item.getId();
        storeItems.get(id).updateTotalNumberSold(quantity);
    }

    public boolean isItemInTheStore(Item item) {
        int id = item.getId();
        return storeItems.containsKey(id);
    }

    public boolean isItemInTheStore(int itemId) {
        return storeItems.containsKey(itemId);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        return "ID: " + id +
                ", Name:'" + name + '\'' +
                ", PPK: " + ppk +
                ", Total Deliveries Revenue: " + df.format(totalDeliveriesRevenue) +
                "\nStore items: " + storeItems +
                "\nStore Orders: " + orders
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        if (id != store.id) return false;
        return Objects.equals(location, store.location);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
