package course.java.sdm.engine;

import course.java.sdm.engine.jaxb.schema.generated.SDMStore;

import java.text.DecimalFormat;
import java.util.*;

public class Store {

    private final int id;
    private final String name;
    private final float ppk;
    private final Location location;
    private final Map<Integer, StoreItem> storeItems;
    private int numSoldItems;
    private final Map<Integer, Order> orders;
    private float totalDeliveriesRevenue;

    public Store(int id, String name, float ppk, Location location) {
        this.id = id;
        this.name = name;
        this.ppk = ppk;
        this.location = location;
        storeItems = new HashMap<>();
        orders = new HashMap<>();
    }

    public Store(SDMStore sdmStore) {
        this.id = sdmStore.getId();
        this.name = sdmStore.getName().toLowerCase();
        this.ppk = sdmStore.getDeliveryPpk();
        this.location = new Location(sdmStore.getLocation());
        storeItems = new HashMap<>();
        orders = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumSoldItems() {
        return numSoldItems;
    }

    public float getPpk() {
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
        storeItems.put(item.getId(), new StoreItem(item, price));
    }

    public void increaseNumItemsSoldByOne() {
        numSoldItems++;
    }

    public void addOrder(Order order) {
        int id = order.getId();
        if (!orders.containsKey(id)) {
            orders.put(id, order);
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
        int quantityInt = 1;    // item per weight
        if (item.getPurchaseCategory().equals(Item.PurchaseCategory.PER_UNIT)) {
            quantityInt = (int) quantity;
        }
        int id = item.getId();
        storeItems.get(id).updateTotalNumberSold(quantityInt);
    }

    public boolean isItemInTheStore(Item item) {
        int id = item.getId();
        return storeItems.containsKey(id);
    }

    public boolean isItemInTheStore(int itemId) {
        return storeItems.containsKey(itemId);
    }

    public boolean isStoreActive() {
        return (!storeItems.isEmpty());
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
