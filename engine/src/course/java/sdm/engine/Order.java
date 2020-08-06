package course.java.sdm.engine;

import java.util.Date;
import java.util.Map;

public class Order {

    private static int numOrders = 1;
    private final int id;
    private final Date date;
    private final int customerId;
    private final int storeId;
    private Map<Item, Float> items;
    private float itemsCost;
    private float deliveryCost;
    private float totalCost;

    public Order(int id, Date date, int customerId, int storeId) {
        this.id = numOrders;
        this.date = date;
        this.customerId = customerId;
        this.storeId = storeId;
        numOrders++;
    }

    public static int getNumOrders() {
        return numOrders;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getStoreId() {
        return storeId;
    }

    public Map<Item, Float> getItems() {
        return items;
    }

    public float getItemsCost() {
        return itemsCost;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public float getTotalCost() {
        return (itemsCost + deliveryCost);
    }

    public void addItem(Item item, float quantity) {
    }

    public void updateItemsCost(float cost) {
        itemsCost += cost;
    }

    public void updateDeliveryCost(float cost) {
        deliveryCost += cost;
    }
}
