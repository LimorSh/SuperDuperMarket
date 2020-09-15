package course.java.sdm.engine.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class StoreOrder {

    private final Date date;
    private final Store store;
    private final Map<Integer, OrderLine> orderLines; //the key is itemId
    private Map<String, ArrayList<Offer>> appliedOffers;  //the key is discount name
    private int totalItems;
    private float itemsCost;
    private float deliveryCost;
    private float totalCost;
    private double distanceFromCustomer;

    public StoreOrder(Date date, Store store, Map<Integer, OrderLine> orderLines) {
        this.date = date;
        this.store = store;
        this.orderLines = orderLines;
    }

    public void SetValues(Location customerLocation) {
        setItemsCost();
        setDeliveryCost(customerLocation);
        setTotalCost();
        setTotalItems();
        setDistanceFromCustomer(customerLocation);
    }

    private void setItemsCost() {
        for (OrderLine orderline : orderLines.values()) {
            this.itemsCost += orderline.getTotalCost();
        }
    }

    private void setDeliveryCost(Location customerLocation) {
        this.deliveryCost = store.getDeliveryCost(customerLocation);
    }

    private void setTotalCost() {
        this.totalCost = itemsCost + deliveryCost;
    }

    private void setTotalItems() {
        for (OrderLine orderline : orderLines.values()) {
            this.totalItems += orderline.calcTotalItems();
        }
    }

    private void setDistanceFromCustomer(Location customerLocation) {
        this.distanceFromCustomer = store.getDistance(customerLocation);
    }

    public void setAppliedOffers(Map<String, ArrayList<Offer>> appliedOffers) {
        this.appliedOffers = appliedOffers;
        appliedOffers.forEach((discountName,offers) -> {
            for (Offer offer : offers) {
                float cost = offer.getTotalCost();
                this.itemsCost += cost;
                this.totalCost += cost;
            }
        });
    }

    public Date getDate() {
        return date;
    }

    public Store getStore() {
        return store;
    }

    public Map<Integer, OrderLine> getOrderLines() {
        return orderLines;
    }

    public Map<String, ArrayList<Offer>> getAppliedOffers() {
        return appliedOffers;
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

    public float getItemQuantity(int id) {
        return orderLines.get(id).getQuantity();
    }

    public double getDistanceFromCustomer() {
        return distanceFromCustomer;
    }
}