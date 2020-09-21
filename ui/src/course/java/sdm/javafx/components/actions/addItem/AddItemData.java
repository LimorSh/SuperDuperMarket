package course.java.sdm.javafx.components.actions.addItem;

import course.java.sdm.engine.engine.BusinessLogic;

import java.util.HashMap;
import java.util.Map;

public class AddItemData {

    protected static final String ADD_ITEM_SUCCESS = "\nThe item was added successfully!";

    protected static final String ITEM_PRICE_MSG_LABEL_TEXT = "A price should be a decimal number.";
    protected static final String ID_MSG_LABEL_TEXT = "An ID should be an integer number.";
    protected static final String EMPTY_PURCHASE_CATEGORY_LABEL_TEXT = "You must choose purchase category.";

    protected int itemId;
    protected String name;
    protected String purchasedCategory;
    protected Map<Integer, Float> storeIdsAndPrices;

    public AddItemData() {
        storeIdsAndPrices = new HashMap<>();
    }

    protected BusinessLogic businessLogic;

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

}
