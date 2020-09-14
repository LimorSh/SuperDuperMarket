package course.java.sdm.engine.engine;
import course.java.sdm.engine.dto.StoreItemDto;
import course.java.sdm.engine.exception.DuplicateStoreItemIdException;
import course.java.sdm.engine.exception.LocationOutOfRangeException;
import course.java.sdm.engine.jaxb.schema.generated.SDMStore;

import java.util.*;

public class Store {

    private final int id;
    private String name;
    private final int ppk;
    private Location location;
    private final Map<Integer, StoreItem> storeItems;
    private final Map<Integer, Order> orders;
    private float totalDeliveriesRevenue;

    public Store(int id, String name, int ppk, Location location) {
        this(id, name, ppk, location.getCoordinate().x, location.getCoordinate().y);
    }

    public Store(int id, String name, int ppk, int xLocation, int yLocation) {
        this.id = id;
        this.name = name.trim();
        this.ppk = ppk;
        setLocation(xLocation, yLocation);
        storeItems = new HashMap<>();
        orders = new HashMap<>();
    }

    public Store(SDMStore sdmStore) {
        this(sdmStore.getId(), sdmStore.getName(),
                sdmStore.getDeliveryPpk(), sdmStore.getLocation().getX(), sdmStore.getLocation().getY());
    }

    private void setLocation(int x, int y) {
        if (!Location.isValidLocation(x, y)) {
            throw new LocationOutOfRangeException(this.getClass().getSimpleName(), name, x, y);
        }
        this.location = new Location(x, y);
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
        if (!isItemInTheStore(id)) {
            storeItems.put(id, new StoreItem(item, price));
        }
        else {
            throw new DuplicateStoreItemIdException(name, item.getName(), id);
        }
    }

    public void addOrder(Order order) {
        int id = order.getId();
        if (!isOrderInTheStore(id)) {
            orders.put(id, order);
        }
//        else {
//            // throw exception
//        }
    }

    public void addDiscount(Discount discount) {
        StoreItem storeItem = storeItems.get(discount.getStoreItemId());
        storeItem.addDiscount(discount);
    }

    public void updateItemPrice(int id, float newPrice) {
        StoreItem storeItem = storeItems.get(id);
        if (storeItem.getPrice() == newPrice) {
            throw new IllegalArgumentException("The new item price is the same as it's current price.");
        }
        storeItems.get(id).setPrice(newPrice);
    }

    public void updateTotalDeliveriesRevenue(Location location) {
        totalDeliveriesRevenue += getDeliveryCost(location);
    }

    public float getDeliveryCost(Location location) {
        float distance = (float) getDistance(location);
        return (distance * ppk);
    }

    public double getDistance(Location location) {
        return (Distance.getDistanceBetweenTwoLocations(location, this.location));
    }

    public float getItemPrice(Item item) {
        int id = item.getId();
        return storeItems.get(id).getPrice();
    }

    public float getItemPrice(int itemId) {
        return storeItems.get(itemId).getPrice();
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

    public boolean isOrderInTheStore(int orderId) {
        return orders.containsKey(orderId);
    }

    public boolean hasDiscounts() {
        for (StoreItem storeItem : storeItems.values()) {
            if (storeItem.hasDiscounts()) {
                return true;
            }
        }
        return false;
    }

    public void deleteItem(int id) {
        if (storeItems.size() > 1) {
            storeItems.remove(id);
            return;
        }

        throw new IllegalArgumentException("Cannot delete the item: The store must sell at least one item.");
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ppk=" + ppk +
                ", location=" + location +
                ", storeItems=" + storeItems +
                ", orders=" + orders +
                ", totalDeliveriesRevenue=" + totalDeliveriesRevenue +
                '}';
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
