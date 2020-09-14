package course.java.sdm.engine.engine;
import course.java.sdm.engine.jaxb.schema.generated.SDMOffer;

public class Offer {

    private final Item item;
    private final double quantity;
    private final int additionalPrice;

    public Offer(Item item, double quantity, int additionalPrice) {
        this.item = item;
        this.quantity = quantity;
        this.additionalPrice = additionalPrice;
    }

    public Offer(SDMOffer sdmOffer, Item item) {
        this(item, sdmOffer.getQuantity(), sdmOffer.getForAdditional());
    }

    public Item getItem() {
        return item;
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

        return item.getId() == offer.getItem().getId();
    }

    @Override
    public int hashCode() {
        return item.getId();
    }
}
