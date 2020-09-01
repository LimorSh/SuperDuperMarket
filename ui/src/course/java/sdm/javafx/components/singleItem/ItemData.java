package course.java.sdm.javafx.components.singleItem;

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

    public void setId(int id) {
        this.id.set(id);
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public void setPurchaseCategory(String purchaseCategory) {
        this.purchaseCategory.set(purchaseCategory);
    }

    public void setNumberOfStoresSellingTheItem(int numberOfStoresSellingTheItem) {
        this.numberOfStoresSellingTheItem.set(numberOfStoresSellingTheItem);
    }

    public void setAveragePrice(float averagePrice) {
        this.averagePrice.set(averagePrice);
    }

    public void setTotalSells(float totalSells) {
        this.totalSells.set(totalSells);
    }
}
