package course.java.sdm.engine.dto;

import course.java.sdm.engine.engine.Item;

public class ItemWithPriceDto extends BasicItemDto{

    private float price;
    private final boolean isAvailableInStore;

    public ItemWithPriceDto(Item item, boolean isAvailableInStore) {
        super(item);
        this.isAvailableInStore = isAvailableInStore;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public boolean isAvailableInStore() {
        return isAvailableInStore;
    }
}
