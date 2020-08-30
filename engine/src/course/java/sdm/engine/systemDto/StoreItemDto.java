package course.java.sdm.engine.systemDto;
import course.java.sdm.engine.systemEngine.Discount;
import course.java.sdm.engine.systemEngine.Store;
import course.java.sdm.engine.systemEngine.StoreItem;

import java.util.ArrayList;
import java.util.Collection;

public class StoreItemDto extends ItemDto {

    private final float price;
    private final float totalSold;
    private final ArrayList<DiscountDto> discountsDto;

    public StoreItemDto(StoreItem storeItem) {
        super(storeItem);
        this.price = storeItem.getPrice();
        this.totalSold = storeItem.getTotalSold();
        discountsDto = new ArrayList<>();
        copyDiscountsDto(storeItem);
    }

    private void copyDiscountsDto(StoreItem storeItem) {
        Collection<Discount> discounts = storeItem.getDiscounts();
        for (Discount discount : discounts) {
            DiscountDto discountDto = new DiscountDto(discount);
            discountsDto.add(discountDto);
        }
    }

    public float getPrice() {
        return price;
    }

    public float getTotalSold() {
        return totalSold;
    }

    public Collection<DiscountDto> getDiscountsDto() {
        return discountsDto;
    }
}
