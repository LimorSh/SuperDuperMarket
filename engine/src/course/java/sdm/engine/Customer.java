package course.java.sdm.engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Customer {

    private static int numCustomers = 1;
    private final int id;
    private String name;
    private final Map<Integer, Order> orders;

    public Customer(int id, String name) {
        this.id = numCustomers;
        setName(name);
        orders = new HashMap<>();
        numCustomers++;
    }

    private void setName(String name) {
        if (!Utils.isStringAnEnglishWord(name)) {
            throw new IllegalArgumentException("The store name " + name + " is not valid: should contain English letters or spaces only.");
        }
        this.name = name.toLowerCase();
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
