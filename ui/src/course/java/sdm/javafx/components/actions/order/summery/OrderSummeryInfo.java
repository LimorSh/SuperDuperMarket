package course.java.sdm.javafx.components.actions.order.summery;

import course.java.sdm.javafx.components.actions.order.summery.singleStore.SingleStoreInfo;
import java.util.ArrayList;
import java.util.Collection;

public class OrderSummeryInfo {

    private int customerId;
    private String customerName;
    private int customerXLocation;
    private int customerYLocation;
    private float itemsCost;
    private float deliveryCost;
    private float totalCost;
    private final Collection<SingleStoreInfo> singleStoresInfo;

    public OrderSummeryInfo() {
        singleStoresInfo = new ArrayList<>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerXLocation() {
        return customerXLocation;
    }

    public void setCustomerXLocation(int customerXLocation) {
        this.customerXLocation = customerXLocation;
    }

    public int getCustomerYLocation() {
        return customerYLocation;
    }

    public void setCustomerYLocation(int customerYLocation) {
        this.customerYLocation = customerYLocation;
    }

    public float getItemsCost() {
        return itemsCost;
    }

    public void setItemsCost(float itemsCost) {
        this.itemsCost = itemsCost;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public Collection<SingleStoreInfo> getSingleStoresInfo() {
        return singleStoresInfo;
    }

    public void addSingleStoreInfo(SingleStoreInfo singleStoreInfo) {
        singleStoresInfo.add(singleStoreInfo);
    }
}
