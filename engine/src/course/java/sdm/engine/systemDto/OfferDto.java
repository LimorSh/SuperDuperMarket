package course.java.sdm.engine.systemDto;
import course.java.sdm.engine.systemEngine.Offer;

public class OfferDto {

    private final int storeItemId;
    private final double quantity;
    private final int additionalPrice;

    public OfferDto(Offer offer) {
        this.storeItemId = offer.getStoreItemId();
        this.quantity = offer.getQuantity();
        this.additionalPrice = offer.getAdditionalPrice();
    }

    public int getStoreItemId() {
        return storeItemId;
    }

    public double getQuantity() {
        return quantity;
    }

    public int getAdditionalPrice() {
        return additionalPrice;
    }
}
