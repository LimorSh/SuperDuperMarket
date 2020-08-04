package course.java.sdm.engine;

import java.util.Dictionary;
import java.util.Map;
import java.util.Set;

public class Store {

    private static int numStores = 1;
    private final int id;
    private final String name;
    private Map<Item, Dictionary> items;
    private int numItemsSold;
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
}
