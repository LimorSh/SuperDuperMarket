package course.java.sdm.javafx.components.actions.addOrder.summery.singleStore;

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
    protected SimpleStringProperty discount;

    private static final String IN_DISCOUNT_STR = "YES";
    private static final String NOT_IN_DISCOUNT_STR = "NO";

    public OrderSummerySinglePurchasedItemData(OrderSummerySingleStoreItemInfo orderSummerySingleStoreItemInfo) {
        this.id = new SimpleIntegerProperty(orderSummerySingleStoreItemInfo.getId());
        this.name = new SimpleStringProperty(orderSummerySingleStoreItemInfo.getName());
        this.purchaseCategory = new SimpleStringProperty(orderSummerySingleStoreItemInfo.getPurchaseCategory());
        this.quantity = new SimpleFloatProperty(orderSummerySingleStoreItemInfo.getQuantity());
        this.pricePerUnit = new SimpleFloatProperty(orderSummerySingleStoreItemInfo.getPricePerUnit());
        this.totalCost = new SimpleFloatProperty(orderSummerySingleStoreItemInfo.getTotalCost());
        boolean isInDiscount = orderSummerySingleStoreItemInfo.getIsInDiscount();
        this.discount = new SimpleStringProperty(getDiscountStr(isInDiscount));
    }

    private String getDiscountStr(boolean isInDiscount) {
        String discountStr;
        if (isInDiscount) {
            discountStr = IN_DISCOUNT_STR;
        }
        else {
            discountStr = NOT_IN_DISCOUNT_STR;
        }
        return discountStr;
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

    public String getDiscount() {
        return discount.get();
    }
}
