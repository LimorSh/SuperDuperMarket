package course.java.sdm.javafx.components.sdmData.SingleOrder.singleStore;

import course.java.sdm.engine.dto.BasicItemDto;
import course.java.sdm.engine.dto.OrderLineDto;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SinglePurchasedItemData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
    protected SimpleStringProperty purchaseCategory;
    protected SimpleFloatProperty quantity;
    protected SimpleFloatProperty pricePerUnit;
    protected SimpleFloatProperty totalCost;
//    protected SimpleBooleanProperty discount;
//    protected SimpleStringProperty discount;

    public SinglePurchasedItemData(OrderLineDto orderLineDto) {
        BasicItemDto basicItemDto = orderLineDto.getBasicItemDto();
        this.id = new SimpleIntegerProperty(basicItemDto.getId());
        this.name = new SimpleStringProperty(basicItemDto.getName());
        this.purchaseCategory = new SimpleStringProperty(basicItemDto.getPurchaseCategory());
        this.quantity = new SimpleFloatProperty(orderLineDto.getQuantity());
        this.pricePerUnit = new SimpleFloatProperty(orderLineDto.getCost());
        this.totalCost = new SimpleFloatProperty(orderLineDto.getTotalCost());
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
