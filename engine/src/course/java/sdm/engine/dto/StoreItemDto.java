package course.java.sdm.engine.dto;
import course.java.sdm.engine.engine.StoreItem;

public class StoreItemDto {

    private final int id;
    private final String name;
    private final String purchaseCategory;
    private final float price;
    private final float totalSells;
//    private final ArrayList<DiscountDto> discountsDto;

    public StoreItemDto(StoreItem storeItem) {
        this.id = storeItem.getId();
        this.name = storeItem.getName();
        this.purchaseCategory = storeItem.getPurchaseCategory().getPurchaseCategoryStr();
        this.price = storeItem.getPrice();
        this.totalSells = storeItem.getTotalSells();
//        discountsDto = new ArrayList<>();
//        copyDiscountsDto(storeItem);
    }

//    private void copyDiscountsDto(StoreItem storeItem) {
//        Collection<Discount> discounts = storeItem.getDiscounts();
//        for (Discount discount : discounts) {
//            DiscountDto discountDto = new DiscountDto(discount, storeItem.getName());
//            discountsDto.add(discountDto);
//        }
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPurchaseCategory() {
        return purchaseCategory;
    }

    public float getPrice() {
        return price;
    }

    public float getTotalSells() {
        return totalSells;
    }

//    public Collection<DiscountDto> getDiscountsDto() {
//        return discountsDto;
//    }
}
