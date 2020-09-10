package course.java.sdm.engine.engine;
import java.util.*;

public class Order {

    private static int numOrders = 1;
    private final int id;
    private final Date date;
    private final Customer customer;
    private final Map<Integer, StoreOrder> storesOrder;     //The key is store id
    private float itemsCost;
    private float deliveryCost;
    private int totalItems;

    public Order(Customer customer, Date date) {
        this.id = numOrders;
        this.customer = customer;
        this.date = date;
        storesOrder = new HashMap<>();
        numOrders++;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Map<Integer, StoreOrder> getStoresOrder() {
        return storesOrder;
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

//    public void addOrderLines(Map<Item, Float> itemsAndQuantities) {
//
//        //do this for every store
////        store.addOrder(this);
//
//        itemsAndQuantities.forEach((item,itemQuantity) -> {
//            int itemId = item.getId();
//            float itemPrice = store.getItemPrice(itemId);
//            if (!orderLines.containsKey(itemId)) {
//                OrderLine orderLine = new OrderLine(item, itemQuantity, itemPrice);
//                orderLines.put(itemId, orderLine);
//            }
//            else {
//                float totalQuantity = itemQuantity;
//                totalQuantity += orderLines.get(itemId).getQuantity();
//                orderLines.get(itemId).setQuantity(totalQuantity);
//            }
//            updateOrderData(item, itemQuantity);
//        });
//    }

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
            updateOrderData(store, item, itemQuantity);
        });

        StoreOrder storeOrder = new StoreOrder(store, orderLines, totalItems, itemsCost,
                deliveryCost, getTotalCost());

        storesOrder.put(store.getId(), storeOrder);
    }

    public void addStoresOrder(Map<Store, Map<Item, Float>> storesToItemsAndQuantities) {
        storesToItemsAndQuantities.forEach((store,itemsAndQuantities) -> {
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
                updateOrderData(store, item, itemQuantity);
            });

            StoreOrder storeOrder = new StoreOrder(store, orderLines, totalItems, itemsCost,
                    deliveryCost, getTotalCost());

            storesOrder.put(store.getId(), storeOrder);

        }
    }

    private void updateOrderData(Store store, Item item, float itemQuantity) {
        updateTotalItems(item.getPurchaseCategory(), itemQuantity);
        updateItemsCost(store, item, itemQuantity);
        updateTotalNumberSoldItemInStore(store, item, itemQuantity);
    }

    public void updateTotalItems(Item.PurchaseCategory purchaseCategory, float quantity) {
        if (purchaseCategory.equals(Item.PurchaseCategory.PER_UNIT))
            totalItems += (int) quantity;
        else
            totalItems++;
    }

    public void updateItemsCost(Store store, Item item, float quantity) {
        float itemCost = (store.getItemPrice(item));
        itemsCost += (itemCost * quantity);
    }

    public void updateDeliveryCost(Store store) {
        float cost = store.getDeliveryCost(customer.getLocation());
        deliveryCost += cost;
    }

    public void updateTotalNumberSoldItemInStore(Store store, Item item, float quantity) {
        store.updateTotalNumberSoldItem(item, quantity);
    }

    public void finish(Store store) {
        updateDeliveryCost(store);
        store.updateTotalDeliveriesRevenue(customer.getLocation());
        customer.addOrder(this);
    }

    public void finish(Collection<Store> stores) {
        for (Store store : stores) {
            finish(store);
        }
    }


//    @Override
//    public String toString() {
//        return "Order{" +
//                "id=" + id +
//                ", date=" + date +
//                ", customer=" + customer +
//                ", store=" + store +
//                ", orderLines=" + orderLines +
//                ", itemsCost=" + itemsCost +
//                ", deliveryCost=" + deliveryCost +
//                ", totalItems=" + totalItems +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Order order = (Order) o;
//
//        return id == order.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return id;
//    }
}