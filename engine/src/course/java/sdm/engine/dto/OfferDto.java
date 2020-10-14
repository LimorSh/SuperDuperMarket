package course.java.sdm.engine.dto;
import course.java.sdm.engine.Utils;
import course.java.sdm.engine.engine.Offer;

public class OfferDto {

    private final int storeItemId;
    private final String storeItemName;
    private final String storeItemPurchaseCategory;
    private final double quantity;
    private final int additionalPrice;
    private final float totalCost;

    public OfferDto(Offer offer) {
        this.storeItemId = offer.getItem().getId();
        this.storeItemName = offer.getItem().getName();
        this.storeItemPurchaseCategory = offer.getItem().getPurchaseCategory().getPurchaseCategoryStr();
        this.quantity = Utils.roundNumberWithTwoDigitsAfterPoint(offer.getQuantity());
        this.additionalPrice = offer.getAdditionalPrice();
        this.totalCost = Utils.roundNumberWithTwoDigitsAfterPoint(
                (float) (offer.getQuantity() * offer.getAdditionalPrice()));
    }

    public int getStoreItemId() {
        return storeItemId;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getStoreItemName() {
        return storeItemName;
    }

    public String getStoreItemPurchaseCategory() {
        return storeItemPurchaseCategory;
    }

    public int getAdditionalPrice() {
        return additionalPrice;
    }

    public float getTotalCost() {
        return totalCost;
    }
}
