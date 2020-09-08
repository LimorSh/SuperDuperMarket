package course.java.sdm.javafx.components.actions.order.summery.stores;

import course.java.sdm.javafx.components.actions.order.summery.singleStore.SingleStoreInfo;

import java.util.ArrayList;
import java.util.Collection;

public class StoresInfo {

    private final Collection<SingleStoreInfo> singleStoresInfo;

    public StoresInfo() {
        singleStoresInfo = new ArrayList<>();
    }

    public Collection<SingleStoreInfo> getSingleStoresInfo() {
        return singleStoresInfo;
    }
}
