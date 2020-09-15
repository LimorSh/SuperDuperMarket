package course.java.sdm.javafx.components.actions.order.discounts.singleDiscount;

import course.java.sdm.engine.Constants;
import course.java.sdm.engine.dto.DiscountDto;
import course.java.sdm.engine.dto.OfferDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.UtilsUI;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Collection;

public class DiscountData {

    protected SimpleStringProperty name;
    protected SimpleStringProperty itemDetails;
    protected SimpleDoubleProperty itemQuantity;
    protected SimpleStringProperty category;
    protected SimpleBooleanProperty isOneOfDiscountCategory;

    protected String categoryStr;
    protected Collection<OfferDto> offersDto;

    protected int itemIdTriggered;
    protected double remainderQuantityToApply;

    public DiscountData() {
        this.name = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.itemDetails = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.itemQuantity = new SimpleDoubleProperty(SuperDuperMarketConstants.INIT_DOUBLE);
        this.category = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.isOneOfDiscountCategory = new SimpleBooleanProperty(SuperDuperMarketConstants.INIT_BOOLEAN);
    }

    protected String getName() {
        return name.get();
    }

    protected String getCategoryStr() {
        return categoryStr;
    }

    protected Collection<OfferDto> getOffersDto() {
        return offersDto;
    }

    public int getItemIdTriggered() {
        return itemIdTriggered;
    }

    public double getRemainderQuantityToApply() {
        return remainderQuantityToApply;
    }

    protected OfferDto getOfferDtoByStoreItemId (int storeItemId) {
        for (OfferDto offerDto : offersDto) {
            if (offerDto.getStoreItemId() == storeItemId) {
                return offerDto;
            }
        }
        return null;
    }

    public void updateItemQuantityTriggered(double quantity) {
        this.remainderQuantityToApply -= quantity;
    }

    private void setName(String name) {
        this.name.set(name);
    }

    private void setItemDetails(String name, int id) {
        this.itemDetails.set(String.format(("%s (ID %d)"), name, id));
    }

    private void setItemQuantity(double itemQuantity) {
        this.itemQuantity.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(itemQuantity));
    }

    private void setCategory(String category) {
        String newCategory = "";
        if (category.equalsIgnoreCase(Constants.DISCOUNT_CATEGORY_ONE_OF)) {
            newCategory = " one of the following items:";
            isOneOfDiscountCategory.set(true);
        }
        else if (category.equalsIgnoreCase(Constants.DISCOUNT_CATEGORY_ALL_OR_NOTHING)){
            newCategory = " all the items below:";
        }
        else {
            newCategory = " the following item:";
        }
        this.category.set(newCategory);

        categoryStr = category;
    }

    private void setOffersDto(Collection<OfferDto> offersDto) {
        this.offersDto = offersDto;
    }

    private void setItemIdTriggered(int itemIdTriggered) {
        this.itemIdTriggered = itemIdTriggered;
    }

    private void setRemainderQuantityToApply(double remainderQuantityToApply) {
        this.remainderQuantityToApply = remainderQuantityToApply;
    }

    public void setDataValues(DiscountDto discountDto, ) {
        setName(discountDto.getName());
        setItemDetails(discountDto.getStoreItemName(), discountDto.getStoreItemId());
        setItemQuantity(discountDto.getStoreItemQuantity());
        setCategory(discountDto.getCategory());
        setOffersDto(discountDto.getOffersDto());
        setItemIdTriggered(discountDto.getStoreItemId());
        setRemainderQuantityToApply();  //צריך לשים את הכמות שנרכשה
    }
}
