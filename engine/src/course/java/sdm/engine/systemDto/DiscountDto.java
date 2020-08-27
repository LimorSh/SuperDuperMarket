package course.java.sdm.engine.systemDto;
import course.java.sdm.engine.systemEngine.Discount;
import course.java.sdm.engine.systemEngine.Offer;
import java.util.ArrayList;
import java.util.Collection;

public class DiscountDto {
    private final String name;
    private final int storeItemId;
    private final int storeItemQuantity;
    private final String category;
    private final Collection<OfferDto> offersDto;

    public DiscountDto(Discount discount) {
        this.name = discount.getName() ;
        this.storeItemId = discount.getStoreItemId();
        this.storeItemQuantity = discount.getStoreItemQuantity();
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

    public int getStoreItemQuantity() {
        return storeItemQuantity;
    }

    public String getCategory() {
        return category;
    }

    public Collection<OfferDto> getOffersDto() {
        return offersDto;
    }
}
