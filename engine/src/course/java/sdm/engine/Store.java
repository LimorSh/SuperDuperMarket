package course.java.sdm.engine;

import java.util.Dictionary;
import java.util.Map;
import java.util.Set;

public class Store {

    private static int numStores = 1;
    private final int id;
    private final String name;
    private Map<Item, Dictionary> items;
    private int numSoldItems;
    private final float ppk;
    private Set<Order> orders;
    private float totalDeliveriesRevenue;
    private final Location location;

    public Store(String name, float ppk, Location location) {
        this.id = numStores;
        this.name = name;
        this.ppk = ppk;
        this.location = location;
        numStores++;
    }

    public static int getNumStores() {
        return numStores;
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

    public Set<Order> getOrders() {
        return orders;
    }

    public Map<Item, Dictionary> getItems() {
        return items;
    }

    public float getTotalDeliveriesRevenue() {
        return totalDeliveriesRevenue;
    }

    public Location getLocation() {
        return location;
    }

    public void addItem(Item item, float price) {
    }

    public void increaseNumItemsSoldByOne() {
        numSoldItems++;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void updateTotalDeliveriesRevenue(float deliveriesRevenue) {
        totalDeliveriesRevenue += totalDeliveriesRevenue;
    }
}
