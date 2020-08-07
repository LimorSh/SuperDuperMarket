package course.java.sdm.engine;

import java.util.*;

public class Store {

    private static class ItemAttributes {
        private float price;
        private int totalNumberSold;

        public ItemAttributes(float price) {
            this.price = price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public void updateTotalNumberSold(int quantity) {
            this.totalNumberSold += quantity;
        }

        @Override
        public String toString() {
            return ", price: " + price +
                   ", quantity: " + totalNumberSold + '}'
                   ;
        }
    }

    private final int id;
    private final String name;
    private final float ppk;
    private final Location location;
    private final Map<Item, ItemAttributes> items;
    private int numSoldItems;
    private final Set<Order> orders;
    private float totalDeliveriesRevenue;

    public Store(int id, String name, float ppk, Location location) {
        this.id = id;
        this.name = name;
        this.ppk = ppk;
        this.location = location;
        items = new HashMap<>();
        orders = new HashSet<>();
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

    public Map<Item, ItemAttributes> getItems() {
        return items;
    }

    public float getTotalDeliveriesRevenue() {
        return totalDeliveriesRevenue;
    }

    public Location getLocation() {
        return location;
    }

    public void addItem(Item item, float price) {
        items.put(item, new ItemAttributes(price));
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

    public float getDeliveryCost(Location location) {
        return (float) (Distance.getDistanceBetweenTwoLocations(location, this.location) * ppk);
    }

    public float getItemPrice(Item item) {
        return items.get(item).price;
    }

    public float getTotalNumberSold(Item item) {
        return items.get(item).totalNumberSold;
    }

    public void updateTotalNumberSoldItem(Item item, float quantity) {
        int quantityInt = 1;    // item per weight
        if (item.getPurchaseType().equals(Item.PurchaseType.PER_UNIT)) {
            quantityInt = (int) quantity;
        }
        items.get(item).updateTotalNumberSold(quantityInt);
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name:'" + name + '\'' +
                ", PPK: " + ppk +
                ", Total Deliveries Revenue: " + totalDeliveriesRevenue +
                "\nStore items: " + items +
                "\nStore Orders: " + orders +
                "\n" + "__________________"
                ;
    }
}
