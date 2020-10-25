package course.java.sdm.engine.engine;

import course.java.sdm.engine.engine.notifications.NotificationManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class StoreOrder {

    private final int orderId;
    private final Date date;
    private final String customerName;
    private final Location customerLocation;
    private final Store store;
    private final Map<Integer, OrderLine> orderLines; //the key is itemId
    private Map<String, ArrayList<Offer>> appliedOffers;  //the key is discount name
    private int totalItems;
    private float itemsCost;
    private float deliveryCost;
    private float totalCost;
    private double distanceFromCustomer;
    private StoreFeedback storeFeedback;

    public StoreOrder(int orderId, Date date, String customerName, Location customerLocation,
                      Store store, Map<Integer, OrderLine> orderLines) {
        this.orderId = orderId;
        this.date = date;
        this.customerName = customerName;
        this.customerLocation = customerLocation;
        this.store = store;
        this.orderLines = orderLines;
    }

    public void SetValues(Location customerLocation, Map<String, ArrayList<Offer>> appliedOffers) {
        this.appliedOffers = appliedOffers;
        setItemsCost();
        setDeliveryCost(customerLocation);
        setTotalCost();
        setItemsCostAndTotalCostOfAppliedOffers();
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

    public StoreFeedback getStoreFeedback() {
        return storeFeedback;
    }

    public void setStoreFeedback(NotificationManager notificationManager, String zoneName,
                                 Date date, String customerName, ArrayList<String> storeRateDetails) {
        int rate = Integer.parseInt(storeRateDetails.get(0));
        String feedback = storeRateDetails.get(1);
        this.storeFeedback = new StoreFeedback(store.getId(), store.getName(), date,
                customerName, rate, feedback);
        notificationManager.addStoreFeedbackNotification(zoneName, store.getOwnerName(),
                store.getName(), customerName, rate);
    }

    private void setTotalItems() {
        ArrayList<Integer> itemIds = new ArrayList<>();
        for (OrderLine orderline : orderLines.values()) {
            Item item = orderline.getItem();
            float quantity = orderline.getQuantity();
            calcTotalItems(itemIds, item, quantity);
        }
        appliedOffers.forEach((discountName, offers) -> {
            for (Offer offer : offers) {
                Item item = offer.getItem();
                float quantity = (float) offer.getQuantity();
                calcTotalItems(itemIds, item, quantity);
            }
        });
    }

    public void calcTotalItems(ArrayList<Integer> itemIds, Item item, float quantity) {
        if (item.getPurchaseCategory().getPurchaseCategoryStr()
                .equals(Item.PurchaseCategory.PER_WEIGHT.getPurchaseCategoryStr())) {
            if (!itemIds.contains(item.getId())) {
                itemIds.add(item.getId());
                this.totalItems += 1;
            }
        }
        else {
            this.totalItems += (int) quantity;
        }
    }

    private void setDistanceFromCustomer(Location customerLocation) {
        this.distanceFromCustomer = store.getDistance(customerLocation);
    }

    public void setItemsCostAndTotalCostOfAppliedOffers() {
        appliedOffers.forEach((discountName,offers) -> {
            for (Offer offer : offers) {
                float cost = offer.getTotalCost();
                this.itemsCost += cost;
                this.totalCost += cost;
            }
        });
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Location getCustomerLocation() {
        return customerLocation;
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