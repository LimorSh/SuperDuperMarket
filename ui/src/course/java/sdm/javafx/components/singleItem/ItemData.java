package course.java.sdm.javafx.components.singleItem;

import course.java.sdm.engine.dto.ItemDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.UtilsUI;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
    protected SimpleStringProperty purchaseCategory;
    protected SimpleIntegerProperty numberOfStoresSellingTheItem;
    protected SimpleFloatProperty averagePrice;
    protected SimpleFloatProperty totalSells;

    public ItemData() {
        this.id = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
        this.name = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.purchaseCategory = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.numberOfStoresSellingTheItem = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
        this.averagePrice = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
        this.totalSells = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
    }

    private void setId(int id) {
        this.id.set(id);
    }

    private void setName(String name) {
        this.name.set(name);
    }

    private void setPurchaseCategory(String purchaseCategory) {
        this.purchaseCategory.set(purchaseCategory);
    }

    private void setNumberOfStoresSellingTheItem(int numberOfStoresSellingTheItem) {
        this.numberOfStoresSellingTheItem.set(numberOfStoresSellingTheItem);
    }

    private void setAveragePrice(float averagePrice) {
        this.averagePrice.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(averagePrice));
    }

    private void setTotalSells(float totalSells) {
        this.totalSells.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(totalSells));
    }

    public void setItemDataValues(ItemDto itemDto) {
        setId(itemDto.getId());
        setName(itemDto.getName());
        setPurchaseCategory(itemDto.getPurchaseCategory());
        setNumberOfStoresSellingTheItem(itemDto.getNumberOfStoresSellingTheItem());
        setAveragePrice(itemDto.getAverageItemPrice());
        setTotalSells(itemDto.getTotalAmountOfItemSells());
    }
}
