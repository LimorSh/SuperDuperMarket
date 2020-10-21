package course.java.sdm.javafx.components.actions.addOrder.storeItems;

import course.java.sdm.engine.engine.BusinessLogic;

import java.util.HashMap;
import java.util.Map;

public class StoreItemsData {

    protected BusinessLogic businessLogic;
    protected final Map<Integer, Float> itemsIdsAndQuantities;
    protected float itemsCost;
    protected static final int PRICE_COLUMN_INDEX = 3;
    protected static final String INVALID_QUANTITY_INPUT_MSG = "Invalid quantity: The quantity should be a number.";
    protected boolean activateAnimation;

    public StoreItemsData() {
        itemsIdsAndQuantities = new HashMap<>();
        itemsCost = 0;
        activateAnimation = true;
    }

    protected void updateItemsAndQuantities(int itemId, float quantity) {
        if (itemsIdsAndQuantities.containsKey(itemId)) {
            quantity += itemsIdsAndQuantities.get(itemId);
        }
        itemsIdsAndQuantities.put(itemId, quantity);
    }

    public Map<Integer, Float> getItemsIdsAndQuantities() {
        return itemsIdsAndQuantities;
    }

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public void setActivateAnimation(boolean activateAnimation) {
        this.activateAnimation = activateAnimation;
    }

    public float getItemsCost() {
        return itemsCost;
    }

    public void updateItemsCost(float cost) {
        itemsCost += cost;
    }

    public boolean isItemsIdsAndQuantitiesEmpty() {
        return itemsIdsAndQuantities.isEmpty();
    }
}
