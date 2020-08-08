package course.java.sdm.engine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private final int id;
    private Date date;
    private final Customer customer;   //maybe don't need
    private final Location customerLocation;
    private final Store store;
    private final Map<Item, Float> items;
    private float itemsCost;
    private float deliveryCost;
//    private float totalCost;

    public Order(int id, Date date, Customer customer, Location customerLocation, Store store) throws ParseException {
        this.id = id;
//        this.date = date;
//        String dateStr = date.toString();
//        this.date = new SimpleDateFormat(dateFormat).parse(dateStr);
        setDate(date.toString());
        this.customer = customer;
        this.customerLocation = customerLocation;
        this.store = store;
        items = new HashMap<>();
    }

    public Order(int id, String dateStr, Customer customer, Location customerLocation, Store store) throws ParseException {
        this.id = id;
        setDate(dateStr);
//        this.date = new SimpleDateFormat(dateFormat).parse(dateStr);
        this.customer = customer;
        this.customerLocation = customerLocation;
        this.store = store;
        items = new HashMap<>();
    }

    private void setDate(String dateStr) throws ParseException {
        String dateFormat = Configurations.orderDateFormat;
        this.date = new SimpleDateFormat(dateFormat).parse(dateStr);
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Store getStore() {
        return store;
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
        items.put(item, quantity);
        updateItemsCost(item);
        updateDeliveryCost();
        updateTotalNumberSoldItemInStore(item, quantity);
    }

    public void updateItemsCost(Item item) {
        float itemCost = (store.getItemPrice(item));
        itemsCost += itemCost;
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
        float totalCost = getTotalCost();
        int totalItem = items.size();
        return "Order{" +
                "Date: " + date +
                ", Total items: " + totalItem +
                ", Items Cost: " + itemsCost +
                ", Delivery Cost: " + deliveryCost +
                ", Total Cost: " + totalCost +
                '}';
    }
}
