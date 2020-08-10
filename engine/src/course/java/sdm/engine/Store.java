package course.java.sdm.engine;

import course.java.sdm.engine.jaxb.schema.generated.SDMStore;

import java.text.DecimalFormat;
import java.util.*;

public class Store {

//    private static class ItemAttributes {
//        private float price;
//        private int totalNumberSold;
//
//        public ItemAttributes(float price) {
//            this.price = price;
//        }
//
//        public void setPrice(float price) {
//            this.price = price;
//        }
//
//        public void updateTotalNumberSold(int quantity) {
//            this.totalNumberSold += quantity;
//        }
//
//        @Override
//        public String toString() {
//            return ", price: " + price +
//                   ", quantity: " + totalNumberSold + '}'
//                   ;
//        }
//    }

    private final int id;
    private final String name;
    private final float ppk;
    private final Location location;
//    private final Map<Item, ItemAttributes> items;

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

//    public Set<StoreItem> getItems() {
//        return items;
//    }

    public float getTotalDeliveriesRevenue() {
        return totalDeliveriesRevenue;
    }

    public Location getLocation() {
        return location;
    }

    public void addItem(Item item, float price) {
        storeItems.put(item.getId(), new StoreItem(item, price));
    }

//    public void addItem(Item item, float price) {
//        StoreItem storeItem = new StoreItem(item, price);
//        items.add(storeItem);
//    }
//
//    public void addItem(StoreItem storeItem) {
//        items.add(storeItem);
//    }
//
//    public void addItem(int id, String name, Item.PurchaseType purchaseType, float price) {
//        StoreItem storeItem = new StoreItem(id, name, purchaseType, price);
//        items.add(storeItem);
//    }

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

    public float getTotalNumberSold(Item item) {
        int id = item.getId();
        return storeItems.get(id).getTotalNumberSold();
    }

//    public float getItemPrice(StoreItem storeItem) {
//        return items.;
//    }

//    public float getTotalNumberSold(Item item) {
//        return items.get(item).totalNumberSold;
//    }

    public void updateTotalNumberSoldItem(Item item, float quantity) {
        int quantityInt = 1;    // item per weight
        if (item.getPurchaseType().equals(Item.PurchaseType.PER_UNIT)) {
            quantityInt = (int) quantity;
        }
        int id = item.getId();
        storeItems.get(id).updateTotalNumberSold(quantityInt);
    }

    public boolean isItemInTheStore(Item item) {
        int id = item.getId();
        return storeItems.containsKey(id);
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
                "\nStore Orders: " + orders +
                "\n" + "__________________"
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
