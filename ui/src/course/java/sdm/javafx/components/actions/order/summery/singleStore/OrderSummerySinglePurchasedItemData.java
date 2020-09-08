package course.java.sdm.javafx.components.actions.order.summery.singleStore;

import course.java.sdm.engine.dto.ItemWithPriceDto;
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

//    public OrderSummerySinglePurchasedItemData(OrderSummerySinglePurchasedItemInfo orderSummerySinglePurchasedItemInfo) {
//        this.id = new SimpleIntegerProperty(orderSummerySinglePurchasedItemInfo.getId());
//        this.name = new SimpleStringProperty(orderSummerySinglePurchasedItemInfo.getName());
//        this.purchaseCategory = new SimpleStringProperty(orderSummerySinglePurchasedItemInfo.getPurchaseCategory());
//        this.quantity = new SimpleStringProperty(orderSummerySinglePurchasedItemInfo.getPurchaseCategory());
//
//    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getPurchaseCategory() {
        return purchaseCategory.get();
    }
}
