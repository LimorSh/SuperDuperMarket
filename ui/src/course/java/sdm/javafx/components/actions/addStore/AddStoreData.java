package course.java.sdm.javafx.components.actions.addStore;

import course.java.sdm.engine.engine.BusinessLogic;

public class AddStoreData {

    protected static final String ADD_STORE_SUCCESS = "The store was added successfully!";
    protected static final String ADD_STORE_FAILURE = "The store could not be added for the following reason: ";
    protected static final String ADD_ITEM_PRICE_FAILURE = "The price you entered is not valid.";

    protected static final String ID_MSG_LABEL_TEXT = "An ID should be an integer number.";
    protected static final String LOCATION_COORDINATE_MSG_LABEL_TEXT = "A coordinate value should be an integer number.";
    protected static final String PPK_MSG_LABEL_TEXT = "A PPK should be an integer number.";

    protected BusinessLogic businessLogic;

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }
}
