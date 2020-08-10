package course.java.sdm.engine.systemDto;

import course.java.sdm.engine.StoreItem;

public class StoreItemDto extends ItemDto {

    private final float price;
    private final int totalNumberSold;

    public StoreItemDto(StoreItem storeItem) {
        super(storeItem);
        this.price = storeItem.getPrice();
        this.totalNumberSold = storeItem.getTotalNumberSold();
    }

    public float getPrice() {
        return price;
    }

    public int getTotalNumberSold() {
        return totalNumberSold;
    }
}
