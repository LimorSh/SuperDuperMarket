package course.java.sdm.engine.engine;

import java.util.Map;

public class StoreOrder {

    private final Store store;
    private final Map<Integer, OrderLine> orderLines; //the key is itemId
    private final int totalItems;
    private final float itemsCost;
    private final float deliveryCost;
    private final float totalCost;

    public StoreOrder(Store store, Map<Integer, OrderLine> orderLines,
                      int totalItems, float itemsCost, float deliveryCost, float totalCost) {
        this.store = store;
        this.orderLines = orderLines;
        this.totalItems = totalItems;
        this.itemsCost = itemsCost;
        this.deliveryCost = deliveryCost;
        this.totalCost = totalCost;
    }

    public Store getStore() {
        return store;
    }

    public Map<Integer, OrderLine> getOrderLines() {
        return orderLines;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public float getItemsCost() {
        return itemsCost;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public boolean isContainItem(int id) {
        return orderLines.containsKey(id);
    }
}
