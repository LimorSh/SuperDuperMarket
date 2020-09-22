package course.java.sdm.javafx.components.actions.addOrder.summery;
import course.java.sdm.javafx.components.actions.addOrder.summery.singleStore.OrderSummerySingleStoreInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class OrderSummeryInfo {

    private LocalDate date;
    private int customerId;
    private String customerName;
    private int customerXLocation;
    private int customerYLocation;
    private boolean isStaticOrder;
    private float itemsCost;
    private float deliveryCost;
    private float totalCost;
    private final Collection<OrderSummerySingleStoreInfo> singleStoresInfo;


    public OrderSummeryInfo() {
        singleStoresInfo = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public boolean getIsStaticOrder() {
        return isStaticOrder;
    }

    public void setIsStaticOrder(boolean value) {
        isStaticOrder = value;
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

    public Collection<OrderSummerySingleStoreInfo> getSingleStoresInfo() {
        return singleStoresInfo;
    }

    public void addSingleStoreInfo(OrderSummerySingleStoreInfo orderSummerySingleStoreInfo) {
        singleStoresInfo.add(orderSummerySingleStoreInfo);
    }

    public void updateCosts(float cost) {
        this.itemsCost += cost;
        this.totalCost += cost;
    }

    public Collection<Integer> getStoresIds() {
        Collection<Integer> storesIds = new ArrayList<>();
        for (OrderSummerySingleStoreInfo orderSummerySingleStoreInfo : singleStoresInfo) {
            storesIds.add(orderSummerySingleStoreInfo.getId());
        }
        return storesIds;
    }
}
