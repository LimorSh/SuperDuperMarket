package course.java.sdm.javafx.components.actions.order.storeItems;

import course.java.sdm.engine.dto.StoreItemDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.UtilsUI;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StoreItemData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
    protected SimpleStringProperty purchaseCategory;
    protected SimpleFloatProperty price;

    public StoreItemData(StoreItemDto storeItemDto) {
        this.id = new SimpleIntegerProperty(storeItemDto.getId());
        this.name = new SimpleStringProperty(storeItemDto.getName());
        this.purchaseCategory = new SimpleStringProperty(storeItemDto.getPurchaseCategory());
        this.price = new SimpleFloatProperty(storeItemDto.getPrice());
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

    public float getPrice() {
        return price.get();
    }
}
