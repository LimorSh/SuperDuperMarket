package course.java.sdm.javafx.components.sdmData.singleStore.singleStoreItem;

import course.java.sdm.engine.dto.StoreItemDto;
import course.java.sdm.javafx.UtilsUI;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SingleStoreItemData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
    protected SimpleStringProperty purchaseCategory;
    protected SimpleFloatProperty pricePerUnit;
    protected SimpleFloatProperty totalSold;

    public SingleStoreItemData(StoreItemDto storeItemDto) {
        this.id = new SimpleIntegerProperty(storeItemDto.getId());
        this.name = new SimpleStringProperty(storeItemDto.getName());
        this.purchaseCategory = new SimpleStringProperty(storeItemDto.getPurchaseCategory());
        this.pricePerUnit = new SimpleFloatProperty(UtilsUI.roundNumberWithTwoDigitsAfterPoint(storeItemDto.getPrice()));
        this.totalSold = new SimpleFloatProperty(UtilsUI.roundNumberWithTwoDigitsAfterPoint(storeItemDto.getTotalSold()));
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

    public float getPricePerUnit() {
        return pricePerUnit.get();
    }

    public float getTotalSold() {
        return totalSold.get();
    }
}
