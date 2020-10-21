package course.java.sdm.engine.engine;
import course.java.sdm.engine.exception.LocationOutOfRangeException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Customer {

    private static int numCustomers = 1;
    private final int id;
    private final String name;
    private final Map<Integer, Order> orders;

    public Customer(String name) {
        this.id = numCustomers;
        this.name = name.trim();
        orders = new HashMap<>();
        numCustomers++;
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

    public Collection<Order> getOrders() {
        return orders.values();
    }

    public void addOrder(Order order) {
        int id = order.getId();
        if (!orders.containsKey(id)) {
            orders.put(id, order);
        }
//        else {
//            // throw exception
//        }
    }

    public boolean isOrdersEmpty() {
        return orders.isEmpty();
    }

    public int getNumberOfOrders() {
        return orders.size();
    }

    public float getAverageItemsCost() {
        if (isOrdersEmpty()) {
            return 0f;
        }

        float sum = 0f;

        for (Order order : orders.values()) {
            sum += order.getItemsCost();
        }

        return (sum / orders.size());
    }

    public float getAverageDeliveriesCost() {
        if (isOrdersEmpty()) {
            return 0f;
        }

        float sum = 0f;

        for (Order order : orders.values()) {
            sum += order.getDeliveryCost();
        }

        return (sum / orders.size());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
