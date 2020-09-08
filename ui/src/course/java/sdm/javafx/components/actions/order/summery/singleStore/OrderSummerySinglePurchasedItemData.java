package course.java.sdm.javafx.components.actions.order.summery.singleStore;

import course.java.sdm.javafx.SuperDuperMarketConstants;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderSummerySinglePurchasedItemData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
    protected SimpleStringProperty purchaseCategory;
    protected SimpleFloatProperty quantity;
    protected SimpleFloatProperty pricePerUnit;
    protected SimpleFloatProperty totalCost;
//    protected SimpleBooleanProperty discount;
//    protected SimpleStringProperty discount;

    public OrderSummerySinglePurchasedItemData(OrderSummerySingleStoreItemInfo orderSummerySingleStoreItemInfo) {
        this.id = new SimpleIntegerProperty(orderSummerySingleStoreItemInfo.getId());
        this.name = new SimpleStringProperty(orderSummerySingleStoreItemInfo.getName());
        this.purchaseCategory = new SimpleStringProperty(orderSummerySingleStoreItemInfo.getPurchaseCategory());
        this.quantity = new SimpleFloatProperty(orderSummerySingleStoreItemInfo.getQuantity());
        this.pricePerUnit = new SimpleFloatProperty(orderSummerySingleStoreItemInfo.getPricePerUnit());
        this.totalCost = new SimpleFloatProperty(orderSummerySingleStoreItemInfo.getTotalCost());
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getPurchaseCategory() {
        return purchaseCategory.get();
    }

    public float getQuantity() {
        return quantity.get();
    }

    public float getPricePerUnit() {
        return pricePerUnit.get();
    }

    public float getTotalCost() {
        return totalCost.get();
    }
}
