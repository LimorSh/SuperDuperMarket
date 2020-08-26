package course.java.sdm.engine.systemEngine;

import course.java.sdm.engine.Configurations;

import java.util.HashMap;
import java.util.Map;

public class Discount {

    public enum Category {
        IRRELEVANT(Configurations.DISCOUNT_CATEGORY_IRRELEVANT),
        ONE_OF(Configurations.DISCOUNT_CATEGORY_ONE_OF),
        ALL_OR_NOTHING(Configurations.DISCOUNT_CATEGORY_ALL_OR_NOTHING),
        ;

        private final String categoryStr;

        Category(String category) {
            this.categoryStr = category;
        }

        public String getCategoryStr() {
            return categoryStr;
        }
    }

    private final String name;
    private final int storeItemId;
    private final int storeItemQuantity;
    private final Category category;
    private final Map<Integer, Offer> offers;

    public Discount(String name, int storeItemId, int storeItemQuantity, Category category) {
        this.name = name;
        this.storeItemId = storeItemId;
        this.storeItemQuantity = storeItemQuantity;
        this.category = category;
        this.offers = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getStoreItemId() {
        return storeItemId;
    }

    public int getStoreItemQuantity() {
        return storeItemQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public Map<Integer, Offer> getOffers() {
        return offers;
    }






    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (storeItemId != discount.storeItemId) return false;
        if (storeItemQuantity != discount.storeItemQuantity) return false;
        if (name != null ? !name.equals(discount.name) : discount.name != null) return false;
        return category == discount.category;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + storeItemId;
        result = 31 * result + storeItemQuantity;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
