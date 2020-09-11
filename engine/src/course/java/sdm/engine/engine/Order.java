package course.java.sdm.engine.engine;
import course.java.sdm.engine.Constants;

import java.util.*;

public class Order {

    public enum OrderCategory {
        STATIC(Constants.ORDER_CATEGORY_STATIC_STR),
        DYNAMIC(Constants.ORDER_CATEGORY_DYNAMIC_STR),
        ;

        private final String orderCategoryStr;

        OrderCategory(String orderCategory) {
            this.orderCategoryStr = orderCategory;
        }

        public String getOrderCategoryStr() {
            return orderCategoryStr;
        }
    }

    private static int numOrders = 1;
    private final int id;
    private final Date date;
    private final Customer customer;
    private final Map<Integer, StoreOrder> storesOrder;     //The key is store id
    private float itemsCost;
    private float deliveryCost;
    private int totalItems;
    private final OrderCategory orderCategory;

    public Order(Customer customer, Date date, String orderCategory) {
        this.id = numOrders;
        this.customer = customer;
        this.date = date;
        this.orderCategory = convertStringToOrderCategory(orderCategory);
        storesOrder = new HashMap<>();
        numOrders++;
    }

    private static Order.OrderCategory convertStringToOrderCategory(String orderCategory) {
        if (orderCategory.toLowerCase().contains(Constants.ORDER_CATEGORY_STATIC_STR)) {
            return Order.OrderCategory.STATIC;
        }
        return Order.OrderCategory.DYNAMIC;
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

    public Map<Integer, StoreOrder> getStoresOrder() {
        return storesOrder;
    }

    public OrderCategory getOrderCategory() {
        return orderCategory;
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

    public boolean isItemInTheOrder(int id) {
        for (StoreOrder storeOrder : storesOrder.values()) {
            if (storeOrder.isContainItem(id)) {
                return true;
            }
        }
        return false;
    }

    public float getItemQuantity(int id) {
        float quantity = 0f;
        for (StoreOrder storeOrder : storesOrder.values()) {
            if (storeOrder.isContainItem(id)) {
                quantity = storeOrder.getItemQuantity(id);
            }
        }
        return quantity;
    }

    public void addStoreOrder(Store store, Map<Item, Float> itemsAndQuantities) {
        store.addOrder(this);

        Map<Integer, OrderLine> orderLines = new HashMap<>();

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
            store.updateTotalNumberSoldItem(item, itemQuantity);
        });

        StoreOrder storeOrder = new StoreOrder(date, store, orderLines);
        storeOrder.SetValues(customer.getLocation());
        storesOrder.put(store.getId(), storeOrder);
        setValues();
    }

    private void setValues() {
        for (StoreOrder storeOrder : this.storesOrder.values()) {
            itemsCost += storeOrder.getItemsCost();
            deliveryCost += storeOrder.getDeliveryCost();
            totalItems += storeOrder.getTotalItems();
        }
    }

    public void addStoresOrder(Map<Store, Map<Item, Float>> storesToItemsAndQuantities) {
        storesToItemsAndQuantities.forEach(this::addStoreOrder);
    }

    public void finish(Store store) {
        store.updateTotalDeliveriesRevenue(customer.getLocation());
        customer.addOrder(this);
    }

    public void finish(Collection<Store> stores) {
        for (Store store : stores) {
            finish(store);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", customer=" + customer +
                ", storesOrder=" + storesOrder +
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