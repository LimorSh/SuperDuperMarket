package course.java.sdm.engine;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Store {

    public class ItemAttributes {
        private float price;
        private int quantity = 0;

        public ItemAttributes(float price) {
            this.price = price;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return ", price: " + price +
                   ", quantity: " + quantity + '}'
                   ;
        }
    }

    private final int id;
    private final String name;
    private final float ppk;
    private final Location location;
    private Map<Item, ItemAttributes> items;
    private int numSoldItems;
    //private Set<Order> orders;
    private float totalDeliveriesRevenue;

    public Store(int id, String name, float ppk, Location location) {
        this.id = id;
        this.name = name;
        this.ppk = ppk;
        this.location = location;
        items = new HashMap<>();
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

//    public Set<Order> getOrders() {
//        return orders;
//    }

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

//    public void addOrder(Order order) {
//        orders.add(order);
//    }

    public void updateTotalDeliveriesRevenue(float deliveriesRevenue) {
        totalDeliveriesRevenue += totalDeliveriesRevenue;
    }

    @Override
    public String toString() {
        return
                "ID: " + id +
                ", Name:'" + name + '\'' +
                ", PPK: " + ppk +
                ", Total Deliveries Revenue: " + totalDeliveriesRevenue +
                "\nStore items: " + items +
                "\n" + "__________________"
                ;
    }
}
