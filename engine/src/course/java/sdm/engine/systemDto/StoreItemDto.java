package course.java.sdm.engine.systemDto;
import course.java.sdm.engine.systemEngine.Discount;
import course.java.sdm.engine.systemEngine.StoreItem;

public class StoreItemDto extends ItemDto {

    private final float price;
    private final float totalSold;
    private final DiscountDto discountDto;

    public StoreItemDto(StoreItem storeItem) {
        super(storeItem);
        this.price = storeItem.getPrice();
        this.totalSold = storeItem.getTotalSold();
        Discount discount = storeItem.getDiscount();
        if (discount != null) {
            this.discountDto = new DiscountDto(discount);
        }
        else {
            discountDto = null;
        }
    }

    public float getPrice() {
        return price;
    }

    public float getTotalSold() {
        return totalSold;
    }

    public DiscountDto getDiscountDto() {
        return discountDto;
    }
}
