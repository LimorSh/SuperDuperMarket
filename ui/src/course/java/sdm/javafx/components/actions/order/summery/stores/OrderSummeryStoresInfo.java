package course.java.sdm.javafx.components.actions.order.summery.stores;

import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreInfo;

import java.util.ArrayList;
import java.util.Collection;

public class OrderSummeryStoresInfo {

    private final Collection<OrderSummerySingleStoreInfo> singleStoresInfo;

    public OrderSummeryStoresInfo() {
        singleStoresInfo = new ArrayList<>();
    }

    public Collection<OrderSummerySingleStoreInfo> getSingleStoresInfo() {
        return singleStoresInfo;
    }
}
