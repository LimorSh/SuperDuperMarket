package course.java.sdm.engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Customer {

    private static int numCustomers = 1;
    private final int id;
    private final String name;
    private final Map<Integer, Order> orders;

    public Customer(int id, String name) {
        this.id = numCustomers;
        this.name = name;
        numCustomers++;
        orders = new HashMap<>();
    }

    public static int getNumCustomers() {
        return numCustomers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        int id = order.getId();
        orders.put(id, order);
    }
}
