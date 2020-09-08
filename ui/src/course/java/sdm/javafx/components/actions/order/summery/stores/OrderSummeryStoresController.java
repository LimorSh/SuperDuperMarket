package course.java.sdm.javafx.components.actions.order.summery.stores;

import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreController;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;

public class OrderSummeryStoresController {

    @FXML private FlowPane flowPane;

    public void createAllStores(Collection<OrderSummerySingleStoreInfo> singleStoresInfo) {
        if (!singleStoresInfo.isEmpty()) {
            for (OrderSummerySingleStoreInfo orderSummerySingleStoreInfo : singleStoresInfo) {
                createSingleStore(orderSummerySingleStoreInfo);
            }
        }
        else {
            // show no items component!
        }
    }

//    public void createAllStores(StoresInfo storesInfo) {
//        Collection<SingleStoreInfo> singleStoresInfo = storesInfo.getSingleStoresInfo();
//        if (!singleStoresInfo.isEmpty()) {
//            for (SingleStoreInfo singleStoreInfo : singleStoresInfo) {
//                createSingleStore(singleStoreInfo);
//            }
//        }
//        else {
//            // show no items component!
//        }
//    }

    private void createSingleStore(OrderSummerySingleStoreInfo orderSummerySingleStoreInfo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ORDER_SUMMERY_SINGLE_STORE_FXML_RESOURCE);
            Node singleStore = loader.load();
            OrderSummerySingleStoreController singleStoreController = loader.getController();

            singleStoreController.setDataValues(orderSummerySingleStoreInfo);
            flowPane.getChildren().add(singleStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

