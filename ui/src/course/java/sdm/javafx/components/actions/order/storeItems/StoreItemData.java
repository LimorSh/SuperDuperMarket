package course.java.sdm.javafx.components.actions.order.storeItems;

import course.java.sdm.engine.dto.ItemWithPriceDto;
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
    protected SimpleStringProperty price;
    private static final String NOT_AVAILABLE = "Not Available";

    public StoreItemData(ItemWithPriceDto itemWithPriceDto) {
        this.id = new SimpleIntegerProperty(itemWithPriceDto.getId());
        this.name = new SimpleStringProperty(itemWithPriceDto.getName());
        this.purchaseCategory = new SimpleStringProperty(itemWithPriceDto.getPurchaseCategory());
        if (itemWithPriceDto.isAvailableInStore()) {
            this.price = new SimpleStringProperty(String.format("%.2f", itemWithPriceDto.getPrice()));
        }
        else {
            this.price = new SimpleStringProperty(NOT_AVAILABLE);
        }
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

    public String getPrice() {
        return price.get();
    }
}
