package course.java.sdm.javafx.components.actions.addOrder.summery.stores;

import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.addOrder.summery.singleStore.OrderSummerySingleStoreController;
import course.java.sdm.javafx.components.actions.addOrder.summery.singleStore.OrderSummerySingleStoreInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class OrderSummeryStoresController {

    @FXML private FlowPane flowPane;

    public void createAllStores(Collection<OrderSummerySingleStoreInfo> singleStoresInfo) {

        Collection<OrderSummerySingleStoreInfo> storesInfoSortedById = singleStoresInfo.stream()
                .sorted(Comparator.comparing(OrderSummerySingleStoreInfo::getId))
                .collect(Collectors.toList());

        for (OrderSummerySingleStoreInfo orderSummerySingleStoreInfo : storesInfoSortedById) {
            createSingleStore(orderSummerySingleStoreInfo);
        }
    }

    private void createSingleStore(OrderSummerySingleStoreInfo orderSummerySingleStoreInfo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ORDER_SUMMERY_SINGLE_STORE_FXML_RESOURCE);
            Node singleStore = loader.load();
            OrderSummerySingleStoreController singleStoreController = loader.getController();

            singleStoreController.setDataValues(orderSummerySingleStoreInfo);
            singleStoreController.setTableViewData(orderSummerySingleStoreInfo.getOrderSummerySingleStoreItemsInfo());

            flowPane.getChildren().add(singleStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

