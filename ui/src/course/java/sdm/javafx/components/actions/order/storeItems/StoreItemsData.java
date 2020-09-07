package course.java.sdm.javafx.components.actions.order.storeItems;

import java.util.HashMap;
import java.util.Map;

public class StoreItemsData {

    protected final Map<Integer, Float> itemsIdsAndQuantities;

    public StoreItemsData() {
        itemsIdsAndQuantities = new HashMap<>();
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
}
