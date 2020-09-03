package course.java.sdm.engine.engine;
import course.java.sdm.engine.Constants;
import course.java.sdm.engine.jaxb.schema.generated.SDMDiscount;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Discount {

    public enum Category {
        IRRELEVANT(Constants.DISCOUNT_CATEGORY_IRRELEVANT),
        ONE_OF(Constants.DISCOUNT_CATEGORY_ONE_OF),
        ALL_OR_NOTHING(Constants.DISCOUNT_CATEGORY_ALL_OR_NOTHING),
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
    private final double storeItemQuantity;
    private final Category category;
    private final Map<Integer, Offer> offers;

    public Discount(String name, int storeItemId, double storeItemQuantity, Category category) {
        this.name = name;
        this.storeItemId = storeItemId;
        this.storeItemQuantity = storeItemQuantity;
        this.category = category;
        this.offers = new HashMap<>();
    }

    public Discount(SDMDiscount sdmDiscount) {
        this(sdmDiscount.getName(), sdmDiscount.getIfYouBuy().getItemId()
                ,sdmDiscount.getIfYouBuy().getQuantity(),
                convertStringToCategory(sdmDiscount.getThenYouGet().getOperator().toLowerCase()));
    }

    private static Discount.Category convertStringToCategory(String category) {
        if (category.contains(Constants.DISCOUNT_CATEGORY_ONE_OF)) {
            return Discount.Category.ONE_OF;
        }
        else if (category.contains(Constants.DISCOUNT_CATEGORY_ALL_OR_NOTHING)) {
            return Discount.Category.ALL_OR_NOTHING;
        }
        return Discount.Category.IRRELEVANT;
    }

    public String getName() {
        return name;
    }

    public int getStoreItemId() {
        return storeItemId;
    }

    public double getStoreItemQuantity() {
        return storeItemQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public Collection<Offer> getOffers() {
        return offers.values();
    }

    public void addOffer(Offer offer) {
        int storeItemId = offer.getStoreItemId();
        if (!isItemInTheOffers(storeItemId)) {
            offers.put(storeItemId, offer);
        }
        else {
            throw new IllegalArgumentException("There is already an offer with ID " + storeItemId + ". Each offer should have an unique ID.");
        }
    }

    public boolean isItemInTheOffers(int itemId) {
        return offers.containsKey(itemId);
    }

    public boolean isGreaterOrEqualToStoreItemQuantity(double storeItemQuantity) {
        return (storeItemQuantity >= this.storeItemQuantity);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        return Objects.equals(name, discount.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
