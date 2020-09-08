package course.java.sdm.engine.engine;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private static int numOrders = 1;
    private final int id;
    private final Date date;
    private final Customer customer;
    private final Store store;
    private final Map<Integer, OrderLine> orderLines; //the key is itemId
//    private final Map<Integer, Set<OrderLine>> orderLines; //the key is itemId
    private float itemsCost;
    private float deliveryCost;
    private int totalItems;

    public Order(Customer customer, Date date, Store store) {
        this.id = numOrders;
        this.customer = customer;
        this.date = date;
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
            }
            else {
                float totalQuantity = itemQuantity;
                totalQuantity += orderLines.get(itemId).getQuantity();
                orderLines.get(itemId).setQuantity(totalQuantity);
            }
            updateOrderData(item, itemQuantity);
        });
    }

    private void updateOrderData(Item item, float itemQuantity) {
        updateTotalItems(item.getPurchaseCategory(), itemQuantity);
        updateItemsCost(item, itemQuantity);
        updateTotalNumberSoldItemInStore(item, itemQuantity);
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
        store.updateTotalDeliveriesRevenue(customer.getLocation());
        customer.addOrder(this);
    }

    public void updateDeliveryCost() {
        float cost = store.getDeliveryCost(customer.getLocation());
        deliveryCost += cost;
    }

    public void updateTotalNumberSoldItemInStore(Item item, float quantity) {
        store.updateTotalNumberSoldItem(item, quantity);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", customer=" + customer +
                ", store=" + store +
                ", orderLines=" + orderLines +
                ", itemsCost=" + itemsCost +
                ", deliveryCost=" + deliveryCost +
                ", totalItems=" + totalItems +
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
