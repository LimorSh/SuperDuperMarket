package course.java.sdm.javafx.components.sdmData.singleOrder.singleStore;

import course.java.sdm.engine.dto.BasicItemDto;
import course.java.sdm.engine.dto.OfferDto;
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
    protected SimpleStringProperty discount;

    private static final String IN_DISCOUNT_STR = "YES";
    private static final String NOT_IN_DISCOUNT_STR = "";

    public SinglePurchasedItemData(OrderLineDto orderLineDto) {
        BasicItemDto basicItemDto = orderLineDto.getBasicItemDto();
        this.id = new SimpleIntegerProperty(basicItemDto.getId());
        this.name = new SimpleStringProperty(basicItemDto.getName());
        this.purchaseCategory = new SimpleStringProperty(basicItemDto.getPurchaseCategory());
        this.quantity = new SimpleFloatProperty(orderLineDto.getQuantity());
        this.pricePerUnit = new SimpleFloatProperty(orderLineDto.getCost());
        this.totalCost = new SimpleFloatProperty(orderLineDto.getTotalCost());
        this.discount = new SimpleStringProperty(NOT_IN_DISCOUNT_STR);
    }

    public SinglePurchasedItemData(OfferDto offerDto) {
        this.id = new SimpleIntegerProperty(offerDto.getStoreItemId());
        this.name = new SimpleStringProperty(offerDto.getStoreItemName());
        this.purchaseCategory = new SimpleStringProperty(offerDto.getStoreItemPurchaseCategory());
        this.quantity = new SimpleFloatProperty((float)(offerDto.getQuantity()));
        this.pricePerUnit = new SimpleFloatProperty(offerDto.getAdditionalPrice());
        this.totalCost = new SimpleFloatProperty(offerDto.getTotalCost());
        this.discount = new SimpleStringProperty(IN_DISCOUNT_STR);
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
