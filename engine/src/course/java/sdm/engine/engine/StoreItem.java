package course.java.sdm.engine.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class StoreItem extends Item{

    private float price;
    private float totalSold;
    private final ArrayList<Discount> discounts;

    public StoreItem(Item item, float price) {
        super(item);
        this.price = price;
        this.discounts = new ArrayList<>();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalSold() {
        return totalSold;
    }

    public Collection<Discount> getDiscounts() {
        return discounts;
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    public void updateTotalNumberSold(float quantity) {
        this.totalSold += quantity;
    }

    public boolean hasDiscounts() {
        return !discounts.isEmpty();
    }

    public boolean isDiscountExist(String discountName) {
        for (Discount discount : discounts) {
            if (discount.getName().equalsIgnoreCase(discountName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "StoreItem{" +
                "price=" + price +
                ", totalSold=" + totalSold +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StoreItem storeItem = (StoreItem) o;

        if (Float.compare(storeItem.price, price) != 0) return false;
        return Float.compare(storeItem.totalSold, totalSold) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (totalSold != +0.0f ? Float.floatToIntBits(totalSold) : 0);
        return result;
    }
}
