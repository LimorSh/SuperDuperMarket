package course.java.sdm.engine.dto;
import course.java.sdm.engine.Utils;
import course.java.sdm.engine.engine.Discount;
import course.java.sdm.engine.engine.Offer;
import java.util.ArrayList;
import java.util.Collection;

public class DiscountDto {
    private final String name;
    private final int storeItemId;
    private final String storeItemName;
    private final double storeItemQuantity;
    private final String category;
    private final Collection<OfferDto> offersDto;

    public DiscountDto(Discount discount, String storeItemName) {
        this.name = discount.getName();
        this.storeItemId = discount.getStoreItemId();
        this.storeItemName = storeItemName;
        this.storeItemQuantity = Utils.roundNumberWithTwoDigitsAfterPoint(discount.getStoreItemQuantity());
        this.category = discount.getCategory().getCategoryStr();
        offersDto = new ArrayList<>();
        copyDiscountOffersDto(discount);
    }

    private void copyDiscountOffersDto(Discount discount) {
        Collection<Offer> offers = discount.getOffers();
        for (Offer offer : offers) {
            OfferDto offerDto = new OfferDto(offer);
            offersDto.add(offerDto);
        }
    }

    public String getName() {
        return name;
    }

    public int getStoreItemId() {
        return storeItemId;
    }

    public String getStoreItemName() {
        return storeItemName;
    }

    public double getStoreItemQuantity() {
        return storeItemQuantity;
    }

    public String getCategory() {
        return category;
    }

    public Collection<OfferDto> getOffersDto() {
        return offersDto;
    }
}
