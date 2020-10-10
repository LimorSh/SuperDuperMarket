package course.java.sdm.engine.dto;

import course.java.sdm.engine.Utils;
import course.java.sdm.engine.engine.Item;

public class ItemDto {

    private final int id;
    private final String name;
    private final String purchaseCategory;
    private final int numberOfStoresSellingTheItem;
    private final float averageItemPrice;
    private final float totalAmountOfItemSells;

    public ItemDto(Item item, int numberOfStoresSellingTheItem,
                   float averageItemPrice, float totalAmountOfItemSells) {
        this.id = item.getId();
        this.name = item.getName();
        this.purchaseCategory = item.getPurchaseCategory().getPurchaseCategoryStr();
        this.numberOfStoresSellingTheItem = numberOfStoresSellingTheItem;
        this.averageItemPrice = Utils.roundNumberWithTwoDigitsAfterPoint(averageItemPrice);
        this.totalAmountOfItemSells = Utils.roundNumberWithTwoDigitsAfterPoint(totalAmountOfItemSells);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPurchaseCategory() {
        return purchaseCategory;
    }

    public int getNumberOfStoresSellingTheItem() {
        return numberOfStoresSellingTheItem;
    }

    public float getAverageItemPrice() {
        return averageItemPrice;
    }

    public float getTotalAmountOfItemSells() {
        return totalAmountOfItemSells;
    }
}
