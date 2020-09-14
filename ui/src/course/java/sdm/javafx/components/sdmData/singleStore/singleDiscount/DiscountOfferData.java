package course.java.sdm.javafx.components.sdmData.singleStore.singleDiscount;

import course.java.sdm.engine.dto.OfferDto;
import course.java.sdm.javafx.UtilsUI;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DiscountOfferData {

    protected SimpleIntegerProperty storeItemId;
    protected SimpleStringProperty storeItemName;
    protected SimpleStringProperty storeItemPurchaseCategory;
    protected SimpleDoubleProperty quantity;
    protected SimpleIntegerProperty additionalPrice;


    public DiscountOfferData(OfferDto offerDto) {
        this.storeItemId = new SimpleIntegerProperty(offerDto.getStoreItemId());
        this.storeItemName = new SimpleStringProperty(offerDto.getStoreItemName());
        this.storeItemPurchaseCategory = new SimpleStringProperty(offerDto.getStoreItemPurchaseCategory());
        this.quantity = new SimpleDoubleProperty(
                UtilsUI.roundNumberWithTwoDigitsAfterPoint(offerDto.getQuantity()));
        this.additionalPrice = new SimpleIntegerProperty(offerDto.getAdditionalPrice());
    }

    public int getStoreItemId() {
        return storeItemId.get();
    }

    public SimpleIntegerProperty storeItemIdProperty() {
        return storeItemId;
    }

    public String getStoreItemName() {
        return storeItemName.get();
    }

    public SimpleStringProperty storeItemNameProperty() {
        return storeItemName;
    }

    public String getStoreItemPurchaseCategory() {
        return storeItemPurchaseCategory.get();
    }

    public SimpleStringProperty storeItemPurchaseCategoryProperty() {
        return storeItemPurchaseCategory;
    }

    public double getQuantity() {
        return quantity.get();
    }

    public SimpleDoubleProperty quantityProperty() {
        return quantity;
    }

    public int getAdditionalPrice() {
        return additionalPrice.get();
    }

    public SimpleIntegerProperty additionalPriceProperty() {
        return additionalPrice;
    }
}
