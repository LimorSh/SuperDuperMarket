package course.java.sdm.javafx.components.sdmData.singleStore.singleDiscount;

import course.java.sdm.engine.Constants;
import course.java.sdm.engine.dto.DiscountDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.UtilsUI;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class DiscountData {

    protected SimpleStringProperty name;
    protected SimpleStringProperty itemDetails;
    protected SimpleDoubleProperty itemQuantity;
    protected SimpleStringProperty category;


    public DiscountData() {
        this.name = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.itemDetails = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.itemQuantity = new SimpleDoubleProperty(SuperDuperMarketConstants.INIT_DOUBLE);
        this.category = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setItemDetails(String name, int id) {
        this.itemDetails.set(String.format(("%s (ID %d)"), name, id));
    }

    public void setItemQuantity(double itemQuantity) {
        this.itemQuantity.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(itemQuantity));
    }

    public void setCategory(String category) {
        String newCategory = "";
        if (category.equalsIgnoreCase(Constants.DISCOUNT_CATEGORY_ONE_OF)) {
            newCategory = " one of the following items:";
        }
        else if (category.equalsIgnoreCase(Constants.DISCOUNT_CATEGORY_ALL_OR_NOTHING)){
            newCategory = " all the items below:";
        }
        else {
            newCategory = " the following item:";
        }
        this.category.set(newCategory);
    }

    public void setDataValues(DiscountDto discountDto) {
        setName(discountDto.getName());
        setItemDetails(discountDto.getStoreItemName(), discountDto.getStoreItemId());
        setItemQuantity(discountDto.getStoreItemQuantity());
        setCategory(discountDto.getCategory());
    }
}
