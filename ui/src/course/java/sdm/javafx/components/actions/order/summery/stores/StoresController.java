package course.java.sdm.javafx.components.actions.order.summery.stores;

import course.java.sdm.engine.dto.ItemDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.SingleStoreController;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.SingleStoreInfo;
import course.java.sdm.javafx.components.sdmData.singleItem.SingleItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;

public class StoresController {

    @FXML private FlowPane flowPane;

    public void createAllStores(Collection<SingleStoreInfo> singleStoresInfo) {
        if (!singleStoresInfo.isEmpty()) {
            for (SingleStoreInfo singleStoreInfo : singleStoresInfo) {
                createSingleStore(singleStoreInfo);
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

    private void createSingleStore(SingleStoreInfo singleStoreInfo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ORDER_SUMMERY_SINGLE_STORE_FXML_RESOURCE);
            Node singleStore = loader.load();
            SingleStoreController singleStoreController = loader.getController();

            singleStoreController.setDataValues(singleStoreInfo);
            flowPane.getChildren().add(singleStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

