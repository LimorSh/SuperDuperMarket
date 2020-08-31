package course.java.sdm.engine.engine;
import course.java.sdm.engine.jaxb.schema.generated.SDMOffer;

public class Offer {

    private final int storeItemId;
    private final double quantity;
    private final int additionalPrice;

    public Offer(int storeItemId, double quantity, int additionalPrice) {
        this.storeItemId = storeItemId;
        this.quantity = quantity;
        this.additionalPrice = additionalPrice;
    }

    public Offer(SDMOffer sdmOffer) {
        this(sdmOffer.getItemId(), sdmOffer.getQuantity(), sdmOffer.getForAdditional());
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        return storeItemId == offer.storeItemId;
    }

    @Override
    public int hashCode() {
        return storeItemId;
    }
}
