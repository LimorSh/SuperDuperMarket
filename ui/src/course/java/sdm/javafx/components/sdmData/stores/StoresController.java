package course.java.sdm.javafx.components.sdmData.stores;

import course.java.sdm.engine.dto.ItemDto;
import course.java.sdm.engine.dto.StoreDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.sdmData.singleStore.SingleStoreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StoresController {

    @FXML private FlowPane flowPane;

    public void createAllStores(Collection<StoreDto> storesDto) {

        Collection<StoreDto> storesDtoSortedById = storesDto.stream()
                .sorted(Comparator.comparing(StoreDto::getId))
                .collect(Collectors.toList());

        for (StoreDto storeDto : storesDtoSortedById) {
            createStore(storeDto);
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
