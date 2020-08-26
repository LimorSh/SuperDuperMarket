package course.java.sdm.engine.systemEngine;

import course.java.sdm.engine.Utils;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    private static int numCustomers = 1;
    private final int id;
    private String name;
    private Location location;
    private final Map<Integer, Order> orders;

    public Customer(int id, String name, Location location) {
        this(id, name, location.getCoordinate().x, location.getCoordinate().y);
    }

    public Customer(int id, String name, int xLocation, int yLocation) {
        this.id = id;
        setName(name);
        try {
            this.location = new Location(xLocation, yLocation);
        }
        catch (Exception e) {
            // make different exception here!!!!!!!!!!!!!!!!
//            throw new StoreLocationOutOfRangeException(name, xLocation, yLocation);
        }
        orders = new HashMap<>();
        numCustomers++;
    }

//    public Customer(SDMCustomer sdmCustomer) {
//        this(sdmCustomer.getId(), sdmCustomer.getName(),
//                sdmCustomer.getLocation().getX(), sdmCustomer.getLocation().getY());
//    }

    private void setName(String name) {
        if (!Utils.isStringAnEnglishWord(name)) {
            throw new IllegalArgumentException("The customer name " + name + " is not valid: should contain English letters or spaces only.");
        }
        this.name = name;
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
        if (!orders.containsKey(id)) {
            orders.put(id, order);
        }
//        else {
//            // throw exception
//        }
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
