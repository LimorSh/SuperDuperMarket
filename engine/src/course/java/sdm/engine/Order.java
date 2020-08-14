package course.java.sdm.engine;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private static int numOrders = 1;
    private final int id;
    private final Date date;
//    private final Customer customer;   //maybe don't need
    private final Location customerLocation;
    private final Store store;
    private final Map<Integer, OrderLine> orderLines; //the key is itemId
    private float itemsCost;
    private float deliveryCost;
    private int totalItems;
//    private float totalCost;

    public Order(Date date, Location customerLocation, Store store) {
        this.id = numOrders;
        this.date = date;
        this.customerLocation = customerLocation;
        this.store = store;
        orderLines = new HashMap<>();
        store.addOrder(this);
        numOrders++;
    }

    public int getId() {
        return id;
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

    public float getItemsCost() {
        return itemsCost;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public float getTotalCost() {
        return (itemsCost + deliveryCost);
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getTotalItemsTypes() {
        return orderLines.keySet().size();
    }

    public boolean isItemInTheOrder(int id) {
        return orderLines.containsKey(id);
    }

    public void addOrderLines(Map<Item, Float> itemsAndQuantities) {
        itemsAndQuantities.forEach((item,itemQuantity) -> {
            int itemId = item.getId();
            float itemPrice = store.getItemPrice(itemId);
            if (!orderLines.containsKey(itemId)) {
                OrderLine orderLine = new OrderLine(item, itemQuantity, itemPrice);
                orderLines.put(itemId, orderLine);
                updateTotalItems(item.getPurchaseCategory(), itemQuantity);
                updateItemsCost(item, itemQuantity);
                updateTotalNumberSoldItemInStore(item, itemQuantity);
            }
            // need to finish this - not ok!
//            else {
//                float totalQuantity = itemQuantity;
//                totalQuantity += orderLines.get(itemId).getQuantity();
//                orderLine = new OrderLine(item, totalQuantity);
//
//                orderLines.put(itemId, orderLine);
//                updateTotalItems(item.getPurchaseType(), totalQuantity);
//
//
//            }
        });
    }

    public void updateTotalItems(Item.PurchaseCategory purchaseCategory, float quantity) {
        if (purchaseCategory.equals(Item.PurchaseCategory.PER_UNIT))
            totalItems += (int) quantity;
        else
            totalItems++;
    }

    public void updateItemsCost(Item item, float quantity) {
        float itemCost = (store.getItemPrice(item));
        itemsCost += (itemCost * quantity);
    }

    public void finish() {
        updateDeliveryCost();
        store.updateTotalDeliveriesRevenue(customerLocation);
    }

    public void updateDeliveryCost() {
        float cost = store.getDeliveryCost(customerLocation);
        deliveryCost += cost;
    }

    public void updateTotalNumberSoldItemInStore(Item item, float quantity) {
        store.updateTotalNumberSoldItem(item, quantity);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        float totalCost = getTotalCost();
        int totalItem = orderLines.size();
        return "Order{" +
                "Date: " + date +
                ", Total items: " +  df.format(totalItem) +
                ", Items Cost: " +  df.format(itemsCost) +
                ", Delivery Cost: " + df.format(deliveryCost) +
                ", Total Cost: " +  df.format(totalCost) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id == order.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
