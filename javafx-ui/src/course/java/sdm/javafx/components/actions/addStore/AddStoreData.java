package course.java.sdm.javafx.components.actions.addStore;

import course.java.sdm.engine.engine.BusinessLogic;

import java.util.HashMap;
import java.util.Map;

public class AddStoreData {

    protected static final String ADD_STORE_SUCCESS = "\nThe store was added successfully!";

    protected static final String ITEM_PRICE_MSG_LABEL_TEXT = "A price should be a decimal number.";
    protected static final String ID_MSG_LABEL_TEXT = "An ID should be an integer number.";
    protected static final String NAME_MSG_LABEL_TEXT = "A name should have at least one English letter.";
    protected static final String LOCATION_COORDINATE_MSG_LABEL_TEXT = "A coordinate value should be an integer number.";
    protected static final String PPK_MSG_LABEL_TEXT = "A PPK should be an integer number.";

    protected int storeId;
    protected String name;
    protected int locationX;
    protected int locationY;
    protected int ppk;
    protected Map<Integer, Float> itemIdsAndPrices;

    public AddStoreData() {
        itemIdsAndPrices = new HashMap<>();
    }

    protected BusinessLogic businessLogic;

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }
}
