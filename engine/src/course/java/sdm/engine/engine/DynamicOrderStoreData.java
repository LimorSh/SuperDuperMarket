package course.java.sdm.engine.engine;

import java.util.ArrayList;
import java.util.Map;

public class DynamicOrderStoreData {

    private final Store store;
    private final Map<Item, Float> itemsAndQuantities;
    private Map<String, ArrayList<Offer>> appliedOffers;

    public DynamicOrderStoreData(Store store, Map<Item, Float> itemsAndQuantities) {
        this.store = store;
        this.itemsAndQuantities = itemsAndQuantities;
    }

    public Store getStore() {
        return store;
    }

    public Map<Item, Float> getItemsAndQuantities() {
        return itemsAndQuantities;
    }

    public Map<String, ArrayList<Offer>> getAppliedOffers() {
        return appliedOffers;
    }

    public void setAppliedOffers(Map<String, ArrayList<Offer>> appliedOffers) {
        this.appliedOffers = appliedOffers;
    }
}
