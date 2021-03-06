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
    private final int storeId;
    private final int storeItemId;
    private final double storeItemQuantity;
    private final Category category;
    private final Map<Integer, Offer> offers;

    public Discount(String name, int storeId, int storeItemId, double storeItemQuantity, Category category) {
        this.name = name;
        this.storeId = storeId;
        this.storeItemId = storeItemId;
        this.storeItemQuantity = storeItemQuantity;
        this.category = category;
        this.offers = new HashMap<>();
    }

    public Discount(SDMDiscount sdmDiscount, int storeId) {
        this(sdmDiscount.getName(), storeId, sdmDiscount.getIfYouBuy().getItemId()
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

    public int getStoreId() {
        return storeId;
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

    public Offer getOffer(int storeItemId) {
        for (Offer offer : offers.values()) {
            if (offer.getItem().getId() == storeItemId) {
                return offer;
            }
        }
        return null;
    }

    public void addOffer(Offer offer) {
        int storeItemId = offer.getItem().getId();
        if (!isItemInTheOffers(storeItemId)) {
            offers.put(storeItemId, offer);
        }
        else {
            throw new IllegalArgumentException("There is already an offer with ID " + storeItemId + ". Each offer should have an unique ID.");
        }
    }

    public boolean isGreaterOrEqualToStoreItemQuantity(float storeItemQuantity) {
        return (storeItemQuantity >= this.storeItemQuantity);
    }

    public boolean isItemInTheOffers(int itemId) {
        return offers.containsKey(itemId);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "name='" + name + '\'' +
                ", storeId=" + storeId +
                ", storeItemId=" + storeItemId +
                ", storeItemQuantity=" + storeItemQuantity +
                ", category=" + category +
                ", offers=" + offers +
                '}';
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
