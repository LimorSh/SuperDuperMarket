package course.java.sdm.engine.engine;

import java.util.Map;

public class StoreOrder {

    private final Store store;
    private final Map<Integer, OrderLine> orderLines; //the key is itemId
    private int totalItems;
    private float itemsCost;
    private float deliveryCost;
    private float totalCost;


    public StoreOrder(Store store, Map<Integer, OrderLine> orderLines) {
        this.store = store;
        this.orderLines = orderLines;
    }

    public void SetValues(Location customerLocation) {
        setItemsCost();
        setDeliveryCost(customerLocation);
        setTotalCost();
        setTotalItems();
    }

    public void setItemsCost() {
        for (OrderLine orderline : orderLines.values()) {
            this.itemsCost += orderline.calcItemCost();
        }
    }

    public void setDeliveryCost(Location customerLocation) {
        this.deliveryCost = store.getDeliveryCost(customerLocation);
    }

    public void setTotalCost() {
        this.totalCost = itemsCost + deliveryCost;
    }

    public void setTotalItems() {
        for (OrderLine orderline : orderLines.values()) {
            this.totalItems += orderline.calcTotalItems();
        }
    }

    public Store getStore() {
        return store;
    }

//    public Map<Integer, OrderLine> getOrderLines() {
//        return orderLines;
//    }

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

    public float getItemQuantity(int id) {
        return orderLines.get(id).getQuantity();
    }
}