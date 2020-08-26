package course.java.sdm.engine.systemEngine;

public class Offer {

    private final int storeItemId;
    private final float quantity;
    private final int additionalPrice;

    public Offer(int storeItemId, float quantity, int additionalPrice) {
        this.storeItemId = storeItemId;
        this.quantity = quantity;
        this.additionalPrice = additionalPrice;
    }

    public int getStoreItemId() {
        return storeItemId;
    }

    public float getQuantity() {
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

        if (storeItemId != offer.storeItemId) return false;
        if (Float.compare(offer.quantity, quantity) != 0) return false;
        return additionalPrice == offer.additionalPrice;
    }

    @Override
    public int hashCode() {
        int result = storeItemId;
        result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
        result = 31 * result + additionalPrice;
        return result;
    }
}
