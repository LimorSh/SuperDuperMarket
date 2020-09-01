package course.java.sdm.javafx.components.singleItem;

import course.java.sdm.engine.dto.ItemDto;
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


    public ItemData(int id, String name, String purchaseCategory, int numberOfStoresSellingTheItem,
                    float averagePrice, float totalSells) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.purchaseCategory = new SimpleStringProperty(purchaseCategory);
        this.numberOfStoresSellingTheItem = new SimpleIntegerProperty(numberOfStoresSellingTheItem);
        this.averagePrice = new SimpleFloatProperty(averagePrice);
        this.totalSells = new SimpleFloatProperty(totalSells);
    }

//    public ItemData(ItemDto itemDto) {
//        this.id = new SimpleIntegerProperty(itemDto.getId());
//        this.name = new SimpleStringProperty(itemDto.getName());
//        this.purchaseCategory = new SimpleStringProperty(itemDto.getPurchaseCategory());
//        this.numberOfStoresSellingTheItem = new SimpleIntegerProperty(itemDto.getNumberOfStoresSellingTheItem());
//        this.averagePrice = new SimpleFloatProperty(itemDto.getAverageItemPrice());
//        this.totalSells = new SimpleFloatProperty(itemDto.getTotalAmountOfItemSells());
//    }

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
        this.averagePrice.set(averagePrice);
    }

    private void setTotalSells(float totalSells) {
        this.totalSells.set(totalSells);
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
