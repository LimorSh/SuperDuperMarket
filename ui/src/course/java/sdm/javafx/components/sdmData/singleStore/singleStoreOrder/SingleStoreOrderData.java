package course.java.sdm.javafx.components.sdmData.singleStore.singleStoreOrder;

import course.java.sdm.engine.dto.OrderDto;
import course.java.sdm.javafx.UtilsUI;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SingleStoreOrderData {

    protected SimpleStringProperty date;
    protected SimpleIntegerProperty totalItems;
    protected SimpleFloatProperty itemsCost;
    protected SimpleFloatProperty deliveryCost;
    protected SimpleFloatProperty totalCost;

    public SingleStoreOrderData(OrderDto orderDto) {
        this.date = new SimpleStringProperty(UtilsUI.convertDateToString(orderDto.getDate()));
        this.totalItems = new SimpleIntegerProperty(orderDto.getTotalItems());
        this.itemsCost = new SimpleFloatProperty(UtilsUI.roundNumberWithTwoDigitsAfterPoint
                (orderDto.getItemsCost()));
        this.deliveryCost = new SimpleFloatProperty(UtilsUI.roundNumberWithTwoDigitsAfterPoint
                (orderDto.getDeliveryCost()));
        this.totalCost = new SimpleFloatProperty(UtilsUI.roundNumberWithTwoDigitsAfterPoint
                (orderDto.getTotalCost()));
    }

    public String getDate() {
        return date.get();
    }

    public int getTotalItems() {
        return totalItems.get();
    }

    public float getItemsCost() {
        return itemsCost.get();
    }

    public float getDeliveryCost() {
        return deliveryCost.get();
    }

    public float getTotalCost() {
        return totalCost.get();
    }
}
