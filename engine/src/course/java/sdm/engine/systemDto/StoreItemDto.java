package course.java.sdm.engine.systemDto;

import course.java.sdm.engine.StoreItem;

public class StoreItemDto extends ItemDto {

    private final float price;
    private final int totalSold;

    public StoreItemDto(StoreItem storeItem) {
        super(storeItem);
        this.price = storeItem.getPrice();
        this.totalSold = storeItem.getTotalSold();
    }

    public float getPrice() {
        return price;
    }

    public int getTotalSold() {
        return totalSold;
    }
}
