package course.java.sdm.engine.systemDto;
import course.java.sdm.engine.systemEngine.StoreItem;

public class StoreItemDto extends ItemDto {

    private final float price;
    private final float totalSold;

    public StoreItemDto(StoreItem storeItem) {
        super(storeItem);
        this.price = storeItem.getPrice();
        this.totalSold = storeItem.getTotalSold();
    }

    public float getPrice() {
        return price;
    }

    public float getTotalSold() {
        return totalSold;
    }
}
