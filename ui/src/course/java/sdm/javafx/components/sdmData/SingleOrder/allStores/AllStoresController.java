package course.java.sdm.javafx.components.sdmData.SingleOrder.allStores;

import course.java.sdm.engine.dto.StoreOrderDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.sdmData.SingleOrder.singleStore.SingleStoreInOrderController;
import course.java.sdm.javafx.components.sdmData.SingleOrder.singleStore.StoreInOrderData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;

public class AllStoresController {

    @FXML private FlowPane flowPane;


    public void createAllStores(Collection<StoreOrderDto> storesOrderDto) {
        if (!storesOrderDto.isEmpty()) {
            for (StoreOrderDto storeOrderDto : storesOrderDto) {
                createSingleStore(storeOrderDto);
            }
        }
        else {
            // show no items component!
        }
    }

    private void createSingleStore(StoreOrderDto storeOrderDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.SINGLE_STORE_IN_ORDER_FXML_RESOURCE);
            Node singleStore = loader.load();
            SingleStoreInOrderController singleStoreInOrderController = loader.getController();

            singleStoreInOrderController.setDataValues(storeOrderDto);
            singleStoreInOrderController.setTableViewData(storeOrderDto.getOrderLinesDto());

            flowPane.getChildren().add(singleStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
