package course.java.sdm.engine.engine.notifications;

import course.java.sdm.engine.Constants;
import course.java.sdm.engine.Utils;
import course.java.sdm.engine.engine.StoreOrder;

import java.util.Objects;

public class OrderNotification extends Notification {

    private final int orderId;
    private final String customerName;
    private final String storeName;
    private final int totalItems;
    private final float itemsCost;
    private final float deliveryCost;

    public OrderNotification(String zoneName, StoreOrder storeOrder) {
        super(zoneName, storeOrder.getStore().getOwnerName(), Constants.NOTIFICATION_TYPE_NEW_ORDER_STR);
        this.orderId = storeOrder.getOrderId();
        this.customerName = storeOrder.getCustomerName();
        this.storeName = storeOrder.getStore().getName();
        this.totalItems = storeOrder.getTotalItems();
        this.itemsCost = Utils.roundNumberWithTwoDigitsAfterPoint(storeOrder.getItemsCost());
        this.deliveryCost = Utils.roundNumberWithTwoDigitsAfterPoint(storeOrder.getDeliveryCost());
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getStoreName() {
        return storeName;
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

    @Override
    public String toString() {
        return "OrderNotification{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", totalItems=" + totalItems +
                ", itemsCost=" + itemsCost +
                ", deliveryCost=" + deliveryCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderNotification that = (OrderNotification) o;

        if (orderId != that.orderId) return false;
        if (totalItems != that.totalItems) return false;
        if (Float.compare(that.itemsCost, itemsCost) != 0) return false;
        if (Float.compare(that.deliveryCost, deliveryCost) != 0) return false;
        if (!Objects.equals(customerName, that.customerName)) return false;
        return Objects.equals(storeName, that.storeName);
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (storeName != null ? storeName.hashCode() : 0);
        result = 31 * result + totalItems;
        result = 31 * result + (itemsCost != +0.0f ? Float.floatToIntBits(itemsCost) : 0);
        result = 31 * result + (deliveryCost != +0.0f ? Float.floatToIntBits(deliveryCost) : 0);
        return result;
    }
}
