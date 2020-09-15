package course.java.sdm.javafx.components.sdmData.stores;

import course.java.sdm.engine.dto.StoreDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.sdmData.singleStore.SingleStoreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;

public class StoresController {

    @FXML private FlowPane flowPane;

    public void createAllStores(Collection<StoreDto> storesDto) {
        if (!storesDto.isEmpty()) {
            for (StoreDto storeDto : storesDto) {
                createStore(storeDto);
            }
        }
        else {
            // show no stores component!
        }
    }

    private void createStore(StoreDto storeDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.SINGLE_STORE_FXML_RESOURCE);
            Node singleStore = loader.load();

            SingleStoreController singleStoreController = loader.getController();
            singleStoreController.setStoreDataValues(storeDto);
            singleStoreController.setItemsTableView(storeDto.getStoreItemsDto());
            singleStoreController.setOrdersTableView(storeDto.getOrdersDto(), storeDto.getId());
            singleStoreController.setDiscountsFlowPane(storeDto);

            flowPane.getChildren().add(singleStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
