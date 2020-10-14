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
    private final Location customerLocation;
    private final Map<Integer, StoreOrder> storesOrder;     //The key is store id
    private float itemsCost;
    private float deliveryCost;
    private final OrderCategory orderCategory;

    public Order(Customer customer, Date date, Location customerLocation, String orderCategory) {
        this.id = numOrders;
        this.customer = customer;
        this.customerLocation = customerLocation;
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

    public Location getCustomerLocation() {
        return customerLocation;
    }

    public Map<Integer, StoreOrder> getStoresOrderMap() {
        return storesOrder;
    }

    public Collection<StoreOrder> getStoresOrder() {
        return storesOrder.values();
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

    public static void initNumOrders() {
        numOrders = 1;
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

    public StoreOrder getStoreOrder(int storeId) {
        return storesOrder.get(storeId);
    }

    public void addStoreOrder(Store store, Map<Item, Float> itemsAndQuantities,
                              Map<String, ArrayList<Offer>> appliedOffers) {
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
        storeOrder.SetValues(customerLocation, appliedOffers);
        storesOrder.put(store.getId(), storeOrder);
        setValues(storeOrder);
    }

    private void setValues(StoreOrder storeOrder) {
        itemsCost += storeOrder.getItemsCost();
        deliveryCost += storeOrder.getDeliveryCost();
    }

    public void addStoresOrder(Collection<DynamicOrderStoreData> dynamicOrderStoresData) {
        for (DynamicOrderStoreData dynamicOrderStoreData : dynamicOrderStoresData) {
            addStoreOrder(dynamicOrderStoreData.getStore(),
                    dynamicOrderStoreData.getItemsAndQuantities(),
                    dynamicOrderStoreData.getAppliedOffers());
        }
    }

    public void finish(Store store) {
        store.updateTotalDeliveriesRevenue(customerLocation);
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
                ", customerLocation=" + customerLocation +
                ", storesOrder=" + storesOrder +
                ", itemsCost=" + itemsCost +
                ", deliveryCost=" + deliveryCost +
                ", orderCategory=" + orderCategory +
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