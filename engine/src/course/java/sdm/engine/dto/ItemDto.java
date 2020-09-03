package course.java.sdm.engine.dto;

import course.java.sdm.engine.engine.Item;

public class ItemDto extends BasicItemDto {

    private final int numberOfStoresSellingTheItem;
    private final float averageItemPrice;
    private final float totalAmountOfItemSells;

    public ItemDto(Item item, int numberOfStoresSellingTheItem,
                   float averageItemPrice, float totalAmountOfItemSells) {
        super(item);
        this.numberOfStoresSellingTheItem = numberOfStoresSellingTheItem;
        this.averageItemPrice = averageItemPrice;
        this.totalAmountOfItemSells = totalAmountOfItemSells;
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
