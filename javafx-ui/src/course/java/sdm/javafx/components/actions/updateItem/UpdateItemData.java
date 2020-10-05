package course.java.sdm.javafx.components.actions.updateItem;

import course.java.sdm.engine.engine.BusinessLogic;

public class UpdateItemData {

    protected static final String DELETE_ITEM = "Delete Item";
    protected static final String ADD_ITEM = "Add Item";
    protected static final String UPDATE_PRICE = "Update Price";
    protected static final String DELETE_ITEM_SUCCESS = "The item was deleted from the store successfully!";
    protected static final String ADD_ITEM_SUCCESS = "The item was added to the store successfully!";
    protected static final String UPDATE_ITEM_SUCCESS = "The item price was updated successfully!";

    protected BusinessLogic businessLogic;

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }
}
