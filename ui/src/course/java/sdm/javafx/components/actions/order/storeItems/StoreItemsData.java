package course.java.sdm.javafx.components.actions.order.storeItems;

import course.java.sdm.engine.engine.BusinessLogic;

import java.util.HashMap;
import java.util.Map;

public class StoreItemsData {

    protected BusinessLogic businessLogic;
    protected final Map<Integer, Float> itemsIdsAndQuantities;
    protected float itemsCost;

    public StoreItemsData() {
        itemsIdsAndQuantities = new HashMap<>();
        itemsCost = 0;
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

    public float getItemsCost() {
        return itemsCost;
    }

    public void updateItemsCost(float cost) {
        itemsCost += cost;
    }
}
