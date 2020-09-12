package course.java.sdm.javafx.components.actions.order.summery.dynamicOrder;

import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.order.summery.dynamicOrder.singleStore.DynamicOrderSingleStoreSummeryController;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class DynamicOrderStoresSummeryController {

    @FXML private FlowPane flowPane;

    public void createAllStores(Collection<OrderSummerySingleStoreInfo> storesInfo) {

        Collection<OrderSummerySingleStoreInfo> storesInfoSortedById = storesInfo.stream()
                .sorted(Comparator.comparing(OrderSummerySingleStoreInfo::getId))
                .collect(Collectors.toList());

        if (!storesInfoSortedById.isEmpty()) {
            for (OrderSummerySingleStoreInfo singleStoreInfo : storesInfoSortedById) {
                createStore(singleStoreInfo);
            }
        }
        else {
            // show no items component!
        }
    }

    private void createStore(OrderSummerySingleStoreInfo singleStoreInfo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.DYNAMIC_ORDER_SINGLE_STORE_SUMMERY_FXML_RESOURCE);
            Node singleStore = loader.load();
            DynamicOrderSingleStoreSummeryController singleStoreController = loader.getController();

            singleStoreController.setItemDataValues(singleStoreInfo);
            flowPane.getChildren().add(singleStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
